package com.acis.assets.mgmt.ao;

public class FieldsModel {
	private int id;
	private String fieldId;

	public FieldsModel(int id, String fieldId) {
		super();
		this.id = id;
		this.fieldId = fieldId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

}
