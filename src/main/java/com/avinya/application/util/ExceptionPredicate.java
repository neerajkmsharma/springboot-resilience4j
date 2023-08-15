package com.avinya.application.util;

import java.util.function.Predicate;

import com.avinya.application.exception.ArticleNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionPredicate implements Predicate<Throwable> {

  @Override
  public boolean test(final Throwable throwable) {

    log.info("Exception predicate is called.");

    return throwable instanceof ArticleNotFoundException;
  }
}
