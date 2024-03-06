package com.dinogo.banking.system.service;

import com.dinogo.banking.system.entity.DTO.PhoneNumberDTO;
import com.dinogo.banking.system.entity.Email;
import com.dinogo.banking.system.entity.PhoneNumber;

import java.util.List;
import java.util.UUID;

public interface Service {
    List<PhoneNumberDTO> getAllNumbersByUser(UUID userId);
    UUID updatePhoneNumber(PhoneNumber phoneNumber);
    void deletePhoneNumber(UUID phoneId);
    void updateEmail(Email email);
    void deleteEmail(UUID emailId);

}
