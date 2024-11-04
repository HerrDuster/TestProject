package com.comparus.configuration.datasource;

import com.comparus.configuration.datasource.MultiTenantDataSourceProperties.DataSourceDefinition;

import javax.sql.DataSource;

public interface DataSourceProvider {

    DataSource buildDataSource(DataSourceDefinition dataSourceDefinition);
}
