package com.acis.assets.mgmt;

public class AssetCustomFieldsBean {

	private int id;

	private String customFieldId;
	
	private String name;

	private String description;

	private String type;

	private String fieldValues;

	public AssetCustomFieldsBean() {
		super();
	}

	public AssetCustomFieldsBean(int id, String name, String description, String type, String fieldValues) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.fieldValues = fieldValues;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(String fieldValues) {
		this.fieldValues = fieldValues;
	}

	public String getCustomFieldId() {
		return customFieldId;
	}

	public void setCustomFieldId(String customFieldId) {
		this.customFieldId = customFieldId;
	}
}
