package com.project.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.management.resources.ParentTaskResource;

/**
 * @author Dhiraj Asati
 *
 */
public interface ParentTaskRepository extends MongoRepository<ParentTaskResource, String> {

}
