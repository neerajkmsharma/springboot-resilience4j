package com.avinya.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.avinya.application.exception.ArticleNotFoundException;
import com.avinya.application.model.Article;

@Component
public class ArticleClient {

  @Autowired
  private RestTemplate restTemplate;

  public Article getArticles(final Long id) throws ArticleNotFoundException {

    final String url = "http://localhost:9090/sim/api/v1/articles/" + id;

    final Article article = restTemplate.getForObject(url, Article.class);

    if (article == null) {
      throw new ArticleNotFoundException("ArticleNotFoundException with id " + id + " not found.");
    }
    return article;
  }
}
