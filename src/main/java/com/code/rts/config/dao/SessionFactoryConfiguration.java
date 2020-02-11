package com.code.rts.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SessionFactoryConfiguration {

    //mybatis-config.xml配置文件的路径
    private static String mybatisConfigFile;

    @Value("${mybatis_config_file}")
    public void setMybatisConfigFile(String mybatisConfigFile) {
        SessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
    }

    //mybatis mapper文件所在路径
    private static String mapperPath;
    @Value("${mapper_path}")
    public void setMapperPath(String mapperPath) {
        SessionFactoryConfiguration.mapperPath = mapperPath;
    }




    //实体类所在的package
    @Value("type_alias_package")
    private String typeAliasPackage;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置mybatis configuration的扫描路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));
        //设置mapper的扫描路径
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        //设置DataSource
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置typeAlias
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
        return sqlSessionFactoryBean;
    }
}
