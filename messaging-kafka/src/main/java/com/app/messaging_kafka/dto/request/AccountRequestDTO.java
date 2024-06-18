package com.app.messaging_kafka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO extends ActionTypeRequestDTO{


//    { for update dto
//        "id" : 1,
//            "code" : "AC001",
//            "fullname" : "dicka",
//            "email" : "dickanirwansyah@gmail.com",
//            "phoneNumber" : "081324366585"
//    }

//    { for insert dto
//
//            "code" : "AC001",
//            "fullname" : "dicka",
//            "email" : "dickanirwansyah@gmail.com",
//            "phoneNumber" : "081324366585"
//    }

    private Long id;
    private String code;
    private String fullname;
    private String email;
    private String phoneNumber;
}
