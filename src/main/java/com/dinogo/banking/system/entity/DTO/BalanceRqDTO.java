package com.dinogo.banking.system.entity.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BalanceRqDTO {
    @NotNull
    UUID idSender;
    @NotNull
    UUID idReceiver;
    @NotNull
    @Positive
    double sum;
}
