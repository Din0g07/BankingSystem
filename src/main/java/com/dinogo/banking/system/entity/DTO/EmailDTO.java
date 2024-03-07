package com.dinogo.banking.system.entity.DTO;

import com.dinogo.banking.system.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
    private String email;
    private User user;
}
