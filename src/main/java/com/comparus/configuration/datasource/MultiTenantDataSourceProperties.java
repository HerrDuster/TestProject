package com.comparus.configuration.datasource;

import com.comparus.exception.DataSourceNamesMustBeUniqueException;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

@Data
@Validated
@ConfigurationProperties(prefix = "com.baidak.test-comparus")
public class MultiTenantDataSourceProperties {

    @NotNull
    @Size(min = 1, message = "At least one data source must be initialized via properties!")
    private final List<DataSourceDefinition> dataSourceDefinitions;

    @PostConstruct
    private void assertThatDatabaseNamesAreDistinct() {
        List<String> names = dataSourceDefinitions.stream()
                .map(DataSourceDefinition::getName)
                .toList();
        Set<String> uniqueNames = Set.copyOf(names);
        if (uniqueNames.size() != names.size()) {
            throw new DataSourceNamesMustBeUniqueException();
        }
    }

    @Data
    public static class DataSourceDefinition {

        @NotBlank
        private final String name;

        @NotBlank
        private final String url;

        @NotBlank
        private final String table;

        @NotBlank
        private final String user;

        @NotBlank
        private final String password;

        @NotNull
        private final Mapping mapping;
    }

    @Data
    public static class Mapping {

        @NotBlank
        private final String id;

        @NotBlank
        private final String username;

        @NotBlank
        private final String name;

        @NotBlank
        private final String surname;
    }
}
