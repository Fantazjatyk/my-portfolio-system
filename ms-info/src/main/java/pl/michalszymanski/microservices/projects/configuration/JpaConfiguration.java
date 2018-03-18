package pl.michalszymanski.microservices.projects.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "personal-info.database")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
