package com.app.messaging_kafka.service.rabbitmq;

import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.dto.response.AccountResponseDTO;
import com.app.messaging_kafka.helper.KafkaCamelHelper;
import com.app.messaging_kafka.repository.AccountRepository;
import com.app.messaging_kafka.service.rabbitmq.producer.RabbitMQProducerAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceRabbitMQImpl implements AccountServiceRabbitMQ {

    private final RabbitMQProducerAccount rabbitMQProducerAccount;
    private final AccountRepository accountRepository;
    private final String ACTION_TYPE_INSERT = "I";
    private final String ACTION_TYPE_UPDATE = "U";
    private final String ACTION_TYPE_DELETE = "D";

    @Override
    public AccountResponseDTO save(AccountRequestDTO requestDTO) {
        AccountResponseDTO responseDTO = new AccountResponseDTO();
        responseDTO.setActionType(ACTION_TYPE_INSERT);
        requestDTO.setActionType(ACTION_TYPE_INSERT);
        BeanUtils.copyProperties(requestDTO, responseDTO);
        String message = KafkaCamelHelper.convertObjToString(responseDTO);
        rabbitMQProducerAccount.publishData(message);
        return responseDTO;
    }

    @Override
    public AccountResponseDTO update(AccountRequestDTO requestDTO) {
        AccountResponseDTO responseDTO = new AccountResponseDTO();
        if (Objects.isNull(requestDTO.getId())){
            log.error("error because id is null");
            throw new RuntimeException("failed update");
        }
        responseDTO.setActionType(ACTION_TYPE_UPDATE);
        requestDTO.setActionType(ACTION_TYPE_UPDATE);
        BeanUtils.copyProperties(requestDTO, responseDTO);
        String message = KafkaCamelHelper.convertObjToString(responseDTO);
        rabbitMQProducerAccount.publishData(message);
        return responseDTO;
    }

    @Override
    public AccountResponseDTO delete(Long id) {
        AccountResponseDTO responseDTO = new AccountResponseDTO();
        AccountRequestDTO requestDTO = new AccountRequestDTO();
        requestDTO.setId(id);
        if (Objects.isNull(requestDTO.getId())){
            log.error("error because id is null");
            throw new RuntimeException("failed update");
        }
        responseDTO.setActionType(ACTION_TYPE_DELETE);
        requestDTO.setActionType(ACTION_TYPE_DELETE);
        BeanUtils.copyProperties(requestDTO, responseDTO);
        String message = KafkaCamelHelper.convertObjToString(responseDTO);
        rabbitMQProducerAccount.publishData(message);
        return responseDTO;
    }

    @Override
    public AccountResponseDTO find(Long id) {
        return accountRepository.findById(id)
                .map(account -> AccountResponseDTO
                        .builder()
                        .id(account.getId())
                        .code(account.getCode())
                        .email(account.getEmail())
                        .phoneNumber(account.getPhoneNumber())
                        .fullname(account.getFullname())
                        .build())
                .orElseThrow(() -> new RuntimeException("failed find data"));
    }

    @Override
    public Page<AccountResponseDTO> search(Pageable pageable) {
        return accountRepository.findAll(pageable)
                .map(account -> AccountResponseDTO
                        .builder()
                        .id(account.getId()).code(account.getCode())
                        .email(account.getEmail()).phoneNumber(account.getPhoneNumber())
                        .fullname(account.getFullname())
                        .build());
    }
}
