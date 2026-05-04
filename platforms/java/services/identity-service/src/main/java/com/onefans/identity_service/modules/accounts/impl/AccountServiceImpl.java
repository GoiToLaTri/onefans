package com.onefans.identity_service.modules.accounts.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onefans.common.dto.request.CreateAccountDto;
import com.onefans.common.dto.response.AccountResponseDto;
import com.onefans.common.enums.EAccountStatus;
import com.onefans.common.exception.HttpException;
import com.onefans.common.exception.errors.AccountError;
import com.onefans.identity_service.modules.accounts.IAccountService;
import com.onefans.identity_service.modules.accounts.entity.Account;
import com.onefans.identity_service.modules.accounts.mapper.AccountMapper;
import com.onefans.identity_service.modules.accounts.repository.AccountRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountServiceImpl implements IAccountService{
    PasswordEncoder passwordEncoder;
    AccountRepository accountRepository;
    AccountMapper accountMapper;

    @Override
    public AccountResponseDto create(CreateAccountDto dto) throws HttpException{
        Account account = accountRepository.findByEmailAndProvider(
            dto.getEmail(), "onefans"
        ).orElse(null);
        
        if(account != null) {
            if(EAccountStatus.ACTIVE.equals(account.getAccountStatus()))
                throw new HttpException(AccountError.ACCOUNT_ALREADY_EXISTS);
            else if (EAccountStatus.PENDING.equals(account.getAccountStatus()))
                // TODO: fix account error
                throw new HttpException(AccountError.ACCOUNT_ALREADY_EXISTS);
        }

        Account newAccount = accountMapper.toAccount(dto);
        newAccount.setPassword(passwordEncoder.encode(dto.getPassword()));
        AccountResponseDto response = accountMapper.toAccountResponse(accountRepository
            .save(newAccount));

        return response;
    }
}
