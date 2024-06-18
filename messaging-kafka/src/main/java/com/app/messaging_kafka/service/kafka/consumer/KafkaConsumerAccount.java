package com.app.messaging_kafka.service.kafka.consumer;

import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.entity.Account;
import com.app.messaging_kafka.helper.KafkaCamelHelper;
import com.app.messaging_kafka.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerAccount {

    private final AccountRepository accountRepository;
    private final String ACTION_TYPE_INSERT = "I";
    private final String ACTION_TYPE_UPDATE = "U";
    private final String ACTION_TYPE_DELETE = "D";

    @KafkaListener(
            topics = "${topic.name}",
            groupId = "${spring.kafka.producer.group-id}"
    )
    public void consumeData(String message){
        try{
            log.info("consume data from Kafka = [{}]",message);
            AccountRequestDTO accountRequestDTO = KafkaCamelHelper.convertToAccount(message);
            if (accountRequestDTO.getActionType().equals(ACTION_TYPE_INSERT)){
                log.info("insert data..");
                accountRepository.save(Account.builder()
                        .email(accountRequestDTO.getEmail())
                        .code(accountRequestDTO.getCode())
                        .fullname(accountRequestDTO.getFullname())
                        .email(accountRequestDTO.getEmail())
                        .phoneNumber(accountRequestDTO.getPhoneNumber()).build());
            }
            if (accountRequestDTO.getActionType().equals(ACTION_TYPE_UPDATE)){
                log.info("update data..");
                Optional<Account> findCurrentAccount = accountRepository.findById(accountRequestDTO.getId());
                if (findCurrentAccount.isPresent()){
                    findCurrentAccount.get().setCode(accountRequestDTO.getCode());
                    findCurrentAccount.get().setEmail(accountRequestDTO.getEmail());
                    findCurrentAccount.get().setPhoneNumber(accountRequestDTO.getPhoneNumber());
                    findCurrentAccount.get().setFullname(accountRequestDTO.getFullname());
                    accountRepository.save(findCurrentAccount.get());
                }else{
                    log.info("account with id = [{}] not found !",accountRequestDTO.getId());
                }
            }
            if (accountRequestDTO.getActionType().equals(ACTION_TYPE_DELETE)){
                log.info("delete data..");
                Optional<Account> findCurrentAccount = accountRepository.findById(accountRequestDTO.getId());
                if (findCurrentAccount.isPresent()){
                    accountRepository.delete(findCurrentAccount.get());
                }else{
                    log.info("account with id = [{}] not found !",accountRequestDTO.getId());
                }
            }
        }catch (Exception e){
            log.error("error because = [{}]",e.getMessage());
        }
    }
}
