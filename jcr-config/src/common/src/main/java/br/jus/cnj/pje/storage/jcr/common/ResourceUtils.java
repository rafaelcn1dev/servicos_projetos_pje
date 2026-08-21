package br.jus.cnj.pje.storage.jcr.common;

import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.HEADER_CONTENT_LENGTH;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.HEADER_CONTENT_RANGE;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_SERVICE_UNAVAILABLE;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.lang3.StringUtils;

/**
 * Métodos utilitários para tratamento de recurso em repositórios HTTP
 * 
 * @author leoneparise
 */
public class ResourceUtils {
	
	/**
	 * Obtem o tamanho do arquivo remoto.
	 * 
	 * @param uri endereco do recurso
	 * @return tamanho do recurso ou {@code -1} caso não exista no repositório
	 * 
	 * @throws IOException
	 * @throws HttpException
	 */
	public static long getResourceSize(String uri, HttpClient http) throws HttpException, IOException {
		HeadMethod head = new HeadMethod(uri);
		try {
			int resp = http.executeMethod(head);

			if(resp == SC_NOT_FOUND) {
				return -1;
			} else if(resp == SC_OK) {
				Header length = head.getResponseHeader(HEADER_CONTENT_LENGTH);
				return length == null ? -1 : Long.parseLong(length.getValue());
			} else {
				throw new HttpException(String.format("Retorno do servidor inválido %d", resp));
			}
		} finally {
			head.releaseConnection();
		}
	}
	
	/**
	 * Insere uma parte de um recurso em um repositório DAV
	 * 
	 * @param uri endereço do recurso
	 * @param data dados do recurso
	 * @param range informações sobre tamanho do recurso, tamanho da parte e offset do recurso  
	 * @param mimetype tipo do recurso. No caso de {@code null} será utilizado o mimetype {@code application/octet-stream}
	 * @param http cliente http
	 * 
	 * @return codigo de resposta do servidor
	 * 
	 * @throws HttpException
	 * @throws IOException
	 */
	public static int putResource(String uri, byte[] data, Range range, String mimetype, HttpClient http) throws HttpException, IOException {
		ByteArrayRequestEntity entity = new ByteArrayRequestEntity(
			Arrays.copyOf(data, range.getLength()), 
			StringUtils.defaultString(mimetype, StorageConstants.DEFAULT_MIMETYPE)
		);
		
		PutMethod put = new PutMethod(uri);
		put.setRequestEntity(entity);
		put.setRequestHeader(HEADER_CONTENT_RANGE, range.toContentRange());
		put.setRequestHeader(HEADER_CONTENT_LENGTH, Long.toString(range.getLength()));
		
		try {
			return http.executeMethod(put);
		} finally {
			put.releaseConnection();
		}
	}
	
	/**
	 * Obtém o conteúdo de um recurso do repositório
	 * 
	 * @param uri endereço do recurso
	 * @param http cliente http
	 * 
	 * @return stream de leitura do recurso
	 * 
	 * @throws HttpException
	 * @throws IOException
	 */
	public static InputStream getResource(String uri, HttpClient http) throws HttpException, IOException {
		GetMethod get = new GetMethod(uri);
		int resp = http.executeMethod(get);
		
		switch(resp) {
			case SC_OK:	
				return get.getResponseBodyAsStream();
			case SC_NOT_FOUND: 
				throw new HttpException(String.format("Rescurso não encontrado %d", resp));
			case SC_CONFLICT: 
				throw new HttpException(String.format("Estado do recurso inválido %d", resp));
			case SC_SERVICE_UNAVAILABLE:
			case SC_INTERNAL_SERVER_ERROR: 
				throw new HttpException(String.format("Erro interno %d", resp));
			default: 
				throw new HttpException(String.format("Código de respo %d", resp));
		}
	}
	
	/**
	 * Obtém o endereço de determinado recurso
	 * 
	 * @param url endereço do repositório WebDAV
	 * @param path endereço relativo do recurso
	 * @return endereço http completo do recurso
	 */
	public static String getResourceURI(String url, String path) {
		return String.format("%s/%s", url, path);
	}
}
