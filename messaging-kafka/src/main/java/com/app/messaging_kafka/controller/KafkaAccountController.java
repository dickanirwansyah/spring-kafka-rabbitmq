package com.app.messaging_kafka.controller;

import com.app.messaging_kafka.dto.RestResponse;
import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.service.kafka.AccountServiceKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/kafka")
@RequiredArgsConstructor
public class KafkaAccountController {

    //ini api kafka

    private final AccountServiceKafka accountServiceKafka;

    @PostMapping(value = "/save")
    public ResponseEntity<RestResponse> save(@RequestBody AccountRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOK(accountServiceKafka.save(requestDTO)));
    }


    @PutMapping(value = "/update")
    public ResponseEntity<RestResponse> update(@RequestBody AccountRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOK(accountServiceKafka.update(requestDTO)));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<RestResponse> delete(@PathVariable("id")Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOK(accountServiceKafka.delete(id)));
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<RestResponse> find(@PathVariable("id")Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOK(accountServiceKafka.find(id)));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<RestResponse> search(@RequestParam(value = "page", defaultValue = "0")Integer page,
                                               @RequestParam(value = "size", defaultValue = "10")Integer size){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOK(accountServiceKafka.search(PageRequest.of(page,size))));
    }
}
