package com.acis.assets.mgmt;

public class AssetDefenitionBean {
	private int id;
	private String assetTypeName;
	private String assetTypeDescription;
	private String iconUrl;

	public AssetDefenitionBean(int id, String assetTypeName, String assetTypeDescription, String iconUrl) {
		super();
		this.id = id;
		this.assetTypeName = assetTypeName;
		this.assetTypeDescription = assetTypeDescription;
		this.iconUrl = iconUrl;
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

	public String getAssetTypeDescription() {
		return assetTypeDescription;
	}

	public void setAssetTypeDescription(String assetTypeDescription) {
		this.assetTypeDescription = assetTypeDescription;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

}
