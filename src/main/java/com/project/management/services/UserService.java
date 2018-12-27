package com.project.management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.management.exception.ProjectManagementException;
import com.project.management.resources.UserResource;


/**
 * @author Dhiraj Asati
 *
 */
public interface UserService {

	public List<UserResource> getUsers() throws ProjectManagementException;

	public UserResource saveUser(UserResource userResource) throws ProjectManagementException;

	public String deleteUser(String userID) throws ProjectManagementException;
	
	public UserResource updateUser(UserResource userResource) throws ProjectManagementException;

	public UserResource getUserById(String userId) throws ProjectManagementException;
	
	public UserResource updateProjectId(String projectId, String userId) throws ProjectManagementException;
	
	public UserResource updateTaskId(String taskId, String userId) throws ProjectManagementException;
}

