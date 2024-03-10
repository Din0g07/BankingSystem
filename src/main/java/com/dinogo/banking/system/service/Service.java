package com.dinogo.banking.system.service;

import com.dinogo.banking.system.entity.DTO.BalanceRqDTO;
import com.dinogo.banking.system.entity.DTO.BalanceRsDTO;
import com.dinogo.banking.system.entity.DTO.EmailDTO;
import com.dinogo.banking.system.entity.DTO.PhoneNumberDTO;
import com.dinogo.banking.system.entity.DTO.UserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface Service {
    UUID addNewUser(UserDTO userDTO);
    UUID updatePhoneNumber(PhoneNumberDTO phoneNumberDTO,
                           UUID id);
    UUID addNewPhoneNumber(PhoneNumberDTO phoneNumberDTO);
    void deletePhoneNumber(UUID phoneId);
    UUID updateEmail(EmailDTO emailDTO, UUID id);
    UUID addNewEmail(EmailDTO emailDTO);
    void deleteEmail(UUID emailId);
    List<UserDTO> usersSearch(Date date,
                              String phoneNumber,
                              String fullName,
                              String email);

    BalanceRsDTO transfer(BalanceRqDTO balanceRqDTO);

}
