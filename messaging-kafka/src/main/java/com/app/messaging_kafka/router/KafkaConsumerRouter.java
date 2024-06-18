package com.app.messaging_kafka.router;

import com.app.messaging_kafka.processor.KafkaMessageProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerRouter extends RouteBuilder {

    private final KafkaMessageProcessor kafkaMessageProcessor;

    @Override
    public void configure() throws Exception {
        from("kafka:my-topic?brokers={{camel.component.kafka.brokers}}&groupId=group_id")
                .log("Message received from kafka : ${body}")
                .process(kafkaMessageProcessor);
    }
}
