package com.dinogo.banking.system.entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserDTO {
    private String fullName;
    private Date date;
    private int balance;

}
