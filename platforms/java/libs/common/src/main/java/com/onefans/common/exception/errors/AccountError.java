package com.onefans.common.exception.errors;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum AccountError implements ErrorDefinition{
    ACCOUNT_NOT_FOUND(
        "ACT_001",
        "Account not found",
        HttpStatus.NOT_FOUND
    ),

    ACCOUNT_ALREADY_EXISTS(
        "ACT_002",
        "Account already exists",
        HttpStatus.CONFLICT
    ),
    ;
    String code;
    String message;
    HttpStatus httpStatus;

    @Override
    public String getCode() { return code; }
    @Override
    public String getMessage() { return message; }
    @Override
    public HttpStatus getHttpStatus() { return httpStatus; }
}
