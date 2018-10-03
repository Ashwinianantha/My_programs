package com.acis.itsm.webpanels;

import java.util.Map;

import com.atlassian.jira.issue.Issue;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.Condition;

public class CollaborationSectionDisplayCondition implements Condition {
	private static final String PROJECT_KEY = "projectkey";

	private String projectKey;

	@Override
	public void init(Map<String, String> params) throws PluginParseException {
		projectKey = params.get(PROJECT_KEY);

	}

	@Override
	public boolean shouldDisplay(Map<String, Object> context) {
//		JiraHelper helper = (JiraHelper) context.get("helper");
		Issue currentIssue = (Issue) context.get("issue");
		boolean shouldDisplay = false;
		if (currentIssue == null) {
			shouldDisplay = false;
		} else if (currentIssue.getProjectObject().getKey().equals(projectKey)) {
			shouldDisplay = true;
		}

		return shouldDisplay;
	}

}
