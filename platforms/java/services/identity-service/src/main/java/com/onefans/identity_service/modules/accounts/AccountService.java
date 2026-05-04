package com.onefans.identity_service.modules.accounts;

import org.springframework.stereotype.Service;

import com.onefans.common.dto.request.CreateAccountDto;
import com.onefans.common.dto.response.AccountResponseDto;
import com.onefans.common.exception.HttpException;
import com.onefans.common.exception.errors.AccountError;
import com.onefans.identity_service.modules.accounts.entity.Account;
import com.onefans.identity_service.modules.accounts.mapper.AccountMapper;
import com.onefans.identity_service.modules.accounts.repository.AccountRepository;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService {
     AccountRepository accountRepository;
     AccountMapper accountMapper;

    @Transactional
    public AccountResponseDto create(CreateAccountDto createAccountDto) {
        boolean exists = accountRepository.existsByEmail(createAccountDto.getEmail());
        if(exists) throw new HttpException(AccountError.ACCOUNT_ALREADY_EXISTS);
        Account account = accountRepository.save(accountMapper.toAccount(createAccountDto));
        return accountMapper.toAccountResponse(account);
    }
}
