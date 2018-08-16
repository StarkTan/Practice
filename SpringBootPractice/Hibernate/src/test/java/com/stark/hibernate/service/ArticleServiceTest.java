package com.stark.hibernate.service;

import com.stark.hibernate.Application;
import com.stark.hibernate.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ArticleServiceTest {
    @Resource
    private ArticleService articleService;

    /**
     * 测试增删改查，因为数据库自增长，删除之后无法测试两次，需要重新运行sql
     */
    @Test
    public void test() {
        Article article = articleService.getArticleById(1);
        assertThat(article.getTitle(), is("Java Concurrency"));

        List<Article> list = articleService.getAllArticles();
        assertThat(list, notNullValue());
        assertThat(list.size(), is(3));

        boolean flag = articleService.addArticle(article);
        assertThat(flag, is(false));

        article.setTitle("Python Concurrency");
        articleService.updateArticle(article);
        Article article1 = articleService.getArticleById(1);
        assertThat(article1.getTitle(), is("Python Concurrency"));


        articleService.deleteArticle(1);
        Article article2 = articleService.getArticleById(1);
        assertThat(article2, nullValue());
    }
}