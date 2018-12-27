package com.project.management.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import com.project.management.resources.TaskResource;
import com.project.management.services.ProjectService;
import com.project.management.services.TaskService;
import com.project.management.services.TaskServiceImpl;

/**
 * @author Dhiraj Asati
 *
 */
@ActiveProfiles("junit")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private MockMvc mockMvc;
	
	private MockRestServiceServer server;
	
	@MockBean
	private TaskService taskService;

	@InjectMocks
	@Autowired
	private TaskController taskController;
	
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
	public void testAddTasks() throws Exception {
		List<TaskResource> taskDetails = new ArrayList<>();
		when(taskService.getTasks()).thenReturn(taskDetails);
		this.mockMvc.perform(get("/services/v1/project-management/getTasks")).andExpect(status().isOk());
		verify(taskService, times(1)).getTasks();
	}
	
	@Test
	public void testAddProject() throws Exception {
		TaskResource taskDetails = new TaskResource();
		taskDetails.setUserId("1");
		taskDetails.setEndDate(new Date());
		taskDetails.setStartDate(new Date());
		taskDetails.setParentTask("");
		taskDetails.setTaskName("");
		taskDetails.setPriority("");

		ObjectMapper mapper = new ObjectMapper();

		when(taskService.addTask(taskDetails)).thenReturn(taskDetails);
		this.mockMvc
				.perform(post("/services/v1/project-management/addTask").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(taskDetails)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void testAddProject_withNoContent() throws Exception {
		TaskResource taskDetails = new TaskResource();
		taskDetails.setUserId("1");
		taskDetails.setEndDate(new Date());
		taskDetails.setStartDate(new Date());
		taskDetails.setParentTask("ParentTask-1");
		taskDetails.setTaskName("Task-Name");
		taskDetails.setPriority("4");

		when(taskService.addTask(taskDetails)).thenReturn(taskDetails);
		this.mockMvc.perform(post("/services/v1/project-management/addTask").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

	}
	
	@Test
	public void testDeleteTask() throws Exception {

		when(taskService.deleteTask("1")).thenReturn("1");
		this.mockMvc.perform(delete("/services/v1/project-management/deleteTask/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	}
	
	@Test
	public void testEndTask() throws Exception {
		
		TaskResource taskDetails = new TaskResource();
		taskDetails.setTaskId("1");
		taskDetails.setEndDate(new Date());
		taskDetails.setStartDate(new Date());
		taskDetails.setParentTask("");
		taskDetails.setTaskName("");
		taskDetails.setPriority("");

		ObjectMapper mapper = new ObjectMapper();

		when(taskService.endTask(taskDetails.getTaskId())).thenReturn(taskDetails);
		this.mockMvc
				.perform(get("/services/v1/project-management/endTask/1").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(taskDetails)))
				.andExpect(status().isOk()).andReturn();

	}

}
