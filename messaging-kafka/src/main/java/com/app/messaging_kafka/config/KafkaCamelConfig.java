package com.app.messaging_kafka.config;

import org.apache.camel.CamelContext;
import org.apache.camel.Configuration;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;

@Configuration
public class KafkaCamelConfig {

    @Bean
    public CamelContext camelContext(){
        return new DefaultCamelContext();
    }

    @Bean
    CamelContextConfiguration contextConfiguration(){
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext camelContext) {

            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {

            }
        };
    }
}
