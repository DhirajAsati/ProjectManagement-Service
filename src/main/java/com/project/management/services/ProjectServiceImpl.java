package com.project.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.repository.ProjectRepository;
import com.project.management.resources.ProjectResource;
import com.task.manager.exception.ProjectManagementException;


/**
 * @author  Dhiraj Asati
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<ProjectResource> getProjects() throws ProjectManagementException {
		return this.projectRepository.findAll();
	}

	@Override
	public ProjectResource addProject(ProjectResource projectResource) throws ProjectManagementException {
		return this.projectRepository.save(projectResource);
	}

	@Override
	public String deleteProject(String projectId) throws ProjectManagementException {
		this.projectRepository.deleteById(projectId);
		return projectId;
	}

	@Override 
	public ProjectResource getProjectById(String projectId) throws ProjectManagementException {
		Optional<ProjectResource> optResource = this.projectRepository.findById(projectId);
		
		return optResource.get();
	}
	
	public ProjectResource updateProject(ProjectResource projectResource) throws ProjectManagementException {
		Optional<ProjectResource> optionalProjectResource = projectRepository.findById(projectResource.getId());
		if (optionalProjectResource.isPresent()) {
			ProjectResource projectResourceTmp = optionalProjectResource.get();
			return projectRepository.save(projectResourceTmp);
		}
		return null;
	}

}
