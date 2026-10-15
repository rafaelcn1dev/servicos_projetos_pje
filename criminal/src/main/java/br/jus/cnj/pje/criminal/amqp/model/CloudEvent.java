package br.jus.cnj.pje.criminal.amqp.model;

import java.io.Serializable;
import java.util.Map;

public class CloudEvent implements Serializable{

	private static final long serialVersionUID = 1L;

	private String uuid;
	private String appName;
	private String appVersion;
	private String routingKey;
	private Long timestamp;
	private Object payload;
	private String payloadHash;
	private Map<String, String> links;
			
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getAppVersion() {
		return appVersion;
	}
	
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getPayloadHash() {
		return payloadHash;
	}

	public void setPayloadHash(String payloadHash) {
		this.payloadHash = payloadHash;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public Map<String, String> getLinks() {
		return links;
	}

	public void setLinks(Map<String, String> links) {
		this.links = links;
	}
	
	@Override
	public String toString() {
		return this.getAppName() + " - " + this.getAppVersion() + ": "+ this.getRoutingKey();
	}

}