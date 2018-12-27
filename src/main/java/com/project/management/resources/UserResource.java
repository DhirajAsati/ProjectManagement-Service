package com.project.management.resources;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "USER_RESOURCE")
public class UserResource {

	@Id
	private String userId;
	
	@Size(min=1, max=70, message="First Name is required")
	private String firstName;

	@Size(min=1, max=70, message="Last Name is required")
	private String lastName;

	@Size(min=1, max=70, message="Employee ID is required")
	private String employeeId;
	
	private String projectId;
	private String taskId;
}
