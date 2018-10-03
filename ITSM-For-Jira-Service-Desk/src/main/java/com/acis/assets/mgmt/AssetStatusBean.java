package com.acis.assets.mgmt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AssetStatusBean {
	@XmlElement
	private int id;
	@XmlElement
	private String name;
	@XmlElement
	private String description;
	@XmlElement
	private String category;
	
	
	public AssetStatusBean() {
		super();
	}


	public AssetStatusBean(int id, String name, String description, String category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
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


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


}
