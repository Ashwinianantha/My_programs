package com.acis.assets.mgmt;

public class AssetTypeFieldsMappingBean {
	private int id;
	private String assetTypeName;
	private String fields;

	public AssetTypeFieldsMappingBean(int id, String assetTypeName, String fields) {
		super();
		this.assetTypeName = assetTypeName;
		this.fields = fields;
	}

	public AssetTypeFieldsMappingBean() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssetTypeName() {
		return assetTypeName;
	}

	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

}
