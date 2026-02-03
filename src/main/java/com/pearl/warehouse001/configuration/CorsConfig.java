package com.pearl.warehouse001.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")      // allow all endpoints
                        .allowedOrigins("http://localhost:4200")    // allow all origins, use "*"  for development
                        .allowedMethods("*")    // allow all HTTP methods (GET, POST, etc.)
                        .allowedHeaders("*");   // allow all headers
            }
        };
    }
}
