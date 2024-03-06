package com.dinogo.banking.system.exceptionhandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankingSystemExceptionHandler {
    @ExceptionHandler
    public BankingSystemExceptionInfo handleException(
            BankingSystemCommonException exp) {
        BankingSystemExceptionInfo info = new BankingSystemExceptionInfo();
        info.setInfo(exp.getMessage());
        return info;
    }
}
