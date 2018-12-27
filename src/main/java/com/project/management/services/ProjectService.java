package com.project.management.services;

import java.util.List;

import com.project.management.exception.ProjectManagementException;
import com.project.management.resources.ProjectResource;

/**
 * @author Dhiraj Asati
 *
 */
public interface ProjectService {

	public List<ProjectResource> getProjects() throws ProjectManagementException;
	
	public ProjectResource getProjectById(String projectId, String userId) throws ProjectManagementException;
	
	public ProjectResource addProject(ProjectResource projectResource) throws ProjectManagementException;
	
	public ProjectResource updateProject(ProjectResource projectResource) throws ProjectManagementException;
	
	public String deleteProject(String projectId) throws ProjectManagementException;
}
