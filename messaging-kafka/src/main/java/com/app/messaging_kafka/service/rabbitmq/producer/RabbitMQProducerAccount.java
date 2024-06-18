package com.app.messaging_kafka.service.rabbitmq.producer;

import com.app.messaging_kafka.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQProducerAccount {

    private final RabbitTemplate rabbitTemplate;

    public void publishData(String message){
        log.info("publish data to RabbitMQ = [{}]",message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "crud_routing_key", message);
    }
}
