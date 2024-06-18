package com.app.messaging_kafka.service.kafka.camel;

import com.app.messaging_kafka.dto.request.AccountRequestDTO;
import com.app.messaging_kafka.entity.Account;
import com.app.messaging_kafka.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CamelAccountService {

    private final AccountRepository accountRepository;

    public void save(AccountRequestDTO requestDTO){
        Account account = new Account();
        BeanUtils.copyProperties(requestDTO, account);
        accountRepository.save(account);
        log.info("success save data account");
    }
}
