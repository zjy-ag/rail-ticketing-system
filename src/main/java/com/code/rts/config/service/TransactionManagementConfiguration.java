package com.code.rts.config.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 对应spring-service里面的transactionManage
 * 继承TransactionManagermentConfigurer是因为开启annotation-driven
 */

@Configuration
//首先使用注解 @EnableTransactionManagement开启事务支持后
//在Service方法上添加注解@Transactional便可
@EnableTransactionManagement
public class TransactionManagementConfiguration implements TransactionManagementConfigurer {
    @Resource
    //注入DataSourceConfiguration里边的DataSource，用过createDataSource获取
    private DataSource dataSource;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
