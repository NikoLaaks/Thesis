package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;

        public CustomUserDetailsService(UserRepository userRepository) {
                this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username)
                        throws UsernameNotFoundException {

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                String[] roles = user.getRoles().stream()
                                .map(role -> role.getName().name())
                                .toArray(String[]::new);

                return org.springframework.security.core.userdetails.User
                                .withUsername(user.getUserName())
                                .password(user.getPassword())
                                .roles(roles)
                                .build();
        }
}
