package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.repository.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// CommandLineRunner roolien luontiin
	@Bean
	CommandLineRunner initRoles(RoleRepository roleRepository) {
		return args -> {
			for (RoleName roleName : RoleName.values()) {
				if (roleRepository.findByName(roleName).isEmpty()) {
					roleRepository.save(new Role(roleName));
				}
			}
		};
	}

}
