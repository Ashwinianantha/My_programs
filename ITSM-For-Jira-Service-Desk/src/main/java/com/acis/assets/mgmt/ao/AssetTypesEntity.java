package com.acis.assets.mgmt.ao;

import net.java.ao.Entity;
import net.java.ao.OneToMany;
import net.java.ao.schema.Table;

@Table("AssetTypes")
public interface AssetTypesEntity extends Entity {
	String getAssetTypeName();

	void setAssetTypeName(String name);

	String getAssetTypeDescription();

	void setAssetTypeDescription(String desctiption);

	String getAssetTypeIconUrl();

	void setAssetTypeIconUrl(String iconUrl);

	@OneToMany(reverse = "getAssetTypesEntity")
	public CustomfieldsMappingEntity[] getCustomfieldsMappingEntity();

}
