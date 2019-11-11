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
    DataSource aarsDevDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(aarsDevDataSource())
                .baselineOnMigrate(true)
//                .cleanOnValidationError(true)
                .locations("classpath:db.migration.postgresql")
                .schemas("public")
                .load();

        flyway.migrate();
        return flyway;
    }

    @Primary
    @Bean("transactionAwareDataSource")
    @DependsOn(value = "aarsDevDataSource")
    @Qualifier(value = "transactionAwareDataSource")
    public TransactionAwareDataSourceProxy transactionAwareDataSource(@Autowired @Qualifier("aarsDevDataSource") DataSource dataSource) {
        return new TransactionAwareDataSourceProxy(dataSource);
    }

    @Bean
    @DependsOn(value = "aarsDevDataSource")
    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("aarsDevDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
