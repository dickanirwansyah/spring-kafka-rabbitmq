package com.app.messaging_kafka.service.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerAccount {

    @Value("${topic.name}")
    private String accountTopic;

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void publishData(String message){
        log.info("publish data to Kafka = [{}]", message);
        kafkaTemplate.send(accountTopic,message);
    }
}
