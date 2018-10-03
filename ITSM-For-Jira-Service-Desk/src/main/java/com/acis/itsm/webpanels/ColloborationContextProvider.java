package com.acis.itsm.webpanels;

import java.util.HashMap;
import java.util.Map;

import com.acis.itsm.utils.Constants;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.plugin.webfragment.contextproviders.AbstractJiraContextProvider;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;

public class ColloborationContextProvider extends AbstractJiraContextProvider {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getContextMap(ApplicationUser user, JiraHelper helper) {
		Map contextMap = new HashMap();
		Issue currentIssue = (Issue) helper.getContextParams().get("issue");
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		CustomField cfConverstionId = cfm.getCustomFieldObjectByName(Constants.cf_converstion_id);

		if (cfConverstionId != null) {
			String cfValue = (String) currentIssue.getCustomFieldValue(cfConverstionId);

			/*
			 * Map<String, String> roomDetails = new HashMap<String, String>();
			 * roomDetails.put("roomId", cfValue);
			 */

			if (cfValue != null) {
				String roomUrl = Constants.stride_app_url + "/" + Constants.cloud_id + "/chat/" + cfValue;
				contextMap.put("roomUrl", roomUrl);
			} else {
				contextMap.put("roomUrl", null);
			}
		} else {
			contextMap.put("roomUrl", null);
		}

		contextMap.put("meetingId", null);

		return contextMap;
	}

}
