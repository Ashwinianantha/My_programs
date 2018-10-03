package com.myplugin.conditions;

import java.util.Collections;
import java.util.Map;

import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginConditionFactory;
import com.opensymphony.workflow.loader.AbstractDescriptor;

public class AssigneConditionFactory extends AbstractWorkflowPluginFactory implements WorkflowPluginConditionFactory{

	@Override
	public Map<String, ?> getDescriptorParams(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return Collections.EMPTY_MAP;
	}

	@Override
	protected void getVelocityParamsForEdit(Map<String, Object> arg0, AbstractDescriptor arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getVelocityParamsForInput(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getVelocityParamsForView(Map<String, Object> arg0, AbstractDescriptor arg1) {
		// TODO Auto-generated method stub
		
	}

}
