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
import java.util.List;

import org.junit.After;
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
import com.project.management.resources.UserResource;
import com.project.management.services.UserService;

/**
 * @author Dhiraj Asati
 *
 */
@ActiveProfiles("junit")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private MockMvc mockMvc;
	
	private MockRestServiceServer server;
	
	@MockBean
	private UserService userService;

	@InjectMocks
	@Autowired
	private UserController userController;
	
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
	public void testGetUsers() throws Exception {
		List<UserResource> userDetailsList = new ArrayList<>();
		UserResource userDetails = new UserResource();
		userDetails.setEmployeeId("1");
		userDetails.setFirstName("First Name");
		userDetails.setLastName("Last Name");
		userDetails.setUserId("1");
		userDetailsList.add(userDetails);
		when(userService.getUsers()).thenReturn(userDetailsList);
		this.mockMvc.perform(get("/services/v1/project-management/getUsers")).andExpect(status().isOk());
		verify(userService, times(1)).getUsers();

	}

	@Test
	public void testGetUsers_with_invalidUrl() throws Exception {
		this.mockMvc.perform(get("/service/v1/project-management/getUsers")).andExpect(status().isNotFound());

	}

	@Test
	public void testAddUser() throws Exception {
		UserResource userDetails = new UserResource();
		userDetails.setEmployeeId("1");
		userDetails.setFirstName("First Name");
		userDetails.setLastName("Last Name");
		userDetails.setUserId("1");

		ObjectMapper mapper = new ObjectMapper();

		when(userService.saveUser(userDetails)).thenReturn(userDetails);
		this.mockMvc
				.perform(post("/services/v1/project-management/addUser").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(userDetails)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void testAddUser_withNoContent() throws Exception {
		UserResource userDetails = new UserResource();
		userDetails.setEmployeeId("1");
		userDetails.setFirstName("First Name");
		userDetails.setLastName("Last Name");
		userDetails.setUserId("1");

		when(userService.saveUser(userDetails)).thenReturn(userDetails);
		this.mockMvc.perform(
				post("/services/v1/project-management/addUser").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();

	}
	
	@Test
	public void testDeleteUser() throws Exception {

		when(userService.deleteUser("1")).thenReturn("1");
		this.mockMvc.perform(delete("/services/v1/project-management/deleteUser/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	}

}
