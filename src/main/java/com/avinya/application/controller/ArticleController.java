package com.avinya.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avinya.application.model.Article;
import com.avinya.application.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/articles")
@Slf4j
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping("/{id}")
  public ResponseEntity<Article> getArticleById(@PathVariable final Long id, @RequestParam(
    defaultValue = "simple-retry") final String retryType) {

    Article article = null;

    switch (retryType) {

      case "simple-retry" -> {
        log.info("Simple retry example");
        article = articleService.getArticles(id);
      }
      case "retry-on-exception" -> {
        log.info("Retry on configured exceptions example");
        article = articleService.getArticlesRetryOnException(id);
      }
      case "retry-on-exception-predicate" -> {
        log.info("Retry on exception predicate example");
        article = articleService.getArticlesRetryOnExceptionPredicate(id);
      }
      case "retry-on-conditional-predicate" -> {
        log.info("Retry on conditional predicate example");
        article = articleService.getArticlesRetryOnConditionalPredicate(id);
      }
      case "retry-using-exponential-backoff" -> {
        log.info("Retry using exponential backoff example");
        article = articleService.getArticlesRetryUsingExponentialBackoff(id);
      }
      case "retry-using-randomized-wait" -> {
        log.info("Retry using randomized wait example");
        article = articleService.getArticlesRetryUsingRandomizedWait(id);
      }
      case "retry-with-fallback" -> {
        log.info("Retry with fallback example");
        article = articleService.getArticlesWithFallback(id);
      }
      case "retry-with-custom-config" -> {
        log.info("Retry with custom config example");
        article = articleService.getArticlesWithCustomRetryConfig(id);
      }
      case "retry-with-event-details" -> {
        log.info("Retry with event details example");
        article = articleService.getArticlesWithRetryEventDetails(id);
      }
      default -> article = null;

    }
    return ResponseEntity.ok(article);
  }
}
