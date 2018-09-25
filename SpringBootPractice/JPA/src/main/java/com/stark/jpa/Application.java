package com.stark.jpa;

import com.stark.jpa.common.repository.BaseRepositoryFactoryBean;
import com.stark.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)//指定自己的工厂类
public class Application {

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
