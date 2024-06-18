package com.app.messaging_kafka.controller;

import com.app.messaging_kafka.dto.RestResponse;
import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.helper.KafkaCamelHelper;
import com.app.messaging_kafka.service.kafka.camel.CamelMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/camel/kafka")
public class CamelAccountController {

    //ini api kafka with camel

    private final CamelMessageService camelMessageService;

    @PostMapping(value = "/save")
    public ResponseEntity<RestResponse> save(@RequestBody AccountRequestDTO requestDTO){
        String message = KafkaCamelHelper.convertObjToString(requestDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOK(camelMessageService.sendMessage(message)));
    }
}
