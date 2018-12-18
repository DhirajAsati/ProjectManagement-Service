package com.project.management.api;

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

import com.project.management.resources.TaskResource;
import com.project.management.resources.UserResource;
import com.project.management.services.TaskService;
import com.project.management.services.TaskServiceImpl;
import com.task.manager.exception.ProjectManagementException;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This class is a controller for Task.
 * @author dhiraj Asati
 *
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/services/v1/project-management")
public class TaskController {

	private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	private TaskService taskService;
	
	@Autowired
	public TaskController(TaskServiceImpl taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(value = "/getTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Retrieve tasks successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public ResponseEntity<List<TaskResource>> getTasks() throws ProjectManagementException {
		final List<TaskResource> taskList = taskService.getTasks();
		return new ResponseEntity<>(taskList, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Task Created successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<TaskResource> addTask(@RequestBody TaskResource taskResource) throws ProjectManagementException {
		TaskResource taskResourceResponse = taskService.addTask(taskResource);
		return new ResponseEntity<>(taskResourceResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateTask", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Task updated successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<TaskResource> updateTask(@RequestBody TaskResource taskResource) throws ProjectManagementException {
		TaskResource taskResourceResponse = taskService.updateTask(taskResource);
		return new ResponseEntity<>(taskResourceResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteTask/{taskId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Task deleted successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public  ResponseEntity<UserResource> deleteTask(@PathVariable String taskId) throws ProjectManagementException {
		this.taskService.deleteTask(taskId);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
