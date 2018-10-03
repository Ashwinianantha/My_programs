package com.acis.itsm.issuetabpanels;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueTabPanel;
import com.atlassian.jira.plugin.issuetabpanel.IssueAction;
import com.atlassian.jira.user.ApplicationUser;

public class CollaborationTabPanel extends AbstractIssueTabPanel {
	private final static Logger log = LoggerFactory.getLogger(CollaborationTabPanel.class);

	@Override
	public List<IssueAction> getActions(Issue issue, ApplicationUser user) {
		// to be implemented
		return Collections.emptyList();
	}

	@Override
	public boolean showPanel(Issue issue, ApplicationUser user) {
		if (issue.getProjectObject().getKey().equals("IHD")) {
			return true;
		} else {
			return false;
		}
	}

}
