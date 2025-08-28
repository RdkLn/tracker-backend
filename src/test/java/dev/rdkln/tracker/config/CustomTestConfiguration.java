package dev.rdkln.tracker.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class CustomTestConfiguration {

    @Bean
    PostgreSQLContainer postgreSQLContainer(){
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:17-alpine"));
    }
}
