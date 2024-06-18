package com.app.messaging_kafka.processor;

import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.helper.KafkaCamelHelper;
import com.app.messaging_kafka.service.kafka.camel.CamelAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageProcessor implements Processor {

    private final CamelAccountService camelAccountService;

    @Override
    public void process(Exchange exchange) throws Exception {
        String message = exchange.getIn().getBody(String.class);
        log.info("Process message from Kafka with apache camel : {}",message);
        AccountRequestDTO requestDTO = KafkaCamelHelper.convertToAccount(message);
        this.camelAccountService.save(requestDTO);
    }
}
