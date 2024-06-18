package com.app.messaging_kafka.helper;

import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaCamelHelper {

    public static final String convertObjToString(Object object){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("convertObjToString error because = {}",e.getMessage());
            return null;
        }
    }

    public static final AccountRequestDTO convertToAccount(String jsonString){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, AccountRequestDTO.class);
        } catch (JsonProcessingException e) {
            log.error("convertObjToString error because = {}",e.getMessage());
            return null;
        }
    }
}
