package com.code.rts.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.beans.PropertyVetoException;

/**
 * 配置DataSource到IOC容器里面
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
@MapperScan("com.code.rts.dao")
public class DataSourceConfiguration {

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
     * 生成与spring-dao.xml对应的bean DataSource
     */
    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        /*生成DataSource实例*/
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //驱动
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUsername);
        dataSource.setPassword(jdbcPassword);

        /*配置c3p0连接池的私有属性*/
        dataSource.setMaxPoolSize(30);
        dataSource.setMinPoolSize(10);
        dataSource.setAutoCommitOnClose(false);
        dataSource.setCheckoutTimeout(10000);
        dataSource.setAcquireRetryAttempts(2);

        return dataSource;
    }
}