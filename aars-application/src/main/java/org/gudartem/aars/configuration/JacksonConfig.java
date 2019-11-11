package org.gudartem.aars.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Configuration
public class JacksonConfig {
    @Bean
    @ConditionalOnMissingBean
    public Jackson2ObjectMapperBuilderCustomizer commonJacksonCustomizer() {
        return builder -> {
            builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
            builder.featuresToDisable(WRITE_DATES_AS_TIMESTAMPS, ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
            builder.modulesToInstall(new Jdk8Module(), new JavaTimeModule());
        };
    }
}
