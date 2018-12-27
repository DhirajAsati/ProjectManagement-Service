package com.project.management.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.exception.ProjectManagementException;
import com.project.management.repository.UserRepository;
import com.project.management.resources.TaskResource;
import com.project.management.resources.UserResource;
import com.project.management.utils.ProjectStatusEnum;

/**
 * @author Dhiraj Asati
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	public List<UserResource> getUsers() throws ProjectManagementException {
		LOG.info("UserServiceImpl:: getUsers method");
		return this.userRepository.findAll();
	}

	public UserResource saveUser(UserResource userResource) throws ProjectManagementException {
		LOG.info("UserServiceImpl:: saveUser method");
		return this.userRepository.save(userResource);
	}

	public String deleteUser(String userId) throws ProjectManagementException {
		LOG.info("UserServiceImpl:: deleteUsers method");
		this.userRepository.deleteById(userId);
		return userId;
	}
	
	@Override
	public UserResource updateUser(UserResource userResource) throws ProjectManagementException {
		LOG.info("UserServiceImpl:: updateUsers method");
		Optional<UserResource> optionalUserResource = userRepository.findById(userResource.getUserId());
		if (optionalUserResource.isPresent()) {
			return userRepository.save(userResource);
		}
		return null;
	}
	@Override
	public UserResource updateProjectId(String projectId, String userId) throws ProjectManagementException {
		LOG.info("UserServiceImpl:: updateProjectId method");
		Optional<UserResource> optionalUserResource = userRepository.findById(userId);
		if (optionalUserResource.isPresent()) {
			optionalUserResource.get().setProjectId(projectId);
			return userRepository.save(optionalUserResource.get());
		}
		return null;
	}
	
	@Override
	public UserResource updateTaskId(String taskId, String userId) throws ProjectManagementException {
		LOG.info("UserServiceImpl:: updateTaskId method");
		Optional<UserResource> optionalUserResource = userRepository.findById(userId);
		if (optionalUserResource.isPresent()) {
			optionalUserResource.get().setProjectId(taskId);
			return userRepository.save(optionalUserResource.get());
		}
		return null;
	}

	@Override
	public UserResource getUserById(String userId) throws ProjectManagementException {
		LOG.info("UserServiceImpl:: getUserById method");
		Optional<UserResource> optionalUserResource = userRepository.findById(userId);
		return (UserResource)optionalUserResource.get();
	}
}
