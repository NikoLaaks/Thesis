package com.example.demo;

import java.util.Set;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	private final UserService userService;

	DemoApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// CommandLineRunner roolien luontiin
	@Bean
	@Order(1)
	CommandLineRunner initRoles(RoleRepository roleRepository) {
		return args -> {
			for (RoleName roleName : RoleName.values()) {
				if (roleRepository.findByName(roleName).isEmpty()) {
					roleRepository.save(new Role(roleName));
				}
			}
		};
	}

	// CommandLineRunner admin käyttäjän luontiin
	@Bean
	@Order(2)
	CommandLineRunner initAdminUser(UserRepository userRepository) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				UserRequest userRequest = new UserRequest();
				userRequest.setUsername("admin");
				userRequest.setPassword("admin");
				userRequest.setName("admin");
				userRequest.setRoles(Set.of(RoleName.ADMIN));
				userService.createUser(userRequest);
			}
		};
	}

}
