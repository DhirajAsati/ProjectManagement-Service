package com.project.management.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.management.resources.ProjectResource;
import com.project.management.services.ProjectService;

/**
 * @author Dhiraj Asati
 *
 */
@ActiveProfiles("junit")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private MockMvc mockMvc;
	
	private MockRestServiceServer server;

	@MockBean
	private ProjectService projectService;

	@InjectMocks
	@Autowired
	private ProjectController projectController;
	
	@Autowired
	private WebApplicationContext context;
	
	@Spy
	private RestTemplate restGateway;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		server = MockRestServiceServer.createServer(restGateway);
	}

	@Test
	public void testGetProjects() throws Exception {
		List<ProjectResource> projectResourceList = new ArrayList<>();
		ProjectResource projectDetails = new ProjectResource();
		projectDetails.setProjectId("1");
		projectDetails.setCompletedTasks(1);
		projectDetails.setEndDate(new Date());
		projectDetails.setStartDate(new Date());
		projectDetails.setPriority("");
		projectResourceList.add(projectDetails);
		when(projectService.getProjects()).thenReturn(projectResourceList);
		this.mockMvc.perform(get("/services/v1/project-management/getProjects")).andExpect(status().isOk());
		verify(projectService, times(1)).getProjects();
	}

	@Test
	public void testGetProjects_with_invalidUrl() throws Exception {
		this.mockMvc.perform(get("/service/v1/project-management/getProjects")).andExpect(status().isNotFound());
	}

	@Test
	public void testAddProject() throws Exception {
		ProjectResource projectDetails = new ProjectResource();
		projectDetails.setProjectId("1");
		projectDetails.setCompletedTasks(10);
		projectDetails.setEndDate(new Date());
		projectDetails.setStartDate(new Date());
		projectDetails.setPriority("10");
		projectDetails.setProjectName("Junit");

		ObjectMapper mapper = new ObjectMapper();
		when(projectService.addProject(projectDetails)).thenReturn(projectDetails);
		this.mockMvc
				.perform(post("/services/v1/project-management/addProject").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(projectDetails)))
				.andExpect(status().isOk()).andReturn();

	}
	
	@Test
	public void testDeleteProject() throws Exception {

		when(projectService.deleteProject("1")).thenReturn("1");
		this.mockMvc.perform(delete("/services/v1/project-management/deleteProject/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	}
	
}
