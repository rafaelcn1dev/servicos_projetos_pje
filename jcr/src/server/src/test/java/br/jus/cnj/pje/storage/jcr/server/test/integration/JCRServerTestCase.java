package br.jus.cnj.pje.storage.jcr.server.test.integration;

import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.junit.BeforeClass;
import org.junit.Test;

import br.jus.cnj.pje.storage.jcr.common.Connection;
import br.jus.cnj.pje.storage.jcr.common.HashProvider;
import br.jus.cnj.pje.storage.jcr.common.Range;
import br.jus.cnj.pje.storage.jcr.common.ResourceUtils;

public class JCRServerTestCase {
	// Http client configuration
	private static String url;
	private static String username;
	private static String password;
	private static int    maxHostConnections;
	
	private static int    chunkSize = 256;
	
	@Test
	public void testPersistWithCorrectHash() throws HttpException, IOException {
		byte[] data = generateRandomString(100).getBytes(Charset.forName("UTF-8"));
		String hash = HashProvider.hash(data);
		
		int resp = ResourceUtils.putResource(getResourceURI(hash), data, new Range(0, data.length, data.length), "text/plain", getHttpClient());
		Assert.assertEquals(SC_NO_CONTENT, resp);
	}
	
	@Test
	public void testPersistWithIncorrectHash() throws HttpException, IOException {
		byte[] data = generateRandomString(100).getBytes(Charset.forName("UTF-8"));
		String hash = HashProvider.hash(data) + "0";
		
		int resp = ResourceUtils.putResource(getResourceURI(hash), data, new Range(0, data.length, data.length), "text/plain", getHttpClient());
		Assert.assertEquals(HttpServletResponse.SC_CONFLICT, resp);
	}
	
	@Test
	public void testRetrieve() throws HttpException, IOException {
		// Insere dados
		byte[] data = generateRandomString(100).getBytes(Charset.forName("UTF-8"));
		String hash = HashProvider.hash(data);
		
		int resp = ResourceUtils.putResource(getResourceURI(hash), data, new Range(0, data.length, data.length), "text/plain", getHttpClient());
		Assert.assertEquals(SC_NO_CONTENT, resp);
		
		// Recupera dados e verifica a integridade do conteúdo
		InputStream out = ResourceUtils.getResource(getResourceURI(hash), getHttpClient());
		Assert.assertEquals(hash, HashProvider.hash(out));
		out.close();
	}
	
	@Test
	public void testChunkedPersist() throws HttpException, IOException {
		// Big data
		byte[] data = generateRandomString(1024).getBytes(Charset.forName("UTF-8"));
		long total = data.length;
		String hash = HashProvider.hash(data);
		ByteArrayInputStream stream = new ByteArrayInputStream(data);
		
		// Envia o primeiro chunk 
		byte[] buffer = new byte[chunkSize];
		stream.read(buffer);
		int resp1 = ResourceUtils.putResource(getResourceURI(hash), buffer, new Range(0, buffer.length, data.length), "text/plain", getHttpClient());
		Assert.assertEquals(SC_NO_CONTENT, resp1);
		
		// Verifica se o tamanho remoto é igual ao chunk enviado 
		long remoteSize = ResourceUtils.getResourceSize(getResourceURI(hash), getHttpClient());
		Assert.assertEquals(buffer.length, remoteSize);
		
		// Envia o restante dos dados
		if(remoteSize < data.length) {
			for(long pos = remoteSize; pos < total; pos += chunkSize) {
				int read = stream.read(buffer);
				int resp = ResourceUtils.putResource(getResourceURI(hash), buffer, new Range(pos, read, total), "text/plain", getHttpClient());
				
				Assert.assertEquals(SC_NO_CONTENT, resp);
			}
		}
		
		// Recupera dados e verifica a integridade do conteúdo
		InputStream out = ResourceUtils.getResource(getResourceURI(hash), getHttpClient());
		Assert.assertEquals(hash, HashProvider.hash(out));
		out.close();
	}
	
	@BeforeClass
	public static void setup() throws IOException {
		Properties config = new Properties();
		config.load(JCRServerTestCase.class.getResourceAsStream("/repository.properties"));
		// Configura conexao
		url = config.getProperty("jcr.url");
		username = config.getProperty("jcr.username");
		password = config.getProperty("jcr.password");
		maxHostConnections = Integer.parseInt(defaultString(config.getProperty("jcr.hostMaxConn"), "10"));
	}
	
	/**
	 * Obtém o endereço http do recurso a ser manipulado
	 * 
	 * @return endereço http do recurso
	 */
	public static String getResourceURI(String hash) {
		return String.format("%s/%s", url, hash);
	}
	
	/**
	 * Obtém o cliente http para realização dos testes
	 *  
	 * @return cliente http
	 */
	public HttpClient getHttpClient() {
		return new Connection(url, username, password, maxHostConnections).getHttpClient();
	}
	
	/**
	 * Gera uma cadeia de caracteres aleatória para realização de testes
	 * 
	 * @param length tamanho da cadeia
	 * @return string contendo caracteres hexadecimais [0-9A-F]
	 */
	private static String generateRandomString(int length) {
		byte[] buffer = new byte[length];
		new Random().nextBytes(buffer);
		return new String(Hex.encodeHex(buffer));
	}
}
