package com.example.Spring_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/welcome")
    public String welcom(){
        return "Welcome to Student place..";
    }
}
