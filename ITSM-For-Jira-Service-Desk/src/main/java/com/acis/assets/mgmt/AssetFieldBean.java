package com.acis.assets.mgmt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AssetFieldBean {
	@XmlElement
	private int id;
	@XmlElement
	private String name;
	@XmlElement
	private String description;
	@XmlElement
	private String type;
	@XmlElement
	private String fieldValues;
	
	public AssetFieldBean() {
		super();
	}

	public AssetFieldBean(int id, String name, String description, String type, String fieldValues) {
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
}
