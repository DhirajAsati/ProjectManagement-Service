package com.project.management.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.management.exception.ProjectManagementException;
import com.project.management.repository.TaskRepository;
import com.project.management.resources.TaskResource;
import com.project.management.utils.ProjectStatusEnum;


/**
 * @author Dhiraj Asati
 *
 */
@ActiveProfiles("junit")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskServiceImpl taskService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetTasks() throws ProjectManagementException {
		when(taskRepository.findAll()).thenReturn(new ArrayList<>());
		assertTrue(taskService.getTasks().size() == 0);

		TaskResource taskDetails = new TaskResource();
		taskDetails.setTaskId("1");
		taskDetails.setEndDate(new Date());
		taskDetails.setStartDate(new Date());
		taskDetails.setTaskName("");
		taskDetails.setParentTask("");
		taskDetails.setPriority("");
		taskDetails.setUserId("12");
		taskDetails.setProjectId("");
		taskDetails.setParentTask("");

		List<TaskResource> taskDetailList = new ArrayList<>();
		taskDetailList.add(taskDetails);

		when(taskRepository.findAll()).thenReturn(taskDetailList);
		assertTrue(taskService.getTasks().size() == 1);
	}

	@Test
	public void testAddTask() throws ProjectManagementException {
		TaskResource taskDetails = new TaskResource();
		taskDetails.setTaskId("1");
		taskDetails.setEndDate(new Date());
		taskDetails.setStartDate(new Date());
		taskDetails.setTaskName("Task-1");
		taskDetails.setParentTask("");
		taskDetails.setPriority("10");
		taskDetails.setUserId("1");

		when(taskRepository.save(taskDetails)).thenReturn(taskDetails);
		assertEquals("1", taskDetails.getTaskId());
	}

	@Test
	public void testEndTask() throws ProjectManagementException {
		TaskResource taskDetails = new TaskResource();
		taskDetails.setTaskId("1");
		taskDetails.setEndDate(new Date());
		taskDetails.setStartDate(new Date());
		taskDetails.setTaskName("Task-1");
		taskDetails.setParentTask("");
		taskDetails.setPriority("10");
		taskDetails.setUserId("1");
		taskDetails.setStatus(ProjectStatusEnum.COMPLETED.getStatus());

		when(taskRepository.findById(taskDetails.getTaskId())).thenReturn(Optional.of(taskDetails));
		when(taskRepository.save(taskDetails)).thenReturn(taskDetails);
		assertEquals(taskService.endTask(taskDetails.getTaskId()).getStatus(), taskDetails.getStatus());
	}

}
