package com.project.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.repository.TaskRepository;
import com.project.management.resources.TaskResource;
import com.project.management.utils.ProjectStatusEnum;
import com.task.manager.exception.ProjectManagementException;

/**
 * @author Dhiraj Asati
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<TaskResource> getTasks() throws ProjectManagementException {
		return taskRepository.findAll();
	}

	@Override
	public TaskResource addTask(TaskResource taskResource) throws ProjectManagementException {
		taskResource.setStatus(ProjectStatusEnum.IN_PROGRESS.getStatus());
		return taskRepository.save(taskResource);
	}

	@Override
	public String deleteTask(String taskId) throws ProjectManagementException {
		taskRepository.deleteById(taskId);
		return taskId;
	}

	@Override
	public TaskResource updateTask(TaskResource taskResource) throws ProjectManagementException {
		Optional<TaskResource> optionalTaskResource = taskRepository.findById(taskResource.getId());
		if (optionalTaskResource.isPresent()) {
			TaskResource taskResourceTmp = optionalTaskResource.get();
			taskResourceTmp.setStatus(ProjectStatusEnum.COMPLETED.getStatus());
			return taskRepository.save(taskResourceTmp);
		}
		return null;
	}

}
