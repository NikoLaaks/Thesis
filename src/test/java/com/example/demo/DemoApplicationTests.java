package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Answer;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Set;

import org.springframework.http.MediaType;

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

	private Long taskId;
	private Long answerId;

	@BeforeEach
	void setup() {
		Role teacherRole = roleRepository.findByName(RoleName.TEACHER).orElseThrow();
		User teacher = new User();
		teacher.setUserName("teacher1");
		teacher.setPassword("password");
		teacher.setRoles(Set.of(teacherRole));

		Role studentRole = roleRepository.findByName(RoleName.STUDENT).orElseThrow();
		User student = new User();
		student.setUserName("student1");
		student.setPassword("password");
		student.setRoles(Set.of(studentRole));

		userRepository.save(teacher);
		userRepository.save(student);

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

	@Test
	void contextLoads() {
	}

}
