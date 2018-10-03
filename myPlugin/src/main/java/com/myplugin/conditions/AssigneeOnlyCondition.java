package com.myplugin.conditions;

import java.util.Map;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.workflow.condition.AbstractJiraCondition;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.WorkflowException;

public class AssigneeOnlyCondition extends AbstractJiraCondition {

	@Override
	public boolean passesCondition(Map transientVars, Map args, PropertySet ps) throws WorkflowException {
		Issue issue = getIssue(transientVars);
		ApplicationUser user = getCaller(transientVars, args);
		ApplicationUser assignee = issue.getAssignee();
System.out.println("current user"+user.getDirectoryUser().getName());
System.out.println("assignee user"+assignee.getDirectoryUser().getName());
		if (assignee!=null && user.getDirectoryUser().getName().equals(assignee.getDirectoryUser().getName()))
			return true;
		else
			return false;
	}

}
