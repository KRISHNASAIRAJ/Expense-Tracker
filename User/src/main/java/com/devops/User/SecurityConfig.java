package com.devops.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/register", "/user/login").permitAll() // Allow register & login
                        .anyRequest().authenticated()) // Secure other endpoints
                .formLogin(form -> form.disable()) // Disable form login
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic Auth

        return http.build();
    }

    // Define a dummy user to bypass Spring Security’s default authentication
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password("{noop}password") // `{noop}` means plain text (no encoding)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}