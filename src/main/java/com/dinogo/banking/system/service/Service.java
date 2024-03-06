package com.dinogo.banking.system.service;

import com.dinogo.banking.system.entity.Email;
import com.dinogo.banking.system.entity.PhoneNumber;

import java.util.UUID;

public interface Service {
    void getAllNumbersByUser(UUID userId);
    void updatePhoneNumber(PhoneNumber phoneNumber);
    void deletePhoneNumber(UUID phoneId);
    void updateEmail(Email email);
    void deleteEmail(UUID emailId);

}
