package com.example.Spring_security.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails student= User.builder()
                                .username("Sai")
                                .password(getPasswordEncoder().encode("Sai@123"))
                                .roles("STUDENT")
                                .build();
        UserDetails admin= User.builder()
                .username("Sasi")
                .password(getPasswordEncoder().encode("Sasi@123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(student,admin);

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                .authorizeHttpRequests
                (       authorize -> authorize
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/student/**")
                        .hasAnyRole("STUDENT","ADMIN")
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated()              // All other requests need authentication
                )
                .csrf
                (
                        csrf -> csrf
                        .ignoringRequestMatchers("/public/**")
                )
                .formLogin(Customizer.withDefaults());  // Default form login
        return http.build();
    }
}
