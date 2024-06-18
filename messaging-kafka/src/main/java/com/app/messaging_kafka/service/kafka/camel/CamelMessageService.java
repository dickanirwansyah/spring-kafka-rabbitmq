package com.app.messaging_kafka.service.kafka.camel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CamelMessageService {

    private final ProducerTemplate producerTemplate;

    public String sendMessage(String message){
        producerTemplate.sendBody("direct:start",message);
        return "Message sent to Kafka with Apache Camel";
    }
}
