package com.app.messaging_kafka.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse {

    private String message;
    private Integer status;
    private Object data;

    public static RestResponse isOK(Object data){
        return RestResponse.builder()
                .data(data)
                .status(200)
                .message("successfully")
                .build();
    }

    public static RestResponse isFailed(){
        return RestResponse.builder()
                .data(null)
                .status(500)
                .message("something went wrong")
                .build();
    }
}
