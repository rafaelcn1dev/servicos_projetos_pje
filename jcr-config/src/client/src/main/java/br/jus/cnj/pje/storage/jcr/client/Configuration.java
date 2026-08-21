package br.jus.cnj.pje.storage.jcr.client;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.jus.cnj.pje.extensao.servico.ParametroService;

/**
 * Configuracao do JCR Client
 * 
 * @author leoneparise
 */
@Name("JCRConfiguration")
public class Configuration {
	private String username;
	private String password;
	private String url;
	private int chunkSize;
	private int hostMaxConn;
	private static Properties properties = new Properties();
	private static String PARAMETRO_PROPERTIES = "br.jus.cnj.pje.jcr-storage.configuration";

	@In(required = false)
	private ParametroService parametroService;

	public Configuration() {
		init();
	}

	@Create
	public void init() {
		properties = new Properties();
		try {
			if (System.getProperty(PARAMETRO_PROPERTIES) != null && System.getProperty(PARAMETRO_PROPERTIES).trim().length() > 0) {
				FileInputStream fis = new FileInputStream(System.getProperty(PARAMETRO_PROPERTIES));
				properties.load(fis);
				fis.close();
			} else if (existeProperties()) {
				String path = Configuration.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				FileInputStream fis = new FileInputStream(getPath(path) + "/jcr-storage.properties");
				properties.load(fis);
				fis.close();
			} else {
				properties.load(Configuration.class.getResourceAsStream("/jcr-storage.properties"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		loadPropertiesFromParameters(properties);
		this.username = properties.getProperty("jcr.username");
		this.password = properties.getProperty("jcr.password");
		this.url = properties.getProperty("jcr.url");
		this.chunkSize = Integer.parseInt(properties.getProperty("jcr.chunkSize"));
		this.hostMaxConn = Integer.parseInt(properties.getProperty("jcr.hostMaxConn"));
	}
	
	private void loadPropertiesFromParameters(Properties props){
		if(parametroService == null){
			return;
		}
		String aux = parametroService.valueOf("jcr.username"); 
		if(aux != null && !aux.isEmpty()){
			properties.put("jcr.username", aux);
		}
		aux = parametroService.valueOf("jcr.password");
		if(aux != null && !aux.isEmpty()){
			properties.put("jcr.password", aux);
		}
		aux = parametroService.valueOf("jcr.url");
		if(aux != null && !aux.isEmpty()){
			properties.put("jcr.url", aux);
		}
		aux = parametroService.valueOf("jcr.chunkSize");
		if(aux != null && !aux.isEmpty()){
			properties.put("jcr.chunkSize", aux);
		}
		aux = parametroService.valueOf("jcr.hostMaxConn");
		if(aux != null && !aux.isEmpty()){
			properties.put("jcr.hostMaxConn", aux);
		}
	}

	/**
	 * Obtém o usuário do repositório JCR
	 * 
	 * @return usuário JCR
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Obtém a senha do repositório JCR
	 * 
	 * @return senha JCR
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Obtém a url do repositório JCR
	 * 
	 * @return url do repositório JCr
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Obtém o tamanho do chunk a ser enviado para o servidor
	 * 
	 * @return tamanho do chunk
	 */
	public int getChunkSize() {
		return chunkSize;
	}

	/**
	 * Obtém o numero máximo de conexões por host
	 * 
	 * @return numero máximo de conexões por host
	 */
	public int getHostMaxConn() {
		return hostMaxConn;
	}

	private static boolean existeProperties() {
		String path = Configuration.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (path.contains("jar")) {
			File file = new File(getPath(path) + "/jcr-storage.properties");
			return file.exists();
		}
		return false;
	}

	private static String getPath(String path) {
		while (true) {
			path = path.substring(path.indexOf("/"), path.lastIndexOf("/"));
			File file = new File(path);
			if (file.isDirectory()) {
				return path;
			}
		}
	}
}
