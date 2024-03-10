package com.dinogo.banking.system.entity.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmailDTO {
    @Email
    private String email;
    @NotNull
    private UUID idUser;
}
