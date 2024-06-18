package com.app.messaging_kafka.dto.response;

import com.app.messaging_kafka.dto.request.ActionTypeRequestDTO;
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
public class AccountResponseDTO extends ActionTypeRequestDTO {

    private Long id;
    private String code;
    private String fullname;
    private String email;
    private String phoneNumber;
}
