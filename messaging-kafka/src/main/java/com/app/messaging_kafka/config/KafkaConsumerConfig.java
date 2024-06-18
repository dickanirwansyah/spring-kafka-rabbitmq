package com.app.messaging_kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServer;

    @Value("${spring.kafka.producer.group-id}")
    private String kafkaGroupId;

    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        log.info("initialize bean consumerFactory..");
        Map<String,String> consumerMapFactory = new HashMap<>();
        consumerMapFactory.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
        consumerMapFactory.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        return new DefaultKafkaConsumerFactory(consumerMapFactory, new StringDeserializer(), new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory(){
        log.info("initialize bean concurent kafka listener..");
        ConcurrentKafkaListenerContainerFactory<String,String> concurrentKafkFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkFactory.setConsumerFactory(consumerFactory());
        return concurrentKafkFactory;
    }
}
