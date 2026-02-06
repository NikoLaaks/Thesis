package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String sanoHello() {
        return "Spring Boot toimii ja endpoint /hello löytyi!";
    }

}
