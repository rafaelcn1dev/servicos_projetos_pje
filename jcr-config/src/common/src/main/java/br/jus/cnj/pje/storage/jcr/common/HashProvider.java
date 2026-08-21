package br.jus.cnj.pje.storage.jcr.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;

/**
 * Provedor de hash para inputstream e array de bytes
 * 
 * @author leoneparise
 */
public class HashProvider {
	public static String ALGORITM = "SHA-1";
	
	private final MessageDigest digest;
	private final DigestInputStream dis;
	
	/**
	 * Constrói um HashProvider para processar um InputStream.
	 * 
	 * @param input InputStream a ser processado
	 * @param algoritm algoritmo de hash
	 */
	public HashProvider(InputStream input, String algoritm) {
		if(input == null)
			throw new IllegalArgumentException("Parameter input can not be null");
		
		try {
			this.digest = MessageDigest.getInstance(algoritm);
			this.dis = new DigestInputStream(input, digest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Obtém o inputstream associado ao HashProvider. O inputstream deve ser utilizado
	 * para que o cálculo seja realizado.
	 * 
	 * @return inputstream associado
	 */
	public InputStream getInputStream() {
		if(dis == null)
			throw new IllegalStateException("Null input stream");
		
		return dis;
	}
	
	/**
	 * Calcula o hash em hexadecimal do InputStream
	 * 
	 * @return hash hexadecimal
	 */
	public String hash() {
		return new String(Hex.encodeHex(digest.digest()));
	}
	
	/**
	 * Calcula o hash em hexadecimal de um array de bytes
	 *  
	 * @param barray array de bytes
	 * 
	 * @return hash hexadecimal
	 */
	public static String hash(byte[] barray) {
		return hash(barray, ALGORITM);
	}
	
	/**
	 * Calcula o hash em hexadecimal de um array de bytes
	 *  
	 * @param barray array de bytes
	 * @param algoritm algoritmo
	 * 
	 * @return hash hexadecimal
	 */
	public static String hash(byte[] barray, String algoritm) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algoritm);
			digest.reset();
			return new String(Hex.encodeHex(digest.digest(barray)));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Calcula o hash em hexadecimal de um inputstream
	 * 
	 * @param stream stream de bytes
	 * 
	 * @return hash hexadecimal
	 */
	public static String hash(InputStream stream) {
		return hash(stream, ALGORITM);
	}
	
	/**
	 * Calcula o hash em hexadecimal de um inputstream
	 * 
	 * @param stream stream de bytes
	 * @param algoritm algoritmo
	 * 
	 * @return hash hexadecimal
	 */
	public static String hash(InputStream stream, String algoritm) {
		try {
			HashProvider provider = new HashProvider(stream, algoritm);
			IOUtils.copyLarge(provider.getInputStream(), new NullOutputStream());
			return provider.hash();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 *Calcula o hash hexadecimal de um arquivo
	 * 
	 * @param file arquivo
	 * @return hash hexadecimal
	 */
	public static String hash(File file) {
		return hash(file, ALGORITM);
	}
	
	/**
	 * Calcula o hash exadecimal de um arquivo
	 * 
	 * @param file arquivo
	 * @param algoritm algoritmo de hash
	 * @return hash hexadecimal
	 */
	public static String hash(File file, String algoritm) {
		InputStream stream = null;
		try {
			stream = new FileInputStream(file);
			return hash(stream, algoritm);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}
}
