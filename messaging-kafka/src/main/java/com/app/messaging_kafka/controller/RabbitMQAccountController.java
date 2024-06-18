package com.app.messaging_kafka.controller;

import com.app.messaging_kafka.dto.RestResponse;
import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.service.rabbitmq.AccountServiceRabbitMQ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rabbitmq")
@RequiredArgsConstructor
public class RabbitMQAccountController {

    private final AccountServiceRabbitMQ accountServiceRabbitMQ;

    @PostMapping(value = "/save")
    public ResponseEntity<RestResponse> save(@RequestBody AccountRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOK(accountServiceRabbitMQ.save(requestDTO)));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<RestResponse> update(@RequestBody AccountRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOK(accountServiceRabbitMQ.update(requestDTO)));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<RestResponse> update(@PathVariable("id")Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOK(accountServiceRabbitMQ.delete(id)));
    }
}
