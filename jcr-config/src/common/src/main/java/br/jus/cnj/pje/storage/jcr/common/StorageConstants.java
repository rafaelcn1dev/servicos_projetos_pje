package br.jus.cnj.pje.storage.jcr.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Constantes utilziadas pelo Storage JCR
 * 
 * @author leoneparise
 */
public class StorageConstants {
	/* HEADERS */
	public static final String HEADER_CONTENT_TYPE   = "Content-Type";
	public static final String HEADER_CONTENT_RANGE  = "Content-Range";
	public static final String HEADER_CONTENT_LENGTH = "Content-Length";
	public static final String HEADER_LAST_MODIFIED  = "Last-Modified";
	public static final String HEADER_NODE_PATH      = "Node-Path";
	public static final String HEADER_IS_TEMPORARY   = "Is-Temporary";
	public static final String HEADER_CONTENT_HASH   = "Content-Hash";
	
	public static final String DEFAULT_MIMETYPE = "application/octet-stream";
	
	/* HTTP METHODS */
	public static final String METHOD_PUT  = "PUT";
	public static final String METHOD_POST = "POST";
	
	/* JCR CONTENT EXTENSIONS */
	public static final String MIX_RESOURCE_EXT = "mix:resourceExtensions";
	public static final String JCR_TEMPORARY    = "jcr:temporary";
	public static final String JCR_CONTENT_SIZE = "jcr:contentSize";
	
	
	public static DateFormat lastModifiedFormat() {
		return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
	}
}
