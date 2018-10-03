package com.acis.assets.mgmt.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ofbiz.core.entity.GenericEntityException;
import org.springframework.beans.factory.annotation.Autowired;

import com.acis.assets.mgmt.AssetFieldBean;
import com.acis.assets.mgmt.ao.AssetsConfigService;
import com.acis.assets.mgmt.ao.AssetsCustomFieldsEntity;
import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.config.managedconfiguration.ConfigurationItemAccessLevel;
import com.atlassian.jira.config.managedconfiguration.ManagedConfigurationItem;
import com.atlassian.jira.config.managedconfiguration.ManagedConfigurationItemBuilder;
import com.atlassian.jira.config.managedconfiguration.ManagedConfigurationItemService;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.GlobalIssueContext;
import com.atlassian.jira.issue.context.JiraContextNode;
import com.atlassian.jira.issue.customfields.CustomFieldSearcher;
import com.atlassian.jira.issue.customfields.CustomFieldType;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

@Path("/fields")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class AssetsFieldsConfigResource {
	CacheControl cacheControl;
	@ComponentImport
	private final CustomFieldManager customFieldManager;
	@ComponentImport
	private final ManagedConfigurationItemService managedConfigurationItemService;
	@ComponentImport
	private final ActiveObjects ao;
	@Autowired
	private final AssetsConfigService configService;

	public AssetsFieldsConfigResource(CustomFieldManager customFieldManager, ManagedConfigurationItemService managedConfigurationItemService,
			AssetsConfigService configService, ActiveObjects ao) {
		super();
		this.customFieldManager = customFieldManager;
		this.managedConfigurationItemService = managedConfigurationItemService;
		this.configService = configService;
		this.ao = ao;
		CacheControl cacheControl = new CacheControl();
		cacheControl.setNoCache(true);
	}

	@GET
	@Path("/all")
	public Response getAllFieldDetails() {
		System.out.println("---------Inside getAllFieldDetails---------");
		List<AssetsCustomFieldsEntity> allFields = configService.getAllCustomFieldsId(ao);
		
		final List<AssetFieldBean> beans = new ArrayList<AssetFieldBean>();
		if (allFields != null) {
			for (AssetsCustomFieldsEntity entity : allFields) {
				CustomField cf = customFieldManager.getCustomFieldObject(entity.getCustomFieldId());
				if (cf != null) {
					AssetFieldBean bean = new AssetFieldBean();
					bean.setId(entity.getID());
					bean.setDescription(cf.getDescription());
					bean.setName(cf.getFieldName());
					bean.setType(cf.getCustomFieldType().getName());
//					System.out.println("Custom Field Type: " + cf.getCustomFieldType().getName());
					beans.add(bean);

				} else {
					System.out.println("No customfield found with Id= " + entity.getCustomFieldId());
				}
			}
		}

		return Response.ok(beans).cacheControl(cacheControl).build();
	}

	@AnonymousAllowed
	@GET
	@Path("{id}")
	public Response getFieldDetails(@PathParam("id") final String id) {
		System.out.println("---------Inside getFieldDetails---------");
		return null;
	}

	@PUT
	@Path("{id}")
	public Response updateFieldDetails(@PathParam("id") final String id, final AssetFieldBean bean) {
		System.out.println("---------Inside getUpdateFieldDetails---------");
		return null;
	}

	@POST
	public Response createField(final AssetFieldBean bean) {
		System.out.println("---------Inside Create Field---------");

		int id = bean.getId();
		String name = bean.getName();
		String description = bean.getDescription();
		String type = bean.getType();
		String fieldValues = bean.getFieldValues();

		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
		System.out.println("Description: " + description);
		System.out.println("Type: " + type);
		System.out.println("FieldValues: " + fieldValues);
		if (name != null && type != null) {
			CustomField field = createCustomField(bean);
			AssetsCustomFieldsEntity record = configService.createCustomFieldId(ao, field.getId());
			bean.setId(record.getID());
		} else {
			return Response.status(Response.Status.BAD_REQUEST).cacheControl(cacheControl).build();
		}

		return Response.ok(bean).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteField(@PathParam("id") final String id) {
		System.out.println("---------Inside delete Field---------");
		System.out.println();
		return null;
	}

	public CustomField createCustomField(AssetFieldBean bean) {

		String name = bean.getName();
		String description = bean.getDescription();
		String type = bean.getType();
		String fieldValues = bean.getFieldValues();
		CustomField newField = null;
		// Create a list of issue types for which the custom field needs to be available
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		issueTypes.add(null);

		// Create a list of project contexts for which the custom field needs to be
		// available
		List<JiraContextNode> contexts = new ArrayList<JiraContextNode>();
		contexts.add(GlobalIssueContext.getInstance());
		String fieldType = "com.atlassian.jira.plugin.system.customfieldtypes:" + type.trim() + "field";
		System.out.println("field Type String: " + fieldType);
		CustomFieldType fieldTypeValue = customFieldManager.getCustomFieldType(fieldType);
		System.out.println("fieldType: " + fieldTypeValue);
		CustomFieldSearcher fieldSearcher = customFieldManager
				.getCustomFieldSearcher("com.atlassian.jira.plugin.system.customfieldtypes:" + type.trim() + "searcher");
//		System.out.println("fieldSearcher: " + fieldSearcher);
		try {
			newField = customFieldManager.createCustomField(name, description, fieldTypeValue, fieldSearcher, contexts, issueTypes);
			System.out.println("new field: " + newField);
			if (newField != null) {
				System.out.println("New Field has create Successfully: " + newField.getName());

				ManagedConfigurationItem managedField = managedConfigurationItemService.getManagedCustomField(newField);
				if (managedField != null) {
					ManagedConfigurationItemBuilder builder = ManagedConfigurationItemBuilder.builder(managedField);
					builder.setManaged(true);
					builder.setConfigurationItemAccessLevel(ConfigurationItemAccessLevel.LOCKED);
					managedField = builder.build();
					managedConfigurationItemService.updateManagedConfigurationItem(managedField);
				}
			}
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}

		return newField;
	}
}
