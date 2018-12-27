package com.project.management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.management.resources.TaskResource;


/**
 * @author Dhiraj Asati
 *
 */
public interface TaskRepository extends MongoRepository<TaskResource, String> {
	
	public List<TaskResource> findByProjectId(String projectId);

}
