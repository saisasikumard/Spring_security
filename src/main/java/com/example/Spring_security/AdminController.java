package com.example.Spring_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/welcome")
    public String welcom(){
	System.out.println("hello world");
        return "Welcome to Admin place..";
    }
}
