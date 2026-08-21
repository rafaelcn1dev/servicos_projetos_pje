package br.jus.cnj.pje.storage.jcr.client.test.integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

import junit.framework.Assert;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.junit.BeforeClass;
import org.junit.Test;

import br.jus.cnj.pje.extensao.StorageException;
import br.jus.cnj.pje.storage.jcr.client.Configuration;
import br.jus.cnj.pje.storage.jcr.client.JCRStorageService;
import br.jus.cnj.pje.storage.jcr.common.Connection;
import br.jus.cnj.pje.storage.jcr.common.HashProvider;
import br.jus.cnj.pje.storage.jcr.common.ResourceUtils;

public class ClientTestCase {
	private static Configuration config;
	private static JCRStorageService storage;
	
	private static final int FILE_SIZE = 5767169; // 5.767.169 B = 5.5MB + 1 byte
	
	@Test
	public void testPersist() throws IOException, StorageException {
		File tempFile = generateRandomData(FILE_SIZE);
		InputStream stream = new FileInputStream(tempFile);
		String hash = storage.persist(stream);
		stream.close();
		
		long remoteSize = ResourceUtils.getResourceSize(getResourceURI(hash), getHttpClient());
		Assert.assertEquals(tempFile.length(), remoteSize);
	}
	
	@Test
	public void testRetrieve() throws IOException, StorageException {
		// Criar um arquivo temporário e envia ao servidor
		File tempFile = generateRandomData(FILE_SIZE);
		InputStream stream = new FileInputStream(tempFile);
		String hash = storage.persist(stream);
		stream.close();
		
		// Obtém o arquivo do servidor
		InputStream outFile = storage.retrieve(hash);

		// Verifica se o hash é o mesmo do arquivo enviado
		Assert.assertEquals(hash, HashProvider.hash(outFile));
		
		// Fecha o arquivo do servidor
		outFile.close();
	}
	
	@BeforeClass
	public static void setup() {
		config = new Configuration();
		storage = new JCRStorageService();
		storage.setConfiguration(config);
	}
	
	/**
	 * Cria um arquivo temporário no disco
	 * 
	 * @param size tamanho do arquivo temporário
	 * @return descritor do arquivo temporário
	 * @throws IOException
	 */
	private File generateRandomData(int size) throws IOException {
		// Carrega um buffer com numeros aleatorios
		byte[] buffer = new byte[size];
		new Random().nextBytes(buffer);
		
		// Criar o arquivo temporário e escreve os dados no mesmo
		File file = File.createTempFile(UUID.randomUUID().toString(), "bin");
		FileWriter writer = new FileWriter(file);
		writer.write(Hex.encodeHex(buffer));
		writer.close();
		
		return file;
	}
	
	/**
	 * Obtém o endereço http do recurso a ser manipulado
	 * 
	 * @return endereço http do recurso
	 */
	public static String getResourceURI(String hash) {
		return String.format("%s/%s", config.getUrl(), hash);
	}
	
	/**
	 * Obtém o cliente http para realização dos testes
	 *  
	 * @return cliente http
	 */
	private HttpClient getHttpClient() {
		return new Connection(config.getUrl(), config.getUsername(), config.getPassword(), config.getHostMaxConn()).getHttpClient();
	}
}
