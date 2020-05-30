package com.edifice.lora.entity;

public class DeviceType 
{
	private Integer id ;
	private String TypeName;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return TypeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
}
