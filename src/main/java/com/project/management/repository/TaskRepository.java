package com.project.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.management.resources.TaskResource;


/**
 * @author Dhiraj Asati
 *
 */
public interface TaskRepository extends MongoRepository<TaskResource, String> {

}
