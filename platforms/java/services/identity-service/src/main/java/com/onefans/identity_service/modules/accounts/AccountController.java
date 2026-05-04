package com.onefans.identity_service.modules.accounts;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onefans.common.dto.request.CreateAccountDto;
import com.onefans.common.dto.response.AccountResponseDto;
import com.onefans.common.dto.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/accounts")
public class AccountController {
    IAccountService accountService;

    @PostMapping("/create")
    public ApiResponse<AccountResponseDto> create(@RequestBody @Valid CreateAccountDto entity) {        
        return ApiResponse.<AccountResponseDto>builder()
                .code("CREATED")
                .message("Account is created")
                .data(accountService.create(entity))
                .build();
    }
    
}
