package com.acis.assets.mgmt.ao;

import net.java.ao.Entity;
import net.java.ao.schema.Table;

@Table("AssetsCustomFields")
public interface AssetsCustomFieldsEntity extends Entity {
	String getCustomFieldId();

	void setCustomFieldId(String customfieldId);

}
