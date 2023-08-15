package com.avinya.application.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class ApplicationConfig {

  @Bean
  RestTemplate restTemplate(final RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  OpenAPI openAPI() {
    return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
      .info(new Info().title("SpringBoot Resilience4j API")
        .description("SpringBoot Resilience4j API")
        .version("1.0")
        .contact(new Contact().name("Neeraj Kumar Sharma")
          .email("neerajkmsharma@gmail.com")
          .url("https://www.linkedin.com/in/neerajkmsharma"))
        .license(new License().name("License of API")
          .url("API license URL")));
  }
}
