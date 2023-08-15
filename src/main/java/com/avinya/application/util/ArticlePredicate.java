package com.avinya.application.util;

import java.util.function.Predicate;

import com.avinya.application.model.Article;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArticlePredicate implements Predicate<Article> {

  @Override
  public boolean test(final Article article) {
    log.info("Condition predicate is called.");
    return article.id()
      .equals(0L);
  }
}
