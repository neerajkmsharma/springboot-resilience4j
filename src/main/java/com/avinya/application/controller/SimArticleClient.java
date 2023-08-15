package com.avinya.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avinya.application.model.Article;

@RestController
@RequestMapping("/sim/api/v1/articles")
public class SimArticleClient {

  @GetMapping("/{id}")
  public ResponseEntity<?> getArticleById(@PathVariable final String id) {

    Article article = null;
    switch (id) {
      case "1" -> article = new Article(1L, "Advaita Vedanta", "Philosophy", "Shankar Acharya", 896);
      case "2" -> article = new Article(2L, "SpringBoot Log4J", "Technology", "Dana Brown", 786);
      case "3" -> article = new Article(3L, "SpringBoot Resilience4j", "Technology", "Anna Smith", 500);
      default -> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("No Article is found on the basis of given seravh criteria");
      }
    }

    return ResponseEntity.ok(article);
  }
}
