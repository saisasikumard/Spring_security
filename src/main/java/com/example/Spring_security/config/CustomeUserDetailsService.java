package com.example.Spring_security.config;

import com.example.Spring_security.Person;
import com.example.Spring_security.PersonRepo;
import com.example.Spring_security.UserDetailsCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomeUserDetailsService implements UserDetailsService {
    @Autowired
    PersonRepo personRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person=personRepo.findByUserName(username);
        if(person==null){
            throw new RuntimeException("User Not exist");
        }
        return new UserDetailsCreator(person);
    }
}
