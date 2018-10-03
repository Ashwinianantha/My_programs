package com.myplugin.conditions;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.atlassian.core.util.map.EasyMap;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginConditionFactory;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.opensymphony.workflow.loader.AbstractDescriptor;
import com.opensymphony.workflow.loader.ConditionDescriptor;

public class RoleConditionFactory extends AbstractWorkflowPluginFactory implements WorkflowPluginConditionFactory {

	private static final String ROLE_NAME = "role";
	private static final String ROLES = "roles";
	private static final String NOT_DEFINED = "Not Defined";
	

	public final ProjectRoleManager projectRoleManager = ComponentAccessor.getComponentOfType(ProjectRoleManager.class);


	@Override
	protected void getVelocityParamsForEdit(Map velocityParams, AbstractDescriptor descriptor) {
		velocityParams.put(ROLE_NAME, getRoleName(descriptor));
		velocityParams.put(ROLES, getProjectRoles());
	}

	@Override
	protected void getVelocityParamsForInput(Map velocityParams) {
		velocityParams.put(ROLES, getProjectRoles());
	}

	@Override
	protected void getVelocityParamsForView(Map velocityParams, AbstractDescriptor descriptor) {
		velocityParams.put(ROLE_NAME, getRoleName(descriptor));
	}

	@SuppressWarnings("unchecked")
	public Map getDescriptorParams(Map conditionParams) {
		if (conditionParams != null && conditionParams.containsKey(ROLE_NAME)) {
			return EasyMap.build(ROLE_NAME, extractSingleParam(conditionParams, ROLE_NAME));
		}

		// Create a "hard coded" parameter
		return EasyMap.build();
	}

	private String getRoleName(AbstractDescriptor descriptor) {

		if (!(descriptor instanceof ConditionDescriptor)) {
			throw new IllegalArgumentException("Descriptor must be a ConditionDescriptor.");
		}

		ConditionDescriptor conditionDescriptor = (ConditionDescriptor) descriptor;

		String role = (String) conditionDescriptor.getArgs().get(ROLE_NAME);
		if (role != null && role.trim().length() > 0)
			return role;
		else
			return NOT_DEFINED;
	}

	private Collection<ProjectRole> getProjectRoles() {
		Collection<ProjectRole> projRoles = projectRoleManager.getProjectRoles();
		return Collections.unmodifiableCollection(projRoles);
		// Get list of project Roles
	}

}