package com.onefans.identity_service.modules.accounts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.onefans.common.dto.request.CreateAccountDto;
import com.onefans.common.dto.response.AccountResponseDto;
import com.onefans.identity_service.modules.accounts.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "provider", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "isBlock", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    Account toAccount(CreateAccountDto createAccountDto);

    AccountResponseDto toAccountResponse(Account account);
}
