package com.dinogo.banking.system.controller;

import com.dinogo.banking.system.exceptionhandling.BankingSystemExceptionInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse<T> {
    private boolean status;
    private T budy;
    private BankingSystemExceptionInfo errorInfo;
}
