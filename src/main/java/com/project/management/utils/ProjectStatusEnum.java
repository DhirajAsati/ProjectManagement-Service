/**
 * 
 */
package com.project.management.utils;

/**
 * @author n0315896
 *
 */
public enum ProjectStatusEnum {

	COMPLETED("Completed"), IN_PROGRESS("In Progress"), SUSPENDED("Suspended");
	String status;

	ProjectStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
