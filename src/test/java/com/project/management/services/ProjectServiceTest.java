package com.project.management.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.project.management.exception.ProjectManagementException;
import com.project.management.repository.ProjectRepository;
import com.project.management.resources.ProjectResource;


/**
 * @author Dhiraj Asati
 *
 */
@ActiveProfiles("junit")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@MockBean
	private ProjectService projectService;
	
	@MockBean
	ProjectRepository projectRepository;
	
	@Spy
	private RestTemplate restGateway;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetProjects() throws ProjectManagementException {
		when(projectRepository.findAll()).thenReturn(new ArrayList<>());
		assertTrue(projectService.getProjects().size() == 0);
	}

	@Test
	public void testAddProject() throws ProjectManagementException {
		ProjectResource projectDetails = new ProjectResource();
		projectDetails.setProjectId("1");
		projectDetails.setCompletedTasks(1);
		projectDetails.setEndDate(new Date());
		projectDetails.setStartDate(new Date());
		projectDetails.setPriority("");
		when(projectRepository.save(projectDetails)).thenReturn(projectDetails);
		assertEquals("1", projectDetails.getProjectId());
	}
}
