package com.acis.assets.mgmt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.acis.assets.mgmt.ao.AssetTypesEntity;
import com.acis.assets.mgmt.ao.AssetsConfigService;
import com.acis.assets.mgmt.ao.AssetsCustomFieldsEntity;
import com.acis.assets.mgmt.ao.CustomfieldsMappingEntity;
import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

import webwork.action.ServletActionContext;
import webwork.multipart.MultiPartRequestWrapper;

@SuppressWarnings("serial")
public class AssetsConfigurationAction extends JiraWebActionSupport {

	private List<AssetDefenitionBean> assetDefenitionBeans;
	private List<AssetCustomFieldsBean> assetFieldBeans;
	private List<AssetFieldBean> selectedFieldsList;
	private List<AssetTypeFieldsMappingBean> assetTypeFieldsMappingList;

	@ComponentImport
	private final ActiveObjects ao;
	@Autowired
	private final AssetsConfigService configService;
	@ComponentImport
	private final CustomFieldManager customFieldManager;

	public AssetsConfigurationAction(ActiveObjects ao, AssetsConfigService configService, CustomFieldManager customFieldManager) {
		super();
		this.ao = ao;
		this.configService = configService;
		this.customFieldManager = customFieldManager;
	}

	protected void doValidation() {
		System.out.println("##################### doValidation #######");
		// Validation code here
		System.out.println("#####################doValidation - end #######");
	}

	protected String doExecute() throws Exception {
		System.out.println("##################### doExecute #######");
		HttpServletRequest req = getHttpRequest();
		String assestType = req.getParameter("asset-type-name");
		String assetDescription = req.getParameter("asset-type-description");
		String iconUrl = req.getParameter("asset-type-icon");
		System.out.println("assestType: " + assestType);
		System.out.println("assetDescription: " + assetDescription);
		System.out.println("iconUrl: " + iconUrl);

		MultiPartRequestWrapper wrapper = ServletActionContext.getMultiPartRequest();
		System.out.println("wrapper: " + wrapper);
		if (wrapper != null) {
			File file = wrapper.getFile("asset-upload-icon");
			assestType = wrapper.getParameter("asset-type-name");
			assetDescription = wrapper.getParameter("asset-type-description");
			iconUrl = wrapper.getParameter("asset-type-icon");
			System.out.println("file: " + file);
		}
		System.out.println("assestType: " + assestType);
		System.out.println("assetDescription: " + assetDescription);
		System.out.println("iconUrl: " + iconUrl);

		if (assestType != null) {
			AssetTypesEntity assest = configService.createAssetType(ao, assestType, assetDescription, iconUrl);
			System.out.println("assest: " + assest.getAssetTypeName());
		}

		this.assetDefenitionBeans = getAllAssetDefenitions();
		this.assetFieldBeans = getAllAssetCustomFields();
		this.assetTypeFieldsMappingList = getAssetTypeFieldsMappingList();

		System.out.println("##################### doExecute - end #######");
		return "input";
	}

	public String doAssetsMappingSave() throws Exception {
		System.out.println("##################### doAssetsMappingSave #######");
		HttpServletRequest req = getHttpRequest();
		String assestTypeId = req.getParameter("asset-type-select");
		System.out.println("assestType: " + assestTypeId);
		String[] values = req.getParameterValues("fieldsListSelected");
		System.out.println("fieldsListSelected: " + values);

		if (assestTypeId != null) {
			List<AssetTypesEntity> assetTypes = configService.getAssetType(ao, assestTypeId);
			AssetTypesEntity assetType = null;
			if (assetTypes != null) {
				if (assetTypes.size() == 1) {
					assetType = assetTypes.get(0);
				} else {
					System.out.println("There are more than one AssetType records with Id: " + assestTypeId + ", Something Wrong...");
				}
			} else {
				System.out.println("No Entity Found with id: " + assestTypeId);
			}

			List<String> fieldIds = new ArrayList<String>();

			if (values != null && values.length > 0) {
				for (String id : values) {
					System.out.println("selcted field id: " + id);
					CustomField cf = customFieldManager.getCustomFieldObject(id);
					if (cf != null) {
						fieldIds.add(cf.getId());
					} else {
						System.out.println("No Customfield found with ID: " + id);
					}
				}
			}

			if (assetType != null && fieldIds.size() > 0) {
				configService.updateAssetTypeAndFieldsMapping(ao, assetType, fieldIds);
			}

		}

		String nextUrl = "/secure/assetsconfig!default.jspa";
		String contextPath = ServletActionContext.getRequest().getContextPath();
		if (contextPath != null) {
			nextUrl = contextPath + nextUrl;
		}

		ServletActionContext.getResponse().sendRedirect(nextUrl);
		return super.doDefault();
	}

