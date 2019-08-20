package org.gudartem.aars.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class DbConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource cimDevDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(cimDevDataSource())
                .baselineOnMigrate(true)
                .cleanOnValidationError(true)
                .locations("classpath:db.migration.postgresql")
                .schemas("public")
                .load();

        flyway.migrate();
        return flyway;
    }

    @Primary
    @Bean("transactionAwareDataSource")
    @DependsOn(value = "cimDevDataSource")
    @Qualifier(value = "transactionAwareDataSource")
    public TransactionAwareDataSourceProxy transactionAwareDataSource(@Autowired @Qualifier("cimDevDataSource") DataSource dataSource) {
        return new TransactionAwareDataSourceProxy(dataSource);
    }

    @Bean
    @DependsOn(value = "cimDevDataSource")
    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("cimDevDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
