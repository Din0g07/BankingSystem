package com.dinogo.banking.system.entity.DTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PhoneNumberDTO {
    @Size(min = 11, max = 11)
    @NotNull
    private String phoneNumber;
    @NotNull
    private UUID idUser;
}
