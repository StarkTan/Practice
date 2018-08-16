package com.stark.hibernate.repository;

import com.stark.hibernate.entity.Article;

import java.util.List;

public interface ArticleRepository {
    List<Article> getAllArticles();

    Article getArticleById(int articleId);

    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(int articleId);

    boolean articleExists(String title, String category);
}
