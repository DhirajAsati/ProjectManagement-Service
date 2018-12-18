package com.project.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.repository.UserRepository;
import com.project.management.resources.TaskResource;
import com.project.management.resources.UserResource;
import com.project.management.utils.ProjectStatusEnum;
import com.task.manager.exception.ProjectManagementException;


/**
 * @author Dhiraj Asati
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserResource> getUsers() throws ProjectManagementException {
		return this.userRepository.findAll();
	}

	public UserResource saveUser(UserResource userResource) throws ProjectManagementException {
		return this.userRepository.save(userResource);
	}

	public String deleteUser(String userID) throws ProjectManagementException {
		this.userRepository.deleteById(userID);
		return userID;
	}
	
	@Override
	public UserResource updateUser(UserResource userResource) throws ProjectManagementException {
		Optional<UserResource> optionalTaskResource = userRepository.findById(userResource.getId());
		if (optionalTaskResource.isPresent()) {
			UserResource userResourceTmp = optionalTaskResource.get();
			return userRepository.save(userResourceTmp);
		}
		return null;
	}
}
