package com.dinogo.banking.system.entity.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmailDTO {
    @Email(message = "Имейл должен быть в верном формате")
    @NotNull(message = "Имейл не должен быть пустым")
    private String email;
    @NotNull(message = "ИД пользователя не должен быть пустым")
    private UUID idUser;
}
