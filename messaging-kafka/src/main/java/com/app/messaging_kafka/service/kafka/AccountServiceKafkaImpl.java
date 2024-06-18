package com.app.messaging_kafka.service.kafka;

import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.dto.response.AccountResponseDTO;
import com.app.messaging_kafka.helper.KafkaCamelHelper;
import com.app.messaging_kafka.repository.AccountRepository;
import com.app.messaging_kafka.service.kafka.producer.KafkaProducerAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceKafkaImpl implements AccountServiceKafka {

    private final KafkaProducerAccount kafkaProducerAccount;
    private final String ACTION_TYPE_INSERT = "I";
    private final String ACTION_TYPE_UPDATE = "U";
    private final String ACTION_TYPE_DELETE = "D";
    private final AccountRepository accountRepository;

    @Override
    public AccountResponseDTO save(AccountRequestDTO requestDTO) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        requestDTO.setActionType(ACTION_TYPE_INSERT);
        accountResponseDTO.setActionType(ACTION_TYPE_INSERT);
        String message = KafkaCamelHelper.convertObjToString(requestDTO);
        BeanUtils.copyProperties(requestDTO,accountResponseDTO);
        kafkaProducerAccount.publishData(message);
        return accountResponseDTO;
    }

    @Override
    public AccountResponseDTO update(AccountRequestDTO requestDTO) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        if (Objects.isNull(requestDTO.getId())){
            log.error("error because id is not found !");
            throw new RuntimeException("failed update !");
        }
        requestDTO.setActionType(ACTION_TYPE_UPDATE);
        accountResponseDTO.setActionType(ACTION_TYPE_INSERT);
        String message = KafkaCamelHelper.convertObjToString(requestDTO);
        BeanUtils.copyProperties(requestDTO,accountResponseDTO);
        kafkaProducerAccount.publishData(message);
        return accountResponseDTO;
    }

    @Override
    public AccountResponseDTO delete(Long id) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
        accountRequestDTO.setId(id);
        accountResponseDTO.setId(id);
        if (Objects.isNull(id)){
            log.error("error because id is not found !");
            throw new RuntimeException("failed update !");
        }
        accountRequestDTO.setActionType(ACTION_TYPE_DELETE);
        accountResponseDTO.setActionType(ACTION_TYPE_DELETE);
        String message = KafkaCamelHelper.convertObjToString(accountRequestDTO);
        kafkaProducerAccount.publishData(message);
        return accountResponseDTO;
    }

    @Override
    public AccountResponseDTO find(Long id) {
        return accountRepository.findById(id)
                .map(account -> AccountResponseDTO
                        .builder()
                        .id(account.getId())
                        .email(account.getEmail())
                        .code(account.getCode())
                        .phoneNumber(account.getPhoneNumber())
                        .fullname(account.getFullname())
                        .build())
                .orElseThrow(()-> new RuntimeException("failed find data"));
    }

    @Override
    public Page<AccountResponseDTO> search(Pageable pageable) {
        return accountRepository.findAll(pageable)
                .map(account -> AccountResponseDTO
                        .builder()
                        .id(account.getId()).email(account.getEmail())
                        .code(account.getCode()).phoneNumber(account.getPhoneNumber())
                        .fullname(account.getFullname())
                        .build());
    }
}
