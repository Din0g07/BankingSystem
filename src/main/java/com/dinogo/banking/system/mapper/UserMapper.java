package com.dinogo.banking.system.mapper;

import com.dinogo.banking.system.entity.DTO.UserDTO;
import com.dinogo.banking.system.entity.Email;
import com.dinogo.banking.system.entity.PhoneNumber;
import com.dinogo.banking.system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "phoneNumbers", ignore = true)
    @Mapping(target = "emails", ignore = true)
    UserDTO map(User user);

    @Mapping(target = "phoneNumbers", ignore = true)
    @Mapping(target = "emails", ignore = true)
    User map(UserDTO userDTO);
}
