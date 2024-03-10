package com.dinogo.banking.system.entity.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BalanceRqDTO {
    @NotNull(message = "ИД пользователя не должен быть пустым")
    UUID idSender;
    @NotNull(message = "ИД пользователя не должен быть пустым")
    UUID idReceiver;
    @NotNull(message = "Сумма не должна быть пустым")
    @Positive(message = "Сумма должна быть положительным")
    double sum;
}
