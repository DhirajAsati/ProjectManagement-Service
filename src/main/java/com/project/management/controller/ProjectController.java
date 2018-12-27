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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.management.exception.ProjectManagementException;
import com.project.management.resources.ProjectResource;
import com.project.management.services.ProjectService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin
@RestController
@RequestMapping(value = "/services/v1/project-management")
public class ProjectController {
	private static final Logger LOG = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value = "/getProjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "All Project retrieved successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<List<ProjectResource>> getProjects() throws ProjectManagementException {
		LOG.info("ProjectController:: Get Project Method");
		List<ProjectResource> projectList = projectService.getProjects();
		return new ResponseEntity<>(projectList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Project retrieved successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<ProjectResource> getProjectById(@RequestParam String projectId, @RequestParam String userId) throws ProjectManagementException {
		LOG.info("ProjectController:: Get Project BY ID Method::  "+userId);
		ProjectResource projectResouce = projectService.getProjectById(projectId, userId);
		return new ResponseEntity<>(projectResouce, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Project created successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<ProjectResource> addProject(@RequestBody ProjectResource projectResource) throws ProjectManagementException {
		LOG.info("ProjectController:: ADD Project Method");
		ProjectResource projectResourceResponse = projectService.addProject(projectResource);
		return new ResponseEntity<>(projectResourceResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateProject", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Project updated successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public @ResponseBody ResponseEntity<ProjectResource> updateProject(@RequestBody ProjectResource projectResource) throws ProjectManagementException {
		LOG.info("ProjectController:: UPDATE Project Method");
		ProjectResource projectResourceResponse = projectService.updateProject(projectResource);
		return new ResponseEntity<>(projectResourceResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteProject/{projectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 201, message = "Task deleted successfully."),
		@ApiResponse(code = 400, message = "Bad request. Missing required parameters."),
		@ApiResponse(code = 404, message = "No records found."),
		@ApiResponse(code = 500, message = "Internal server error.") }) 
	public  ResponseEntity<ProjectResource> deleteProjectById(@PathVariable String projectId) throws ProjectManagementException {
		LOG.info("ProjectController:: DELETE Project Method");
		this.projectService.deleteProject(projectId);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
