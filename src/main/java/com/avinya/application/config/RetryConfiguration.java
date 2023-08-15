package com.avinya.application.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.avinya.application.exception.ArticleNotFoundException;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;

@Configuration
public class RetryConfiguration {

  @Bean
  Retry retryWithCustomConfig(final RetryRegistry retryRegistry) {
    final RetryConfig customConfig = RetryConfig.custom()
      .maxAttempts(3)
      .waitDuration(Duration.ofSeconds(2))
      .retryExceptions(HttpClientErrorException.class, HttpServerErrorException.class)
      .ignoreExceptions(ArticleNotFoundException.class)
      .build();

    return retryRegistry.retry("customRetryConfig", customConfig);
  }
}
