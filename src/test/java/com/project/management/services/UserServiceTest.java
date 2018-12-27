package com.project.management.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.management.exception.ProjectManagementException;
import com.project.management.repository.UserRepository;
import com.project.management.resources.UserResource;


/**
 * @author Dhiraj Asati
 *
 */
@ActiveProfiles("junit")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	private UserRepository userRepository;

	@MockBean
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUsers() throws ProjectManagementException {
		when(userRepository.findAll()).thenReturn(new ArrayList<>());
		assertTrue(userService.getUsers().size() == 0);
	}

	@Test
	public void testAddUser() throws ProjectManagementException {
		UserResource userDetails = new UserResource();
		userDetails.setUserId("1");
		userDetails.setEmployeeId("1");
		userDetails.setFirstName("First Name");
		userDetails.setLastName("Last Name");
		userDetails.setProjectId("1");

		when(userRepository.save(userDetails)).thenReturn(userDetails);
		assertEquals("1", userDetails.getUserId());
	}

}
