package com.onefans.identity_service.modules.accounts;

import org.springframework.web.bind.annotation.RestController;

import com.onefans.common.dto.response.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/accounts")
public class AccountController {
    @PostMapping("/create")
    public ApiResponse<String> create(@RequestBody String entity) {        
        return ApiResponse.<String>builder()
                .code("Created")
                .message(null)
                .data(entity)
                .build();
    }
    
}
