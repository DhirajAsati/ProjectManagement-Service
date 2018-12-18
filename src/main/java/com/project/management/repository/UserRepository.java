package com.project.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.management.resources.UserResource;


/**
 * @author Dhiraj Asati
 *
 */
public interface UserRepository extends MongoRepository<UserResource, String>  {

}
