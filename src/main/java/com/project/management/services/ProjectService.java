package com.project.management.services;

import java.util.List;

import com.project.management.resources.ProjectResource;
import com.task.manager.exception.ProjectManagementException;

/**
 * @author Dhiraj Asati
 *
 */
public interface ProjectService {

	public List<ProjectResource> getProjects() throws ProjectManagementException;
	
	public ProjectResource getProjectById(String projectId) throws ProjectManagementException;
	
	public ProjectResource addProject(ProjectResource projectResource) throws ProjectManagementException;
	
	public ProjectResource updateProject(ProjectResource projectResource) throws ProjectManagementException;
	
	public String deleteProject(String projectId) throws ProjectManagementException;
}
