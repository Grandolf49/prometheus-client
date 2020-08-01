package com.edifice.lora.entity;

public class SolutionGroup {

	String id ;
	String solutionId ;
	String goupId ;

	Solutions solution ;
	Group group ;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the solutionId
	 */
	public String getSolutionId() {
		return solutionId;
	}
	/**
	 * @param solutionId the solutionId to set
	 */
	public void setSolutionId(String solutionId) {
		this.solutionId = solutionId;
	}
	/**
	 * @return the goupId
	 */
	public String getGoupId() {
		return goupId;
	}
	/**
	 * @param goupId the goupId to set
	 */
	public void setGoupId(String goupId) {
		this.goupId = goupId;
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
	
	
}
