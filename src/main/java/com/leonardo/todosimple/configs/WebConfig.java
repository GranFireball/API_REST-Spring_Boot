package com.leonardo.todosimple.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  
  public void addCorsMappings(CorsRegistry registry){
    registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
  }

}