	public String doDefault() throws Exception {
		System.out.println("##################### doDefault #######");

		String result = super.doDefault();
		this.assetDefenitionBeans = getAllAssetDefenitions();
		this.assetFieldBeans = getAllAssetCustomFields();
		this.assetTypeFieldsMappingList = getAssetTypeFieldsMapping();

		return result;
	}

	public void doPost() throws Exception {
		System.out.println("##################### goPost #######");
	}

	public List<AssetDefenitionBean> getAssetDefenitionBeans() {
		return assetDefenitionBeans;
	}

	public void setAssetDefenitionBeans(List<AssetDefenitionBean> assetDefenitionBeans) {
		this.assetDefenitionBeans = assetDefenitionBeans;
	}

	public List<AssetFieldBean> getSelectedFields() {
		return selectedFieldsList;
	}

	public void setSelectedFields(List<AssetFieldBean> selectedFields) {
		this.selectedFieldsList = selectedFields;
	}

	public List<AssetCustomFieldsBean> getAssetFieldBeans() {
		return assetFieldBeans;
	}

	public void setAssetFieldBeans(List<AssetCustomFieldsBean> assetFieldBeans) {
		this.assetFieldBeans = assetFieldBeans;
	}

	public List<AssetTypeFieldsMappingBean> getAssetTypeFieldsMappingList() {
		return assetTypeFieldsMappingList;
	}

	public void setAssetTypeFieldsMappingList(List<AssetTypeFieldsMappingBean> assetTypeFieldsMappingList) {
		this.assetTypeFieldsMappingList = assetTypeFieldsMappingList;
	}

	public List<AssetDefenitionBean> getAllAssetDefenitions() {
		List<AssetDefenitionBean> allAssets = new ArrayList<AssetDefenitionBean>();
		List<AssetTypesEntity> allAssetsTypes = configService.getAllAssetTypes(ao);
		for (AssetTypesEntity entity : allAssetsTypes) {
			allAssets.add(new AssetDefenitionBean(entity.getID(), entity.getAssetTypeName(), entity.getAssetTypeDescription(),
					entity.getAssetTypeIconUrl()));
		}

		return allAssets;
	}

	public List<AssetCustomFieldsBean> getAllAssetCustomFields() {
		List<AssetsCustomFieldsEntity> allFields = configService.getAllCustomFieldsId(ao);
		final List<AssetCustomFieldsBean> beans = new ArrayList<AssetCustomFieldsBean>();
		if (allFields != null) {
			for (AssetsCustomFieldsEntity entity : allFields) {
				CustomField cf = customFieldManager.getCustomFieldObject(entity.getCustomFieldId());
				if (cf != null) {
					AssetCustomFieldsBean bean = new AssetCustomFieldsBean();
					bean.setId(entity.getID());
					bean.setCustomFieldId(cf.getId());
					bean.setDescription(cf.getDescription());
					bean.setName(cf.getFieldName());
					bean.setType(cf.getCustomFieldType().getName());
					// System.out.println("Custom Field Type: " +
					// cf.getCustomFieldType().getName());
					beans.add(bean);

				} else {
					System.out.println("No customfield found with Id= " + entity.getCustomFieldId());
				}
			}
		} else {
			System.out.println("allFields: " + allFields);
		}

		return beans;
	}

	public List<AssetTypeFieldsMappingBean> getAssetTypeFieldsMapping() {

		List<AssetTypeFieldsMappingBean> beans = new ArrayList<AssetTypeFieldsMappingBean>();
		List<AssetTypesEntity> allAssetTypes = configService.getAllAssetTypes(ao);
		for (AssetTypesEntity entity : allAssetTypes) {
			AssetTypeFieldsMappingBean bean = new AssetTypeFieldsMappingBean();
			String assetTypename = entity.getAssetTypeName();
			CustomfieldsMappingEntity[] fields = entity.getCustomfieldsMappingEntity();
			if (fields != null) {
				List<String> fieldsList = new ArrayList<String>();
				for (CustomfieldsMappingEntity fieldEntity : fields) {
					String fieldId = fieldEntity.getCustomFieldId();
					System.out.println("fieldId: " + fieldId);
					CustomField cf = customFieldManager.getCustomFieldObject(fieldId);
					System.out.println("cf: " + cf);
					if (cf != null) {
						fieldsList.add(cf.getName());
					}
				}
				bean.setFields(StringUtils.join(fieldsList, ","));
			}
			bean.setId(entity.getID());
			bean.setAssetTypeName(assetTypename);

			beans.add(bean);
		}
		return beans;

	}

}
