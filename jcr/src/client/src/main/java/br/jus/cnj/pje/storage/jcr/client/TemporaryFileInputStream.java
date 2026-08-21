package br.jus.cnj.pje.storage.jcr.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

import br.jus.cnj.pje.extensao.StorageException;

/**
 * InputStream responsável por remover o arquivo temporário após
 * a chamada do método {@link TemporaryFileInputStream#close()}
 * 
 * @author leoneparise
 */
public class TemporaryFileInputStream extends FileInputStream {
	private File file;
	
	public TemporaryFileInputStream(File file) throws FileNotFoundException {
		super(file);
		this.file = file;
	}

	public TemporaryFileInputStream(String name) throws FileNotFoundException {
		this(new File(name));
	}
	
	@Override
	public void close() throws IOException {
		try {
			super.close();
		} finally {
			file.delete();
		}
	}
	
	/**
	 * Cria arquivo temporário para armazenar os dados do Stream
	 * 
	 * @param stream stream de dados
	 * @return arquivo temporário contendo os dados
	 * @throws StorageException
	 */
	public static File createFile(InputStream stream) throws IOException {
		File file = File.createTempFile(UUID.randomUUID().toString(), "bin");
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			IOUtils.copyLarge(stream, out);
			return file;
		} finally {
			if(out != null) out.close();
		}
	}
	
	/**
	 * Cria arquivo temporário para armazenar os dados do Stream 
	 * e retorna o stream do arquivo
	 * 
	 * @param stream stream de dados
	 * @return arquivo temporário contendo os dados
	 * @throws StorageException
	 */
	public static InputStream createStream(InputStream stream) throws IOException {
		return new TemporaryFileInputStream(createFile(stream));
	}
}
