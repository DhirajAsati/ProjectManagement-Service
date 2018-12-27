package com.project.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.management.exception.ProjectManagementException;
import com.project.management.resources.UserResource;
import com.project.management.services.UserService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController()
@RequestMapping("/services/v1/project-management")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "UserLise retrieved successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public ResponseEntity<List<UserResource>> getUsersList() throws ProjectManagementException {
		LOG.info("UserController:: GetUserList method");
		List<UserResource> userResourceList = userService.getUsers();
		return new ResponseEntity<>(userResourceList, HttpStatus.OK);

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "User Created successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<UserResource> createUser(@RequestBody UserResource userResource) throws ProjectManagementException {
		LOG.info("UserController:: CreateUser method");
		UserResource userDetailsResponse = userService.saveUser(userResource);
		return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "User updated successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<UserResource> updateUser(@RequestBody UserResource userResource) throws ProjectManagementException {
		LOG.info("UserController:: Update User method");
		UserResource userDetailsResponse = userService.updateUser(userResource);
		return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "User deleted successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public  ResponseEntity<UserResource> deleteUser(@PathVariable String userId) throws ProjectManagementException {
		LOG.info("UserController:: Delete User method");
		this.userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getUserById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "UserLise retrieved successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public ResponseEntity<UserResource> getUserById(@PathVariable String userId) throws ProjectManagementException {
		LOG.info("UserController:: Get User By ID method");
		UserResource userResource = userService.getUserById(userId);
		return new ResponseEntity<>(userResource, HttpStatus.OK);

	}

}
