package com.edifice.lora.entity;

public class GroupDevices 
{
	private Integer id ;
	private Integer solutionGroupId ;
	private Integer deviceId ;
	private String endpointKey ; //depreceted
	private Boolean isDeleted ;
	
	private Device device ;
	private SolutionGroup solutionGroup;
	private Group group ;
	private Solutions solution ;
	
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
	 * @return the solution
	 */
	public Solutions getSolution() {
		return solution;
	}
	/**
	 * @param solution the solution to set
	 */
	public void setSolution(Solutions solution) {
		this.solution = solution;
	}
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
	 * @return the groupId
	 */
	public Integer getSolutionGroupId() {
		return solutionGroupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setSolutionGroupId(Integer groupId) {
		this.solutionGroupId = groupId;
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
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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
	/**
	 * @return the group
	 */
	public SolutionGroup getSolutionGroup() {
		return solutionGroup;
	}
	/**
	 * @param group the group to set
	 */
	public void setSolutionGroup(SolutionGroup group) {
		this.solutionGroup = group;
	}

}
