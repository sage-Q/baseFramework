package com.gsgy.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = GahlxtDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "gahlxtSqlSessionFactory")
public class GahlxtDataSourceConfig {

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.gsgy.dao.gahlxt";
    static final String MAPPER_LOCATION = "classpath:mybatis/gahlxt/**/*Mapper.xml";

    @Value("${gsgy_gahlxt.url}")
    private String url;

    @Value("${gsgy_gahlxt.username}")
    private String user;

    @Value("${gsgy_gahlxt.password}")
    private String password;

    @Value("${gsgy_gahlxt.driver-class-name}")
    private String driverClass;

    @Value("${gsgy_gahlxt.type}")
    private String dbType;

    @Value("${gsgy_gahlxt.filters}")
    private String filters;

    @Bean(name = "gahlxtDataSource")
    @Primary
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDbType(dbType);
        dataSource.setFilters(filters);
        return dataSource;
    }

    @Bean(name = "gahlxtTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() throws SQLException  {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "gahlxtSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("gahlxtDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(GahlxtDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
