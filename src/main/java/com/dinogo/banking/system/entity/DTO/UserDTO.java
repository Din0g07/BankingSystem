package com.dinogo.banking.system.entity.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    @NotNull
    @Size(min = 1, max = 150)
    private String fullName;
    @NotNull
    private Date date;
    @Positive
    @NotNull
    private int balance;
    @Size(min = 11, max = 11)
    @NotNull
    private List<String> phoneNumbers;
    @Email
    @NotNull
    private List<String> emails;
}
