package com.dinogo.banking.system.mapper;

import com.dinogo.banking.system.entity.DTO.EmailDTO;
import com.dinogo.banking.system.entity.Email;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    EmailDTO map(Email email);
    Email map(EmailDTO emailDTO);
}
