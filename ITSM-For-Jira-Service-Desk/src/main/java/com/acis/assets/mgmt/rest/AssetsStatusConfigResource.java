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

import org.springframework.beans.factory.annotation.Autowired;

import com.acis.assets.mgmt.AssetFieldBean;
import com.acis.assets.mgmt.AssetStatusBean;
import com.acis.assets.mgmt.ao.AssetsConfigService;
import com.acis.assets.mgmt.ao.AssetsStatusEntity;
import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.StatusCategoryManager;
import com.atlassian.jira.config.StatusManager;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.issue.status.category.StatusCategory;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

@Path("/status")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class AssetsStatusConfigResource {
	CacheControl cacheControl;
	@ComponentImport
	private final CustomFieldManager customFieldManager;
	@ComponentImport
	private final StatusManager statusManager;
	@ComponentImport
	private final ActiveObjects ao;
	@Autowired
	private final AssetsConfigService configService;

	public AssetsStatusConfigResource(CustomFieldManager customFieldManager, StatusManager statusManager, AssetsConfigService configService,
			ActiveObjects ao) {
		super();
		this.customFieldManager = customFieldManager;
		this.configService = configService;
		this.statusManager = statusManager;
		this.ao = ao;
		CacheControl cacheControl = new CacheControl();
		cacheControl.setNoCache(true);
	}

	@GET
	@Path("/all")
	public Response getAllStatusDetails() {
		System.out.println("---------Inside getAllStatusDetails---------");
		List<AssetsStatusEntity> allStatusIds = configService.getAllStatusIds(ao);
//		System.out.println("allFields: " + allStatusIds);
		final List<AssetStatusBean> beans = new ArrayList<AssetStatusBean>();
		if (allStatusIds != null) {
			for (AssetsStatusEntity entity : allStatusIds) {
				Status status = statusManager.getStatus(entity.getStatusId());
				if (status != null) {
					AssetStatusBean bean = new AssetStatusBean();
					bean.setId(entity.getID());
					bean.setDescription(status.getDescription());
					bean.setName(status.getName());
					bean.setCategory(status.getStatusCategory().getName());
					beans.add(bean);
				} else {
					System.out.println("No customfield found with Id= " + entity.getStatusId());
				}
			}
		}

		return Response.ok(beans).cacheControl(cacheControl).build();
	}

	@POST
	public Response createStatus(final AssetStatusBean bean) {
		System.out.println("---------Inside Status Creation---------");

		int id = bean.getId();
		String name = bean.getName();
		String description = bean.getDescription();
		String category = bean.getCategory();

		/*
		 * System.out.println("ID: " + id); System.out.println("Name: " + name);
		 * System.out.println("Description: " + description);
		 * System.out.println("Category: " + category);
		 */

		if (name != null && category != null) {
			Status status = createStatus(name, description, category);
			if (status != null) {
				AssetsStatusEntity record = configService.createStatusId(ao, status.getId());
				bean.setId(record.getID());
			} else {
				return Response.status(Response.Status.BAD_REQUEST).cacheControl(cacheControl).build();
			}

		} else {
			return Response.status(Response.Status.BAD_REQUEST).cacheControl(cacheControl).build();
		}

		return Response.ok(bean).build();
	}

	@AnonymousAllowed
	@GET
	@Path("{id}")
	public Response getStatusDetails(@PathParam("id") final String id) {
		System.out.println("---------Inside getStatusDetails---------");

		AssetStatusBean bean = new AssetStatusBean();
		if (id != null) {
			List<AssetsStatusEntity> records = configService.getStatusId(ao, id);
			if (records != null) {

				String statusId = null;
				int columnId = Integer.parseInt(id);
				if (records.size() == 1) {
					statusId = records.get(0).getStatusId();

				} else if (records.size() > 1) {
					for (AssetsStatusEntity entity : records) {
						if (entity.getID() == Integer.parseInt(id)) {
							statusId = entity.getStatusId();
							break;
						}
					}
				}

				if (statusId != null) {
					Status status = statusManager.getStatus(statusId);
					if (status != null) {
						bean.setId(columnId);
						bean.setName(status.getName());
						bean.setDescription(status.getDescription());
						bean.setCategory(status.getStatusCategory().getName());
					} else {
						System.out.println("No status Found with status Id= " + statusId);
					}
				} else {
					System.out.println("No Status Found on AO tables.");
				}
			}
		}
		return Response.ok(bean).build();

	}

	@PUT
	@Path("{id}")
	public Response updateStatusDetails(@PathParam("id") final String id, final AssetFieldBean bean) {
		System.out.println("---------Inside getUpdateFieldDetails---------");
		return null;
	}

	@DELETE
	@Path("{id}")
	public Response deleteField(@PathParam("id") final String id) {
		System.out.println("---------Inside delete Field---------");
		System.out.println();
		return null;
	}

	public Status createStatus(String name, String description, String statusCategoryKey) {

		String categoryKey = null;
		switch (statusCategoryKey) {
		case "UNDEFINED":
			categoryKey = StatusCategory.UNDEFINED;
			break;
		case "todo":
			categoryKey = StatusCategory.TO_DO;
			break;
		case "In Progress":
			categoryKey = StatusCategory.IN_PROGRESS;
			break;
		case "done":
			categoryKey = StatusCategory.COMPLETE;
			break;
		default:
			categoryKey = StatusCategory.UNDEFINED;
		}

		StatusCategoryManager statusCategoryManager = ComponentAccessor.getComponent(StatusCategoryManager.class);
		StatusCategory statusCategory = statusCategoryManager.getStatusCategoryByKey(categoryKey);
		if (statusCategory != null) {
			System.out.println("statusCategory name: " + statusCategory.getName());
		}

		return statusManager.createStatus(name, description, "/images/icons/pluginIcon.png", statusCategory);
	}

}
