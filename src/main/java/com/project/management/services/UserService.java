package com.project.management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.management.resources.UserResource;
import com.task.manager.exception.ProjectManagementException;


/**
 * @author Dhiraj Asati
 *
 */
public interface UserService {

	public List<UserResource> getUsers() throws ProjectManagementException;

	public UserResource saveUser(UserResource userResource) throws ProjectManagementException;

	public String deleteUser(String userID) throws ProjectManagementException;
	
	public UserResource updateUser(UserResource userResource) throws ProjectManagementException;

}
