# User Aggregation Service

This is a Spring Boot-based service designed to aggregate user data from multiple databases and provide a single REST endpoint to retrieve the combined data. The application reads user data from any number of configured data sources and exposes them through an HTTP endpoint.

## Task Definition

The goal of this application is to provide a unified interface to query user data from multiple databases. The application allows for declarative configuration of the data sources in a YAML file.

### Main Endpoint

#### `GET /api/v1/users`

This endpoint retrieves user data aggregated from all configured data sources.

Example success response:
```json
[
  {
    "id": "example-user-id-1",
    "username": "user-1",
    "name": "User",
    "surname": "Userenko"
  },
  {
    "id": "example-user-id-2",
    "username": "user-2",
    "name": "Testuser",
    "surname": "Testov"
  }
]
```

Data Source Configuration

Data sources are defined in the application.yml file under data-sources. Each data source must specify details like the URL, table name, and column mappings.

Example configuration:

yaml

data-sources:
  - name: data-base-1
    strategy: postgres
    url: jdbc:postgresql://<db_host>:<db_port>/TestDB1
    table: users
    user: "testuser1"
    password: "testpass1"
    mapping:
      id: user_id
      username: login
      name: first_name
      surname: last_name
  - name: data-base-2
    strategy: postgres
    url: jdbc:postgresql://<db_host>:<db_port>/TestDB2
    table: user_table
    user: "testuser2"
    password: "testpass2"
    mapping:
      id: ldap_login
      username: ldap_login
      name: name
      surname: surname

Project Setup

    Clone the repository:

    bash

git clone <repository_url>
cd user-aggregation-service

Build the project:

bash

./mvnw clean install

Run the application:

bash

    ./mvnw spring-boot:run

    Access the OpenAPI documentation at http://localhost:8080/swagger-ui.html.

Features

    Spring Boot Application: Built with Spring Boot for rapid development and dependency injection.
    OpenAPI Integration: Provides Swagger documentation for easy interaction with the endpoint.
    Declarative Data Source Configuration: Data sources are configured via YAML, enabling easy addition or removal of databases without code changes.
    Caching Specifications: Includes a caching mechanism to optimize query filters.
    Multi-tenant Support: Can handle multiple data sources dynamically by mapping configurations for each source.

Running Tests

The project includes integration tests that use Testcontainers for PostgreSQL. These tests simulate a multi-database environment to validate that the service can retrieve and aggregate data across multiple sources.

Run tests with:

bash

./mvnw test

Example Test Configuration

Example integration tests are defined using the @Testcontainers annotation and @DataJpaTest to leverage Testcontainers and JPA for repository testing.
Usage Example

Retrieve all users from configured databases:

bash

curl -X GET http://localhost:8080/api/v1/users
