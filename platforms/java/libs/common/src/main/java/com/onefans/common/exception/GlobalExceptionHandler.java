package com.onefans.common.exception;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onefans.common.dto.response.ErrorResponse;
import com.onefans.common.exception.errors.ServerError;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnMissingBean(GlobalExceptionHandler.class) // Chỉ tạo nếu chưa có ai tạo Handler này
@Order(Ordered.LOWEST_PRECEDENCE) // Ưu tiên thấp nhất để nhường chỗ cho App chính
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ErrorResponse> exceptionHandling(HttpException exception, HttpServletRequest request) {
        ErrorResponse response = ErrorResponse.builder()
            .status(ServerError.SERVER_ERROR.getHttpStatus().value())
            .code(ServerError.SERVER_ERROR.getCode())
            .detail(ServerError.SERVER_ERROR.getMessage())
            .path(request.getRequestURI())
            .timestamp(LocalDateTime.now())
            .build();
        return ResponseEntity.status(exception.getError().getHttpStatus().value()).body(response); 
    }

    @ExceptionHandler(value = HttpException.class)
    ResponseEntity<ErrorResponse> runtimeExceptionHandling(HttpException exception, HttpServletRequest request) {
        ErrorResponse response = ErrorResponse.builder()
            .status(exception.getError().getHttpStatus().value())
            .code(exception.getError().getCode())
            .detail(exception.getError().getMessage())
            .path(request.getRequestURI())
            .timestamp(LocalDateTime.now())
            .build();
        return ResponseEntity.status(exception.getError().getHttpStatus().value()).body(response); 
    }
}
