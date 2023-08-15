package com.avinya.application.model;

public record Article(Long id, String title, String category, String writer, int likes) {
}
