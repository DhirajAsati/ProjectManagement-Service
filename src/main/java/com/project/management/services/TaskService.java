package com.project.management.services;

import java.util.List;

import com.project.management.resources.TaskResource;
import com.task.manager.exception.ProjectManagementException;

/**
 * @author Dhiraj Asati
 *
 */
public interface TaskService {

	public List<TaskResource> getTasks() throws ProjectManagementException;

	public TaskResource addTask(TaskResource taskResource) throws ProjectManagementException;
	
	public TaskResource updateTask(TaskResource taskResource) throws ProjectManagementException;

	public String deleteTask(String taskId) throws ProjectManagementException;
	
	//public TaskResource endTask(String taskId) throws ProjectManagementException;

}
