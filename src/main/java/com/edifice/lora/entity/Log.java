package com.edifice.lora.entity;

import java.sql.Date;

public class Log 
{
	Integer logId ;
	String deviceId;
	String thingsRequest;
	Date created ;
	/**
	 * @return the logId
	 */
	public Integer getLogId() {
		return logId;
	}
	/**
	 * @param logId the logId to set
	 */
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the thingsRequest
	 */
	public String getThingsRequest() {
		return thingsRequest;
	}
	/**
	 * @param thingsRequest the thingsRequest to set
	 */
	public void setThingsRequest(String thingsRequest) {
		this.thingsRequest = thingsRequest;
	}
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	
}
