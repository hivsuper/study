package com.bcp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Qualifier("datasource")
    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public BasicDataSource datasource() {
        BasicDataSource dataSource = (BasicDataSource) DataSourceBuilder.create().type(BasicDataSource.class).build();
        dataSource.setMaxWaitMillis(60000);
        dataSource.setMaxTotal(20);
        dataSource.setMaxIdle(20);
        return dataSource;
    }
}