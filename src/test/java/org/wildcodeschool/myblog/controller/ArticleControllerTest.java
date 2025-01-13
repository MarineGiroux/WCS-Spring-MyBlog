package org.wildcodeschool.myblog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wildcodeschool.myblog.model.Article;
import org.wildcodeschool.myblog.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArticleControllerTest {


    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll();
    }

    @Test
    void test_findByTitle_found() {
        // Given
        Article article = new Article();
        article.setTitle("Test Title");
        article.setContent("Test Content");
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        articleRepository.save(article);

        // When
        List<Article> result = articleRepository.findByTitle("Test Title");

        // Then
        assertFalse(result.isEmpty());
        assertEquals("Test Title", result.get(0).getTitle());
    }

    @Test
    void test_findByCreateDateAfter_found() {
        // Given
        Article article = new Article();
        article.setTitle("Future Article");
        article.setContent("Content");
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        articleRepository.save(article);

        // When
        List<Article> result = articleRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1));

        // Then
        assertFalse(result.isEmpty());
    }

    @Test
    void test_findTop5ByOrderByCreateDateDesc() {
        // Given
        for (int i = 0; i < 7; i++) {
            Article article = new Article();
            article.setTitle("Article " + i);
            article.setContent("Content " + i);
            article.setCreatedAt(LocalDateTime.now().minusDays(i));
            article.setUpdatedAt(LocalDateTime.now());
            articleRepository.save(article);
        }

        // When
        List<Article> result = articleRepository.findTop5ByOrderByCreatedAtDesc();

        // Then
        assertEquals(5, result.size());
        assertTrue(result.get(0).getCreatedAt().isAfter(result.get(4).getCreatedAt()));
    }

}