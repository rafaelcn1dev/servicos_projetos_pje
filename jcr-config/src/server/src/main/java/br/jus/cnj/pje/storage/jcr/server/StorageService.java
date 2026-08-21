package br.jus.cnj.pje.storage.jcr.server;

import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.JCR_CONTENT_SIZE;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.JCR_TEMPORARY;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.MIX_RESOURCE_EXT;
import static org.apache.jackrabbit.JcrConstants.JCR_CONTENT;
import static org.apache.jackrabbit.JcrConstants.JCR_DATA;
import static org.apache.jackrabbit.JcrConstants.JCR_LASTMODIFIED;
import static org.apache.jackrabbit.JcrConstants.JCR_MIMETYPE;
import static org.apache.jackrabbit.JcrConstants.NT_FILE;
import static org.apache.jackrabbit.JcrConstants.NT_RESOURCE;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.nodetype.NodeType;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;

import org.apache.commons.io.IOUtils;

import br.jus.cnj.pje.storage.jcr.common.HashProvider;
import br.jus.cnj.pje.storage.jcr.server.exception.InconsistentHashException;
import br.jus.cnj.pje.storage.jcr.server.exception.NodeNotFoundException;
import br.jus.cnj.pje.storage.jcr.server.exception.NotTemporaryNodeException;
import br.jus.cnj.pje.storage.jcr.server.exception.PropertyNotFoundException;
import br.jus.cnj.pje.storage.jcr.server.exception.StorageException;
import br.jus.cnj.pje.storage.jcr.server.support.JCRTemplate;
import br.jus.cnj.pje.storage.jcr.server.support.JCRTemplateWithReturn;

public class StorageService {
	private PathBuilder pathBuilder;
	private File tempDir;
	
	public StorageService(PathBuilder pathBuilder, String tempDir) {
		super();
		this.pathBuilder = pathBuilder;
		this.tempDir = new File(tempDir);
		
		if(!this.tempDir.exists())
			this.tempDir.mkdirs();
	}
	
	/**
	 * Obtém atributos de um recurso armazenado
	 * 
	 * @param hash id do recurso
	 * @return atributos do recurso ou null caso não seja encontrado
	 */
	public Attributes getAttributesById(String hash) {
		return hasNodeFile(hash) ? new Attributes(getNodeFile(hash)) : null;
	}
	
	/**
	 * Obtém o recurso armazenado
	 * 
	 * @param hash id do recruso
	 * @return recurso ou null caso não seja encontrado
	 */
	public Resource getResourceById(String hash) {
		return hasNodeFile(hash) ? new Resource(getNodeFile(hash)) : null;
	}
	
	/**
	 * Persiste um arquivo ou parte no repositório JCR. O conteúdo do {@link stream} será acrescentado
	 * a um arquivo temporário enquanto o argumento {@link persist} for {@code false}. Uma vez {@code true}
	 * o hash é verificado e o documento é persistido no repositório.
	 * 
	 * @param hash hash do documento
	 * @param mimetype tipo do documento
	 * @param persist indica se o documento deve ser persistido no repositório
	 * @param stream conteúdo do documento completo ou parte.
	 * @throws RepositoryException 
	 * @throws IOException 
	 */
	public void persistFile(final String hash, final String mimetype, final boolean persist, final InputStream stream) {
		new JCRTemplate() {
			public void doWithouReturn(Session session) throws Exception {
				// Cria o nó no JCR caso não exista
				Node node = hasNodeFile(hash) ? getNodeFile(hash) : createNodeFile(hash, mimetype);
				File file = new File(tempDir, hash);
				
				if(!isTemporary(node))
					throw new NotTemporaryNodeException(hash);
				
				if(!file.exists())
					file.createNewFile();
				
				// Acrescenta o conteúdo do arquivo ao arquivo temporário e atualiza o nó
				FileOutputStream out = new FileOutputStream(file, true);
				try{
					IOUtils.copy(stream, out);
				} finally {
					out.close();
				}
				
				// Atualiza o tamanho do arquivo no nó
				updateNodeFileSize(node, file.length());
				
				// Persiste o documento no repositório
				if(persist) {
					String fileHash = HashProvider.hash(new FileInputStream(file));
					
					if(hash.equals(fileHash)) {
						updateNodeFileData(node, new FileInputStream(file));
						updateNodeFileTemp(node, false);
						file.delete();
					} else {
						node.remove();
						file.delete();
						throw new InconsistentHashException(hash, fileHash);
					}
				}
			}
		}.execute();
	}
	
