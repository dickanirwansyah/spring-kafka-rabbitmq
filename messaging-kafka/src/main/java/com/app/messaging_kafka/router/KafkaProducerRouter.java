package com.app.messaging_kafka.router;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
         from("direct:start")
                 .log("send data to kafka : ${body}")
                 .to("kafka:my-topic?brokers={{camel.component.kafka.brokers}}");
    }
}
