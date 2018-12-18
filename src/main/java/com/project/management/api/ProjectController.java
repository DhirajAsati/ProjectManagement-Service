package com.project.management.api;

import java.util.List;

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

import com.project.management.resources.ProjectResource;
import com.project.management.resources.UserResource;
import com.project.management.services.ProjectService;
import com.task.manager.exception.ProjectManagementException;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin
@RestController
@RequestMapping(value = "/services/v1/project-management")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value = "/getProjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "All Project retrieved successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<List<ProjectResource>> getProjects() throws ProjectManagementException {
		List<ProjectResource> projectList = projectService.getProjects();
		return new ResponseEntity<>(projectList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProjects/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Project retrieved successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<List<ProjectResource>> getProjectById() throws ProjectManagementException {
		List<ProjectResource> projectList = projectService.getProjects();
		return new ResponseEntity<>(projectList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Project created successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<ProjectResource> addProject(@RequestBody ProjectResource projectResource) throws ProjectManagementException {
		ProjectResource projectResourceResponse = projectService.addProject(projectResource);
		return new ResponseEntity<>(projectResourceResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateProject", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Project update successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<ProjectResource> updateProject(@RequestBody ProjectResource projectResource) throws ProjectManagementException {
		ProjectResource projectResourceResponse = projectService.updateProject(projectResource);
		return new ResponseEntity<>(projectResourceResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteProject/{projectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Task deleted successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public  ResponseEntity<UserResource> getUsersList(@PathVariable String projectId) throws ProjectManagementException {
		this.projectService.deleteProject(projectId);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