	/**
	 * Verifica se o documento identificado pelo hash existe no repositório
	 * 
	 * @param identifier hash de identificação
	 * @return {@code true} caso existe {@code false} caso contrário
	 * 
	 * @throws RepositoryException
	 */
	public boolean hasNodeFile(String identifier) {
		return getNodeFile(identifier) != null;
	}
	
	/**
	 * Obtém um nó de acordo com o hash de identificação do documento. Caso o nó não possua o mixin de extensão de arquivos
	 * o mixin é adicionado e deve ser persistido posterioromente.
	 * 
	 * @param identifier hash de identificação
	 * @return Node identificado pelo hash ou {@code null} caso contrário
	 * 
	 * @throws RepositoryException
	 */
	private Node getNodeFile(final String identifier){
		return new JCRTemplateWithReturn<Node>() {
			@Override
			public Node doAndReturn(Session session) throws Exception {
				QueryManager qm = session.getWorkspace().getQueryManager();
				Query query = qm.createQuery(String.format("SELECT * FROM [nt:file] WHERE name() = '%s'", identifier.trim()), Query.JCR_SQL2);
				NodeIterator it = query.execute().getNodes();

				if(it.hasNext()){
					return it.nextNode();
				} else
					return null;
			}
		}.execute();
	}
	
	/**
	 * Cria um novo nó de arquivo conforme o pathBuilder correspondente.
	 * 
	 * @param hash nome do arquivo
	 * @param mimetype tipo do arquivo
	 * @return nó do arquivo criado
	 * @throws RepositoryException
	 */
	private Node createNodeFile(final String hash, final String mimetype) {
		return new JCRTemplateWithReturn<Node>() {
			public Node doAndReturn(Session session) throws Exception {
				// Cria a estrutura de diretório e o nó do arquivo
				Node file = pathBuilder.buildPath(session.getRootNode()).addNode(hash, NT_FILE);
				
				// Cria o conteudo do arquivo.
				Node content = file.addNode(JCR_CONTENT, NT_RESOURCE);
				
				// Verifica e adiciona o MIXIN de extensões de arquivo
				if(!hasMixin(content, MIX_RESOURCE_EXT))
					content.addMixin(MIX_RESOURCE_EXT);
				
				
				
				// Atualiza os atributos com os valores padrões.
				content.setProperty(JCR_TEMPORARY, true);
				content.setProperty(JCR_CONTENT_SIZE, 0);
				content.setProperty(JCR_MIMETYPE, mimetype);
				content.setProperty(JCR_LASTMODIFIED, Calendar.getInstance());
				
				// Inicializa o valor binário vazio
				Binary bin = session.getValueFactory().createBinary(new ByteArrayInputStream(new byte[0]));
				content.setProperty(JCR_DATA, bin);
				bin.dispose();

				return file;
			};
		}.execute();
	}
	
	/**
	 * Atualiza o conteúdo do nó arquivo
	 * 
	 * @param file nó do tipo arquivo
	 * @param data conteúdo do arquivo
	 * @return Nó atualizado
	 * @throws RepositoryException
	 */
	public void updateNodeFileData(final Node file, final InputStream data) {
		new JCRTemplate() {
			public void doWithouReturn(Session session) throws Exception {
				if(!file.hasNode(JCR_CONTENT))
					throw new RepositoryException("O nó de arquivo não possui um nó de conteúdo");
				
				Node content = file.getNode(JCR_CONTENT);
				
				Binary bin = session.getValueFactory().createBinary(data);
				content.setProperty(JCR_DATA, bin);
				content.setProperty(JCR_CONTENT_SIZE, bin.getSize());
				content.setProperty(JCR_LASTMODIFIED, Calendar.getInstance());
				bin.dispose();
			};
		}.execute();
	}
	
