package com.myplugin.validators;

import java.util.Map;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.InvalidInputException;
import com.opensymphony.workflow.Validator;


public class FieldValidator implements Validator {
		private final CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();

	private static final String FIELD_NAME = "field";

	
	
	public void validate(Map transientVars, Map args, PropertySet ps) throws InvalidInputException {
		Issue issue = (Issue) transientVars.get("issue");
		String field = (String) args.get(FIELD_NAME);	
		@SuppressWarnings("deprecation")
		CustomField customField = customFieldManager.getCustomFieldObjectByName(field);

		
		if (customField!=null){
			//Check if the custom field value is NULL
			if (issue.getCustomFieldValue(customField) == null){
				throw new InvalidInputException("The field:"+field+" is required!");
			}
		}
	}
}
