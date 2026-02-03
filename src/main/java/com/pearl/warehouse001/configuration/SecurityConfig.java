package com.pearl.warehouse001.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF for development/REST testing
                .csrf(csrf -> csrf.disable())

                // 2. Configure path permissions
                .authorizeHttpRequests(auth -> auth
                        // Allow Swagger UI and OpenAPI docs without login
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Allow your warehouse API for now (or change to authenticated())
                        .requestMatchers("/api/v1/warehouses/**").permitAll()
                        .requestMatchers("/api/v1/zones/**").permitAll()

                        // Everything else requires login
                        .anyRequest().authenticated()
                )

                // 3. Enable default form login so you can actually log in later
                .formLogin(form -> form.permitAll());

        return http.build();
    }
}