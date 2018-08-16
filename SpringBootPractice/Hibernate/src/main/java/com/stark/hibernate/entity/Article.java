package com.stark.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "articles")
@Getter
@Setter
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private int articleId;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private String category;
}