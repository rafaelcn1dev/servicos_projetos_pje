package br.jus.cnj.pje.storage.jcr.common;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * Classe de configuração HttpClient
 * 
 * @author leoneparise
 */
public class Connection {
	private final String url;
	private final String username;
	private final String password;
	private final int maxHostConn;
	
	public Connection(String url, String username, String password, int maxHostConn) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
		this.maxHostConn = maxHostConn;
	}
	
	public HttpClient getHttpClient() {
		// Configuração do Host
		HostConfiguration host = new HostConfiguration();
		host.setHost(url);
		// Configuração do Connection Manager
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setMaxConnectionsPerHost(host, maxHostConn);
		HttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
		manager.setParams(params);
		// Configuração do HttpClient 
		Credentials cred = new UsernamePasswordCredentials(username, password);
		HttpClient client = new HttpClient(manager);
		client.getState().setCredentials(AuthScope.ANY, cred);
		client.setHostConfiguration(host);
		client.getParams().setAuthenticationPreemptive(true);
		client.getParams().setParameter(HttpMethodParams.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		
		return client;
	}
}