	/**
	 * Atualiza o tamanho do conteúdo do nó arquivo
	 * 
	 * @param file nó do tipo arquivo
	 * @param size tamanho do conteúdo
	 * @return Nó atualizado
	 * @throws RepositoryException
	 */
	public void updateNodeFileSize(final Node file, final long size) {
		new JCRTemplate() {
			public void doWithouReturn(Session session) throws Exception {
				if(!file.hasNode(JCR_CONTENT))
					throw new RepositoryException("O nó de arquivo não possui um nó de conteúdo");
				
				Node content = file.getNode(JCR_CONTENT);

				content.setProperty(JCR_CONTENT_SIZE, size);
				content.setProperty(JCR_LASTMODIFIED, Calendar.getInstance());
			};
		}.execute();
	}
	
	/**
	 * Atualiza o atributo temporário do arquivo. Se for atribuído {@code false} uma vez, não pode
	 * ser mais atualizado
	 * 
	 * @param file nó do tipo arquivo
	 * @param tmp indica se o arquivo é temporário
	 * @return Nó atualizado
	 * @throws RepositoryException
	 */
	public void updateNodeFileTemp(final Node file, final boolean temp) {
		new JCRTemplate() {
			public void doWithouReturn(Session session) throws RepositoryException, StorageException {
				if(!file.hasNode(JCR_CONTENT))
					throw new RepositoryException("O nó de arquivo não possui um nó de conteúdo");
				
				Node content = file.getNode(JCR_CONTENT);
				
				if(content.getProperty(JCR_TEMPORARY).getBoolean())
					content.setProperty(JCR_TEMPORARY, temp);
			};
		}.execute();
	}
	
	/**
	 * Obtém todos os arquivos temporários
	 *  
	 * @param session sessão JCR
	 * @return NodeIterator contendo todos os arquivos temporários
	 * @throws RepositoryException
	 */
	public NodeIterator getAllTemporaryFiles() throws RepositoryException, StorageException {
		return new JCRTemplateWithReturn<NodeIterator>() {
			public NodeIterator doAndReturn(Session session) throws RepositoryException, StorageException {
				QueryManager qm = session.getWorkspace().getQueryManager();
				Query query = qm.createQuery("SELECT * FROM [nt:file] as file WHERE file.[jcr:temporary] = true", Query.JCR_SQL2);
				return query.execute().getNodes();
			}
		}.execute();
	}
	
	/**
	 * Verifia se o nó possui determinado mixin
	 * 
	 * @param node nó a ser verificado
	 * @param mixin nome do mixin
	 * @return {@code true} caso possua o mixin {@code false} caso contrário
	 * @throws RepositoryException
	 */
	private boolean hasMixin(Node node, String mixin) throws RepositoryException {
		for(NodeType ntype : node.getMixinNodeTypes())
			if(ntype.isMixin() && ntype.getName().equalsIgnoreCase(MIX_RESOURCE_EXT))
				return true;
		return false;
	}
	
	/**
	 * Verifica se o nó é temporário
	 * 
	 * @param node nó a ser verificado
	 * @return {@code true} caso seja temporário, {@code false} caso contrário
	 * @throws RepositoryException
	 * @throws StorageException
	 */
	private boolean isTemporary(Node node) throws RepositoryException, StorageException {
		if(!node.hasNode(JCR_CONTENT))
			throw new NodeNotFoundException(node.getName(), JCR_CONTENT);
		
		Node content = node.getNode(JCR_CONTENT);
		
		if(!content.hasProperty(JCR_TEMPORARY))
			throw new PropertyNotFoundException(node.getName(), JCR_TEMPORARY);
		
		return content.getProperty(JCR_TEMPORARY).getBoolean();
	}
}
