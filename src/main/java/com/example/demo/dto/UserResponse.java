package com.example.demo.dto;

import java.util.Set;

import com.example.demo.entity.RoleName;

public class UserResponse {

    private Long id;
    private String name;
    private Set<RoleName> roles;

    public UserResponse(Long id, String name, Set<RoleName> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public UserResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

}
