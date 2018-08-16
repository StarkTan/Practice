package com.stark.hibernate.service;

import com.stark.hibernate.entity.Article;
import com.stark.hibernate.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {

    @Resource
    private ArticleRepository repository;

    public Article getArticleById(int articleId) {
        return repository.getArticleById(articleId);
    }

    public List<Article> getAllArticles() {
        return repository.getAllArticles();
    }

    public synchronized boolean addArticle(Article article) {
        if (repository.articleExists(article.getTitle(), article.getCategory())) {
            return false;
        } else {
            repository.addArticle(article);
            return true;
        }
    }

    public void updateArticle(Article article) {
        repository.updateArticle(article);
    }

    public void deleteArticle(int articleId) {
        repository.deleteArticle(articleId);
    }
}
