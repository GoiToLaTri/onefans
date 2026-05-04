package com.onefans.identity_service.modules.accounts;


import com.onefans.common.dto.request.CreateAccountDto;
import com.onefans.common.dto.response.AccountResponseDto;

public interface IAccountService {
    AccountResponseDto create(CreateAccountDto dto);
}
