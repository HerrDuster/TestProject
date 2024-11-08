package com.comparus.configuration.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import static org.springframework.boot.jdbc.DatabaseDriver.POSTGRESQL;

@Primary
@Component
@ConditionalOnClass(HikariDataSource.class)
public class HikariDataSourceProvider implements DataSourceProvider {

    private static final Integer HIKARI_MAX_POOL_SIZE = 10;

    @Override
    public DataSource buildDataSource(MultiTenantDataSourceProperties.DataSourceDefinition dataSourceDefinition) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dataSourceDefinition.getUrl());
        hikariConfig.setUsername(dataSourceDefinition.getUser());
        hikariConfig.setPassword(dataSourceDefinition.getPassword());
        //NOTE: The driver class may be implemented via properties and provided from outside
        hikariConfig.setDriverClassName(POSTGRESQL.getDriverClassName());
        hikariConfig.setMaximumPoolSize(HIKARI_MAX_POOL_SIZE);
        hikariConfig.setPoolName("TenantPool-" + dataSourceDefinition.getName() + "-" + hikariConfig.hashCode());
        return new HikariDataSource(hikariConfig);
    }
}
