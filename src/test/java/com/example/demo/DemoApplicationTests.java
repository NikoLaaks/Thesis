package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityConfig;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;

	private Long taskId;
	private Long answerId;

	@BeforeEach
	void setup() {
		Role teacherRole = roleRepository.findByName(RoleName.TEACHER).orElseThrow();
		User teacher = new User();
		teacher.setUserName("teacher1");
		teacher.setPassword(passwordEncoder.encode("password"));
		teacher.setRoles(Set.of(teacherRole));

		Role studentRole = roleRepository.findByName(RoleName.STUDENT).orElseThrow();
		User student = new User();
		student.setUserName("student1");
		student.setPassword(passwordEncoder.encode("password"));
		student.setRoles(Set.of(studentRole));

		// Tämä käyttäjä luodaan oikealla service funktiolla
		UserRequest userRequest = new UserRequest();
		userRequest.setUsername("student2");
		userRequest.setPassword("password");
		userRequest.setName("testioppilas");
		userRequest.setRoles(Set.of(RoleName.STUDENT));
		userService.createUser(userRequest);

		userRepository.save(teacher);
		userRepository.save(student);
		userRepository.flush();

		Task task = new Task();
		task.setTitle("Test Task");
		task.setUser(teacher);
		Task savedTask = taskRepository.save(task);
		taskId = savedTask.getId();

		Answer answer = new Answer();
		answer.setContent("Initial answer");
		answer.setUser(student);
		answer.setTask(task);
		Answer savedAnswer = answerRepository.save(answer);
		answerId = savedAnswer.getId();
	}

	// Testaa, että vastauksen omistaja (student1) voi päivittää vastauksensa
	@Test
	void ownerCanUpdateAnswer() throws Exception {
		mockMvc.perform(put("/api/answers/" + answerId)
				.with(user("student1").roles("STUDENT"))
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						    {
						        "content": "Updated answer"
						    }
						"""))
				.andExpect(status().isOk());
		Answer updated = answerRepository.findById(answerId).orElseThrow();
		assertEquals("Updated answer", updated.getContent());
	}

	// Testaa, että toinen opiskelija (student2) ei voi päivittää toisen opiskelijan
	// (student1) vastausta
	@Test
	void otherStudentCannotUpdateAnswer() throws Exception {
		mockMvc.perform(put("/api/answers/" + answerId)
				.with(user("student2").roles("STUDENT"))
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
							{
								"content": "This should not work"
							}
						"""))
				.andExpect(status().isForbidden());
	}

	// Testaa, ettei kirjautuminen onnistu väärällä salasanalla
	@Test
	void cantLoginWithFakeCredentials() throws Exception {
		mockMvc.perform(post("/api/auth/login")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
							{
								"username": "admin",
								"password": "fakepassword"
							}
						"""))
				.andExpect(status().isUnauthorized());
	}

	// Testaa tokenin generoinnin toimivuus
	@Test
	void jwtTokenGenerationTest() throws Exception {
		String response = mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.with(csrf())
				.content("""
						{
							"username": "student2",
							"password": "password"
						}
						"""))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(response);
		System.out.println("LOGIN RESPONSE:");
		System.out.println(response);
		String token = jsonNode.get("token").asText();

		mockMvc.perform(get("/api/tasks")
				.header("Authorization", "Bearer " + token)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void contextLoads() {
	}

}
