package com.dinogo.banking.system.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BalanceRsDTO {
    BigDecimal balance;
}
