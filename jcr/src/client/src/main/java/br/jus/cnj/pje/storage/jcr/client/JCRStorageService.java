package br.jus.cnj.pje.storage.jcr.client;

import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

import br.jus.cnj.pje.extensao.StorageException;
import br.jus.cnj.pje.extensao.servico.StorageService;
import br.jus.cnj.pje.storage.jcr.common.Connection;
import br.jus.cnj.pje.storage.jcr.common.HashProvider;
import br.jus.cnj.pje.storage.jcr.common.Range;
import br.jus.cnj.pje.storage.jcr.common.ResourceUtils;

/**
 * Implementação JCR 2.0 do serviço de <i>storage</i> de documentos definido no
 * <b>Pje Interfaces</b>
 * 
 * @author leoneparise
 */
@Name("storageService")
@Scope(ScopeType.APPLICATION)
@Startup
public class JCRStorageService implements StorageService {
	
	@In(value="JCRConfiguration", create=true)
	private Configuration configuration;
	
	@Override
	public String persist(InputStream data, String mimetype) throws StorageException {
		File temp = null;
		
		try {
			temp = TemporaryFileInputStream.createFile(data);
			
			String hash = HashProvider.hash(temp);
			String uri = getResourceURI(hash);
			
			// Obtém o tamanho do arquivo atual e do arquivo remoto
			long fileSize = temp.length();
			long remoteSize = ResourceUtils.getResourceSize(uri, getHttpClient());
			
			// Calcula a posição atual do arquivo com relação ao tamanho do arquivo remoto.
			long startPos = remoteSize > 0 ? remoteSize : 0;
			int chunkSize = configuration.getChunkSize();
			
			if(fileSize > remoteSize) {
				// Enviar o arquivo em chunks
				InputStream stream = createStreamAfterPos(temp, startPos);
				byte[] buffer = new byte[chunkSize];
				
				try {
					for(long pos = startPos; pos < fileSize; pos += chunkSize) {
						int read = stream.read(buffer);
						int resp = ResourceUtils.putResource(getResourceURI(hash), buffer, new Range(pos, read, fileSize), mimetype, getHttpClient());
						
						if(resp != SC_NO_CONTENT)
							throw new StorageException(String.format("Falha ao enviar documento. Retorno inválido do servidor: %d", resp));
					}
				}
				finally {
					if(stream != null) stream.close();
				}
			} else if(fileSize < remoteSize) {
				throw new StorageException("Falha ao enviar documento. O tamanho do arquivo remoto é maior que o arquivo local");
			}
			
			return hash;
		} catch (HttpException e) {
			throw new StorageException(e);
		} catch (IOException e) {
			throw new StorageException(e);
		} finally {
			if(temp != null) temp.delete();
		}
	}
	
	@Override
	public String persist(InputStream data) throws StorageException {
		return persist(data, null);
	}

	@Override
	public InputStream retrieve(String identifier) throws StorageException {
		try {
			return TemporaryFileInputStream.createStream(ResourceUtils.getResource(getResourceURI(identifier), getHttpClient()));
		} catch (HttpException e) {
			throw new StorageException(e);
		} catch (IOException e) {
			throw new StorageException(e);
		}
	}	
	
	/**
	 * Cria um Stream a partir de determinada posição no arquivo 
	 * 
	 * @param file arquivo de entrada
	 * @param pos posição no arquivo em bytes
	 * @return stream de leitura
	 */
	private InputStream createStreamAfterPos(File file, long pos) throws IOException {
		FileInputStream stream = new FileInputStream(file);
		stream.skip(pos);
		return stream;
	}
	
	/**
	 * Obtém o endereço do recurso identificado pelo hash dado
	 *  
	 * @param hash hash do recurso
	 * @return endereço do recurso
	 */
	private String getResourceURI(String hash) {
		return ResourceUtils.getResourceURI(configuration.getUrl(), hash);
	}
	
	/**
	 * Obtém o cliente http associado
	 * 
	 * @return cliente http
	 */
	private HttpClient getHttpClient() {
		return new Connection(configuration.getUrl(), configuration.getUsername(), 
				configuration.getPassword(), configuration.getHostMaxConn()).getHttpClient();
	}
	
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
}
