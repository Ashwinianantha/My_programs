package com.acis.itsm.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.acis.itsm.utils.Constants;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.type.EventDispatchOption;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.user.ApplicationUser;

@Path("/stride")
public class StrideRestResource {

	@POST
	@Path("/createConversation")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createConversation(final ConversationBean input) {
		System.out.println("###createConversation##########");
		/*
		 * if (StringUtils.isEmpty(input.getName()) ||
		 * StringUtils.isEmpty(input.getTopic()) ||
		 * StringUtils.isEmpty(input.getIssueKey())) { return
		 * Response.status(401).entity("Input parameters are missing.").build(); }
		 */
		System.out.println("issue key: " + input.getIssueKey());
		if (StringUtils.isEmpty(input.getIssueKey())) {
			return Response.status(401).entity("Input parameters are missing.").build();
		}
		String response = null;
		IssueManager imgr = ComponentAccessor.getIssueManager();

		MutableIssue issue = imgr.getIssueByCurrentKey(input.getIssueKey().trim());
		if (issue != null) {
			/*
			 * String conversationId = StrideClient.createConverstion(Constants.cloud_id,
			 * input.getName(), input.getTopic());
			 */
			String conversationId = StrideClient.createConverstion(Constants.cloud_id, issue.getKey(), issue.getSummary());
			System.out.println("conversationId: " + conversationId);
			if (conversationId != null) {
				ConversationResponseBean bean = new ConversationResponseBean(Constants.stride_url, conversationId, Constants.cloud_id);

				CustomField cf = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(Constants.cf_converstion_id);
				if (cf != null) {
					issue.setCustomFieldValue(cf, conversationId);
					ApplicationUser currentUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser();
					Issue updated = imgr.updateIssue(currentUser, issue, EventDispatchOption.ISSUE_UPDATED, false);
				} else {
					System.out.println("Customf field not found.");
				}

				try {
					ObjectMapper mapper = new ObjectMapper();
					response = mapper.writeValueAsString(bean);
				} catch (JsonGenerationException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				return Response.status(400).entity("Something wrong!, Refer the jira log").build();
			}
		}
		return Response.ok(response, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/getConversationDeatils")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getConversationDeatils(@QueryParam("conversationId") String conversationId) {
		System.out.println("###getConversationDeatils##########");
		if (StringUtils.isEmpty(conversationId)) {
			return Response.status(401).entity("Input parameters are missing.").build();
		}

		String conversation = StrideClient.getConversation(Constants.cloud_id, conversationId);
		System.out.println("conversationId: " + conversationId);
		if (conversationId != null) {
			return Response.ok(conversation.toString(), MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(400).entity("Something wrong!, Refer the jira log").build();
		}

	}

	public void updateCustomField(String issueKey, String cfName, String fieldValue) {

	}
}
