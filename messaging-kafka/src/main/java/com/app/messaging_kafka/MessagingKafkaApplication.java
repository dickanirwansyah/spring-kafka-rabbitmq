package com.app.messaging_kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MessagingKafkaApplication {

	public static void main(String[] args) {
		log.info("starter messaging spring boot camel with kafka..");
		SpringApplication.run(MessagingKafkaApplication.class, args);
	}

}
