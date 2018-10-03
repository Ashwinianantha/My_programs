package com.acis.itsm.rest;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversationResponseBean {

	@JsonProperty
	private String url;
	@JsonProperty
	private String conversationId;
	@JsonProperty
	private String cloudId;

	public ConversationResponseBean(String url, String conversationId, String cloudId) {
		super();
		this.url = url;
		this.conversationId = conversationId;
		this.cloudId = cloudId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getCloudId() {
		return cloudId;
	}

	public void setCloudId(String cloudId) {
		this.cloudId = cloudId;
	}

}
