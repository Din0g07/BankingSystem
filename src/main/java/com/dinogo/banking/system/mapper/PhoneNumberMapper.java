package com.dinogo.banking.system.mapper;

import com.dinogo.banking.system.entity.DTO.PhoneNumberDTO;
import com.dinogo.banking.system.entity.PhoneNumber;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {
    PhoneNumberDTO map(PhoneNumber phoneNumber);
    PhoneNumber map(PhoneNumberDTO phoneNumberDTO);
}
