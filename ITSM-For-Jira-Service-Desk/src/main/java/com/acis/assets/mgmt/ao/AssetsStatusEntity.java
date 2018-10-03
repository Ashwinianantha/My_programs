package com.acis.assets.mgmt.ao;

import net.java.ao.Entity;
import net.java.ao.schema.Table;

@Table("AssetsStatusIds")
public interface AssetsStatusEntity extends Entity {
	String getStatusId();

	void setStatusId(String statusId);
}
