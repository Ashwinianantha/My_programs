package com.acis.assets.mgmt.ao;

import net.java.ao.Entity;
import net.java.ao.schema.Table;

@Table("FieldsMapping")
public interface CustomfieldsMappingEntity extends Entity {

	String getCustomFieldId();

	void setCustomFieldId(String customfieldId);

	public void setAssetTypesEntity(AssetTypesEntity assetsType);

	public AssetTypesEntity getAssetTypesEntity();
}
