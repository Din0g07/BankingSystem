package com.dinogo.banking.system.entity.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PhoneNumberDTO {
    @Size(min = 11, max = 11, message = "Номер телефона должен состоять из 11 чисел")
    @NotNull(message = "Номер телефона не должен быть пустым")
    private String phoneNumber;
    @NotNull(message = "ИД пользователя не должен быть пустым")
    private UUID idUser;
}
