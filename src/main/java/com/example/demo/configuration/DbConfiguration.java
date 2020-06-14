package com.example.demo.configuration;

import lombok.Data;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name = "mysql.enable", havingValue = "true")
@EnableConfigurationProperties
public class DbConfiguration {

    @Bean
    @Qualifier("mysqlDataSource")
    public DataSource dataSource(PropertiesConfiguration configuration) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(configuration.getDriverClassName());
        dataSource.setUrl(configuration.getUrl());
        dataSource.setUsername(configuration.getUsername());
        dataSource.setPassword(configuration.getPassword());
        //1)指定数据库连接池中初始化连接数的个数
        dataSource.setInitialSize(2);
        //2)指定最大的连接数:同一时刻同时向数据库申请的连接数
        dataSource.setMaxTotal(3);
        //3)指定连接波峰过后，保持的最少的连接数量
        dataSource.setMinIdle(2);
        //4)申请一次的最少连接时长
        dataSource.setMaxWaitMillis(1000);
        return dataSource;
    }

    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(@Autowired
                                                              @Qualifier("mysqlDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Springboot里面已经注册过这个bean了
     */
//    @Bean
//    TransactionManager transactionManager(@Autowired
//                                          @Qualifier("mysqlDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }


    @Component
    @ConfigurationProperties(prefix = "spring.datasource")
    @Data
    public class PropertiesConfiguration {
        private String driverClassName;
        private String url;
        private String username;
        private String password;
    }
}
