package com.app.messaging_kafka.service.rabbitmq;

import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.dto.response.AccountResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountServiceRabbitMQ {

    AccountResponseDTO save(AccountRequestDTO requestDTO);
    AccountResponseDTO update(AccountRequestDTO requestDTO);
    AccountResponseDTO delete(Long id);
    AccountResponseDTO find(Long id);
    Page<AccountResponseDTO> search(Pageable pageable);
}
