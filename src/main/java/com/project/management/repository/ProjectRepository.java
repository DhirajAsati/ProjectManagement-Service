package com.project.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.management.resources.ProjectResource;

/**
 * @author Dhiraj Asati
 *
 */
public interface ProjectRepository extends MongoRepository<ProjectResource, String> {

}
