package com.avinya.application.exception;

public class ArticleNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ArticleNotFoundException(final String message) {
    super(message);
  }
}
