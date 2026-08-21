package br.jus.cnj.pje.storage.jcr.common;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representação do cabeçalho {@code Content-Range} do protocolo Http
 * 
 * @author leoneparise
 */
public class Range {
	private final long start;
	private final long end;
	private final long total;
	private final  int length;
	
	/**
	 * Cria um novo objeto Range
	 * 
	 * @param start offset do arquivo
	 * @param length tamanho do range
	 * @param total tamanho total do arquivo
	 */
	public Range(long start, int length, long total) {
		super();
		this.start = start;
		this.end = start + length;
		this.length = length;
		this.total = total;
	}
	
	/**
	 * Obtem o início do range
	 * 
	 * @return início do range
	 */
	public long getStart() {
		return start;
	}
	
	/**
	 * Obtém o fim do range
	 * 
	 * @return fim do range
	 */
	public long getEnd() {
		return end;
	}
	
	/**
	 * Obtém o tamanho total da entidade
	 * 
	 * @return tamanho total da entidade
	 */
	public long getTotal() {
		return total;
	}
	
	/**
	 * Obtém o tamanho do range
	 * 
	 * @return tamanho do range
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Verifica se o Range é o último
	 * 
	 * @return {@code true} caso seja o último {@code false} caso contrário
	 */
	public boolean isLast() {
		return end == total;
	}
	
	/**
	 * String utilizada no cabeçalho Content-Range
	 * 
	 * @return string a ser utilizada pelo cabeçalho
	 */
	public String toContentRange() {
		return String.format("bytes %d-%d/%d", start, end, total);
	}

	/**
	 * Realiza o parse do cabeçalho Content-Range. O cabecalho é da forma {@code bytes <start>-<end>/<total>}
	 * onde {@code <start>} e {@code <end>} representam o <b>primeiro</b> e <b>último</b> bytes a enviados 
	 * na requisição e {@code <total>} representa o tamanho total do arquivo.
	 * 
	 * @param str string do cabeçalho
	 * @return Range correspondente
	 * 
	 * @throws ParseException caso ocorra algum erro na realização
	 * do parse
	 */
	public static Range parse(String str) throws ParseException {
		if(str == null)
			throw new ParseException("O parametro não deve ser nulo", 0);
		
		Pattern p = Pattern.compile("bytes (\\d+)-(\\d+)/(\\d+)");
		Matcher m = p.matcher(str);
		
		if(!m.matches())
			throw new ParseException("Formato inválido", 0);
		
		try {
			long start = Long.parseLong(m.group(1));
			long end = Long.parseLong(m.group(2));
			long total = Long.parseLong(m.group(3));
		
		return new Range(start, (int)(end - start), total);
		} catch (IndexOutOfBoundsException e) {
			throw new ParseException("Formato inválido", 0);
		} 
	}
	
	@Override
	public String toString() {
		return String.format("Range {start = %d, end = %d, total = %d}", start, end, total);
	}
}
