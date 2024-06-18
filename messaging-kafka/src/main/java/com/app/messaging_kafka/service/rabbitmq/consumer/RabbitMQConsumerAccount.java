package com.app.messaging_kafka.service.rabbitmq.consumer;

import com.app.messaging_kafka.config.RabbitMQConfig;
import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.entity.Account;
import com.app.messaging_kafka.helper.KafkaCamelHelper;
import com.app.messaging_kafka.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQConsumerAccount {

    private final AccountRepository accountRepository;
    private final String ACTION_TYPE_INSERT = "I";
    private final String ACTION_TYPE_UPDATE = "U";
    private final String ACTION_TYPE_DELETE = "D";

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeData(String message){
        log.info("consume data from RabbitMQ = [{}]",message);
        AccountRequestDTO accountRequestDTO = KafkaCamelHelper.convertToAccount(message);
        try{
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
            throw new RuntimeException(e.getMessage());
        }
    }
}
