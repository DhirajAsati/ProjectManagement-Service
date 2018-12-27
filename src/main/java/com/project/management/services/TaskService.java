package com.project.management.services;

import java.util.List;

import com.project.management.exception.ProjectManagementException;
import com.project.management.resources.ParentTaskResource;
import com.project.management.resources.TaskResource;

/**
 * @author Dhiraj Asati
 *
 */
public interface TaskService {

	public List<TaskResource> getTasks() throws ProjectManagementException;
	
	public List<ParentTaskResource> getParentTasks() throws ProjectManagementException;

	public TaskResource addTask(TaskResource taskResource) throws ProjectManagementException;
	
	public TaskResource updateTask(TaskResource taskResource) throws ProjectManagementException;

	public String deleteTask(String taskId) throws ProjectManagementException;
	
	public ParentTaskResource addParentTask(ParentTaskResource parentTaskResource) throws ProjectManagementException;
	
	public List<TaskResource> getTasksByProjectId(String projectId) throws ProjectManagementException;
	
	public TaskResource getTaskById(String taskId) throws ProjectManagementException;
	
	public TaskResource endTask(String taskId) throws ProjectManagementException;

}
