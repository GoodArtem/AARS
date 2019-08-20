package org.gudartem.aars.configuration;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameStyle;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
public class JooqConfiguration {

    @Bean("connectionProvider")
    @DependsOn(value = "transactionAwareDataSource")
    @Qualifier(value = "connectionProvider")
    public DataSourceConnectionProvider connectionProvider(
            @Autowired @Qualifier("transactionAwareDataSource") TransactionAwareDataSourceProxy transactionAwareDataSourceProxy
    ) {
        return new DataSourceConnectionProvider(transactionAwareDataSourceProxy);
    }

    @Bean("jooqConfig")
    @Primary
    @DependsOn(value = "connectionProvider")
    @Qualifier(value = "jooqConfig")
    public org.jooq.Configuration configuration(
            @Autowired @Qualifier("connectionProvider") DataSourceConnectionProvider dataSourceConnectionProvider
    ) {
        DefaultConfiguration conf = new DefaultConfiguration();
        conf.set(dataSourceConnectionProvider);
        conf.set(SQLDialect.POSTGRES_10);
        return conf;
    }

    @Bean("cimDslContext")
    @DependsOn(value = "jooqConfig")
    @Qualifier("cimDslContext")
    public DSLContext dsl(@Autowired @Qualifier("jooqConfig") org.jooq.Configuration configuration) {
        DSLContext context = new DefaultDSLContext(configuration);
        context.settings().setRenderNameStyle(RenderNameStyle.LOWER);
        context.settings().withReturnAllOnUpdatableRecord(true);
        return context;
    }
}
