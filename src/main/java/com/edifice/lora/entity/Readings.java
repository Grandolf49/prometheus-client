package com.edifice.lora.entity;

import java.sql.Date;

public class Readings {

	Integer id ;
	Integer deviceId ;
	String endpointKey; //deviceKey
	Integer groupId ;
	Date readingDate ;
	Double latitude ;
	Double longitude ;
	Double values ;
	
	Group group ;
	Device device ;
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
	 * @return the deviceId
	 */
	public Integer getDeviceId() {
		return deviceId;
	}
	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the endpointKey
	 */
	public String getEndpointKey() {
		return endpointKey;
	}
	/**
	 * @param endpointKey the endpointKey to set
	 */
	public void setEndpointKey(String endpointKey) {
		this.endpointKey = endpointKey;
	}
	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the readingDate
	 */
	public Date getReadingDate() {
		return readingDate;
	}
	/**
	 * @param readingDate the readingDate to set
	 */
	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the values
	 */
	public Double getValues() {
		return values;
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(Double values) {
		this.values = values;
	}
	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}
	/**
	 * @return the device
	 */
	public Device getDevice() {
		return device;
	}
	/**
	 * @param device the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}
	
	
}
