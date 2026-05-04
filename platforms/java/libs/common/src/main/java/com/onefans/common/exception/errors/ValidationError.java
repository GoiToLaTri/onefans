package com.onefans.common.exception.errors;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum ValidationError implements ErrorDefinition {
    KEY_INVALID(
            "VAL_001",
            "Key invalid",
            HttpStatus.BAD_REQUEST),
    NAME_REQUIRED("VAL_002", "Name is required", HttpStatus.BAD_REQUEST),
    NAME_INVALID("VAL_003", "Name is invalid", HttpStatus.BAD_REQUEST),

    PASSWORD_REQUIRED("VAL_004", "Password is required", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID("VAL_005", "Password must be at least 4 characters", HttpStatus.BAD_REQUEST),

    EMAIL_REQUIRED("VAL_006", "Email is required", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID("VAL_007", "Email is invalid", HttpStatus.BAD_REQUEST);
    ;

    String code;
    String message;
    HttpStatus httpStatus;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
