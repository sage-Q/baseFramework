package com.gsgy.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = GgzzsbptDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "ggzzsbptSqlSessionFactory")
public class GgzzsbptDataSourceConfig {

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.gsgy.dao.ggzzsbpt";
    static final String MAPPER_LOCATION = "classpath:mybatis/ggzzsbpt/**/*Mapper.xml";

    @Value("${ggzzsbpt.url}")
    private String url;

    @Value("${ggzzsbpt.username}")
    private String user;

    @Value("${ggzzsbpt.password}")
    private String password;

    @Value("${ggzzsbpt.driver-class-name}")
    private String driverClass;

    @Value("${ggzzsbpt.type}")
    private String dbType;

    @Value("${ggzzsbpt.filters}")
    private String filters;

    @Bean(name = "ggzzsbptDataSource")
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

    @Bean(name = "ggzzsbptTransactionManager")
    public DataSourceTransactionManager transactionManager() throws SQLException  {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "ggzzsbptSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ggzzsbptDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(GgzzsbptDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
