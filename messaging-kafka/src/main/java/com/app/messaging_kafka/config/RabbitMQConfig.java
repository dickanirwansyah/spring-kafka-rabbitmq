package com.app.messaging_kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "crud_queue";
    public static final String EXCHANGE_NAME = "crud_exchange";

    @Bean
    public Queue queue(){
        log.info("initialize bean queue RabbitMQ..");
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange topicExchange(){
        log.info("initialize bean topic exchange RabbitMQ..");
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange topicExchange){
        log.info("initilaize bean binding RabbitMQ..");
        return BindingBuilder.bind(queue).to(topicExchange).with("crud_routing_key");
    }
}
