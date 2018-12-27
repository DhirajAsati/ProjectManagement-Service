package com.project.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.exception.ProjectManagementException;
import com.project.management.repository.ParentTaskRepository;
import com.project.management.repository.TaskRepository;
import com.project.management.resources.ParentTaskResource;
import com.project.management.resources.ProjectResource;
import com.project.management.resources.TaskResource;
import com.project.management.utils.ProjectStatusEnum;

/**
 * @author Dhiraj Asati
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ParentTaskRepository parentTaskRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<TaskResource> getTasks() throws ProjectManagementException {
		return taskRepository.findAll();
	}
	
	@Override
	public List<ParentTaskResource> getParentTasks() throws ProjectManagementException {
		return parentTaskRepository.findAll();
	}
	
	@Override 
	public List<TaskResource> getTasksByProjectId(String projectId) throws ProjectManagementException {
		List<TaskResource> taskResourceList = this.taskRepository.findByProjectId(projectId);
		if(taskResourceList != null) {
		return taskResourceList;
		}
		return null;
	}
	
	@Override 
	public TaskResource getTaskById(String taskId) throws ProjectManagementException {
		Optional<TaskResource> optionalTaskResource = taskRepository.findById(taskId);
		return optionalTaskResource.get();
	}

	@Override
	public TaskResource addTask(TaskResource taskResource) throws ProjectManagementException {
		taskResource.setStatus(ProjectStatusEnum.IN_PROGRESS.getStatus());
		TaskResource taskResourceTmp = taskRepository.save(taskResource);
		updateTaskIdInUser(taskResourceTmp);
		return taskResourceTmp;
	}
	
	@Override
	public ParentTaskResource addParentTask(ParentTaskResource parentTaskResource) throws ProjectManagementException {
		ParentTaskResource parentTaskResourceTmp = parentTaskRepository.save(parentTaskResource);
		return parentTaskResourceTmp;
	}

	@Override
	public String deleteTask(String taskId) throws ProjectManagementException {
		taskRepository.deleteById(taskId);
		return taskId;
	}

	@Override
	public TaskResource updateTask(TaskResource taskResource) throws ProjectManagementException {
		Optional<TaskResource> optionalTaskResource = taskRepository.findById(taskResource.getTaskId());
		if (optionalTaskResource.isPresent()) {
			return taskRepository.save(taskResource);
		}
		return null;
	}
	
	@Override
	public TaskResource endTask(String taskId) throws ProjectManagementException {
		Optional<TaskResource> optionalTaskResource = taskRepository.findById(taskId);
		if (optionalTaskResource.isPresent()) {
			optionalTaskResource.get().setStatus(ProjectStatusEnum.COMPLETED.getStatus());
			return taskRepository.save(optionalTaskResource.get());
		}
		return null;
	}
	
	private void updateTaskIdInUser(TaskResource taskResource) throws ProjectManagementException {
		System.out.println("projectResource.getProjectId():: "+taskResource.getProjectId());
		System.out.println("projectResource.getUserId():: "+taskResource.getUserId());
		userService.updateProjectId(taskResource.getTaskId(), taskResource.getUserId());
	}
}
