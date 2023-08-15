package com.avinya.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.avinya.application.config.ArticleClient;
import com.avinya.application.exception.ArticleNotFoundException;
import com.avinya.application.model.Article;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleService {

  @Autowired
  private ArticleClient articleClient;

  @Retry(
    name = "simpleRetry")
  public Article getArticles(final Long id) {
    return findArticles(id);
  }

  @Retry(
    name = "retryWithEventDetails")
  public Article getArticlesWithRetryEventDetails(final Long id) {
    return findArticles(id);
  }

  @Retry(
    name = "simpleRetry",
    fallbackMethod = "getArticlesFallbackMethod")
  public Article getArticlesWithFallback(final Long id) {
    return findArticles(id);
  }

  @Retry(
    name = "customRetryConfig")
  public Article getArticlesWithCustomRetryConfig(final Long id) {
    return findArticles(id);
  }

  @Retry(
    name = "retryOnException")
  public Article getArticlesRetryOnException(final Long id) {
    return findArticles(id);
  }

  @Retry(
    name = "retryOnConditionalPredicate")
  public Article getArticlesRetryOnConditionalPredicate(final Long id) {
    try {
      return findArticles(id);
    }
    catch (final ArticleNotFoundException articleNotFoundException) {
      log.error("Article not found exception encountered. Returning default value", articleNotFoundException);

      return new Article(0L, "Default Article ", "Default Technology", "Default", 0);
    }
  }

  @Retry(
    name = "retryOnExceptionPredicate")
  public Article getArticlesRetryOnExceptionPredicate(final Long id) {
    return findArticles(id);
  }

  @Retry(
    name = "retryUsingExponentialBackoff")
  public Article getArticlesRetryUsingExponentialBackoff(final Long id) {
    return findArticles(id);
  }

  @Retry(
    name = "retryUsingRandomizedWait")
  public Article getArticlesRetryUsingRandomizedWait(final Long id) {
    return findArticles(id);
  }

  private Article findArticles(final Long id) {
    Article article = null;
    try {
      article = articleClient.getArticles(id);
    }
    catch (final HttpServerErrorException httpServerErrorException) {
      log.error("Received HTTP server error exception while fetching the article details. Error Message: {}",
        httpServerErrorException.getMessage());
      throw httpServerErrorException;
    }
    catch (final HttpClientErrorException httpClientErrorException) {
      log.error("Received HTTP client error exception while fetching the article details. Error Message: {}",
        httpClientErrorException.getMessage());
      throw httpClientErrorException;
    }
    catch (final ResourceAccessException resourceAccessException) {
      log.error("Received Resource Access exception while fetching the article details.");
      throw resourceAccessException;
    }
    catch (final Exception exception) {
      log.error("Unexpected error encountered while fetching the article details");
      throw exception;
    }
    return article;
  }
}
