package com.example.Spring_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
            PersonRepo personRepo;
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to public place..";
    }
    @PostMapping("/add")
    public String addStudent(@RequestBody Person person){

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepo.save(person);
        return "Person added";
    }
}
