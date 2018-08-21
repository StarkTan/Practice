package com.stark.multisource.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DruidDataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.jpa")
    @Bean(name = "jpaDataSource")
    @Qualifier("jpaDataSource")//指定唯一，防止注入冲突
    @Primary //主数据源
    public DataSource jpaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.hibernate")
    @Bean(name = "hibernateDataSource")
    @Qualifier("hibernateDataSource")
    public DataSource bookDataSource() {
        return DataSourceBuilder.create().build();
    }
}
