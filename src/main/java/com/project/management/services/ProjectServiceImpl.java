package com.project.management.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.controller.ProjectController;
import com.project.management.exception.ProjectManagementException;
import com.project.management.repository.ProjectRepository;
import com.project.management.resources.ProjectResource;
import com.project.management.resources.TaskResource;
import com.project.management.resources.UserResource;
import com.project.management.utils.ProjectStatusEnum;

/**
 * @author  Dhiraj Asati
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	private static final Logger LOG = LoggerFactory.getLogger(ProjectServiceImpl.class);
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;

	@Override
	public List<ProjectResource> getProjects() throws ProjectManagementException {
		int  totalTask = 0;
		int  completedTask = 0;
		LOG.info("ProjecrServiceImpl:: getProjects");
		List<ProjectResource> projectResourceList = this.projectRepository.findAll();
		List<TaskResource> taskResourceList = taskService.getTasks();
		if(projectResourceList!=null && !projectResourceList.isEmpty()) {
			for(ProjectResource projectResource: projectResourceList) {
				for(TaskResource taskResource: taskResourceList) {
					if(projectResource.getProjectId().equals(taskResource.getProjectId())) {
						if(ProjectStatusEnum.COMPLETED.getStatus().equals(taskResource.getStatus())) {
							completedTask++;
						}
						totalTask++;
					}
				}
				projectResource.setCompletedTasks(completedTask);
				projectResource.setTotalTasks(totalTask);
				completedTask = 0;
				totalTask = 0;
			}
		}
		return projectResourceList;
	}

	@Override
	public ProjectResource addProject(ProjectResource projectResource) throws ProjectManagementException {
		LOG.info("ProjecrServiceImpl:: addProject");
		ProjectResource projectResourceTmp = this.projectRepository.save(projectResource);
		updateProjectIdInUser(projectResourceTmp);
		return projectResourceTmp;
		
	}

	@Override
	public String deleteProject(String projectId) throws ProjectManagementException {
		LOG.info("ProjecrServiceImpl:: deleteProject");
		this.projectRepository.deleteById(projectId);
		return projectId;
	}

	@Override 
	public ProjectResource getProjectById(String projectId, String userId) throws ProjectManagementException {
		LOG.info("ProjecrServiceImpl:: getProjectBy ID");
		UserResource userResource = userService.getUserById(userId);
		Optional<ProjectResource> optionalResource = this.projectRepository.findById(projectId);
		ProjectResource projectResource = optionalResource.get();
		projectResource.setUserId(userResource.getUserId());
		projectResource.setUserName(userResource.getFirstName()+" "+userResource.getLastName());
		return projectResource;
	}
	
	public ProjectResource updateProject(ProjectResource projectResource) throws ProjectManagementException {
		LOG.info("ProjecrServiceImpl:: Update Projects");
		Optional<ProjectResource> optionalProjectResource = projectRepository.findById(projectResource.getProjectId());
		if (optionalProjectResource.isPresent()) {
			updateProjectIdInUser(projectResource);
			return projectRepository.save(projectResource);
		}
		return null;
	}
	
	private void updateProjectIdInUser(ProjectResource projectResource) throws ProjectManagementException {
		System.out.println("projectResource.getProjectId():: "+projectResource.getProjectId());
		System.out.println("projectResource.getUserId():: "+projectResource.getUserId());
		userService.updateProjectId(projectResource.getProjectId(), projectResource.getUserId());
	}
}
