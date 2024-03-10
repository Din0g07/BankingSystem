package com.dinogo.banking.system.exceptionhandling;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
public class BankingSystemExceptionHandler {
    @ExceptionHandler
    public BankingSystemExceptionInfo handleException(
            BankingSystemCommonException exp) {
        BankingSystemExceptionInfo info = new BankingSystemExceptionInfo();
        info.setInfo(exp.getMessage());
        return info;
    }

    @ExceptionHandler
    public BankingSystemExceptionInfo handleException(
            MethodArgumentNotValidException exp) {
        BankingSystemExceptionInfo info = new BankingSystemExceptionInfo();
        info.setInfo(Objects.requireNonNull(exp.getFieldError()).getDefaultMessage());
        return info;
    }

    @ExceptionHandler
    public BankingSystemExceptionInfo handleException(
            MethodArgumentTypeMismatchException exp) {
        BankingSystemExceptionInfo info = new BankingSystemExceptionInfo();
        info.setInfo(exp.getMessage());
        return info;
    }
}
