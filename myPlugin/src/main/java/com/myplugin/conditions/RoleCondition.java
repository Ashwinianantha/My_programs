package com.myplugin.conditions;

import java.util.Map;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.workflow.WorkflowException;
import com.atlassian.jira.workflow.condition.AbstractJiraCondition;
import com.opensymphony.module.propertyset.PropertySet;

public class RoleCondition extends AbstractJiraCondition{
	
	public final ProjectRoleManager projectRoleManager = ComponentAccessor.getComponentOfType(ProjectRoleManager.class);
	private static final String ROLE_NAME = "Role";    

    public boolean passesCondition(Map transientVars, Map args, PropertySet ps) throws WorkflowException {
        
        Issue issue = getIssue(transientVars);      
        ApplicationUser user = getCaller(transientVars, args);
        
        System.out.println("user name: "+ user.getDisplayName());
        Project project = issue.getProjectObject();
        System.out.println("project name: "+project.getName());
        String roleName = (String) args.get(ROLE_NAME);
       System.out.println("role name: "+roleName);
        //ProjectRoleManager projectRoleManager=ComponentAccessor.getComponent(ProjectRoleManager.class);
        return projectRoleManager.isUserInProjectRole(user, projectRoleManager.getProjectRole(roleName), project);
  
    }


}