package com.dinogo.banking.system.entity.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    @NotNull(message = "ФИО не должно быть пустым")
    @Size(min = 1, max = 150, message = "ФИО не должно быть пустым или слишком длинным")
    private String fullName;
    @NotNull(message = "Дата рождения не должно быть пустым")
    private Date date;
    @Positive(message = "Баланс должен быть положительным")
    @NotNull(message = "Баланс не должно быть пустым")
    private BigDecimal balance;
    @NotEmpty(message = "Номера телефона не должны быть пустым")
    private List<String> phoneNumbers;
    @NotEmpty(message = "Имейлы не должны быть пустым")
    private List<String> emails;
}
