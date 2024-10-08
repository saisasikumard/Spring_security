package com.example.Spring_security.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll()  // Allow public access to these URLs
                        .anyRequest().authenticated()              // All other requests need authentication
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/public/**")     // Disable CSRF protection for public endpoints only
                )
                .formLogin(Customizer.withDefaults());  // Default form login

        return http.build();
    }
}
