package com.dinogo.banking.system.service;

import com.dinogo.banking.system.entity.DTO.PhoneNumberDTO;
import com.dinogo.banking.system.entity.Email;
import com.dinogo.banking.system.entity.PhoneNumber;
import com.dinogo.banking.system.exceptionhandling.BankingSystemCommonException;
import com.dinogo.banking.system.mapper.PhoneNumberMapper;
import com.dinogo.banking.system.repository.EmailRepository;
import com.dinogo.banking.system.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
public class ServiceImp implements Service{
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private PhoneNumberMapper phoneNumberMapper;

    @Override
    public List<PhoneNumberDTO> getAllNumbersByUser(UUID userId) {
        return phoneNumberMapper.entityListToDTOList(
                phoneNumberRepository.findAllByUserId(userId));
    }

    @Override
    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        if(phoneNumberRepository.countOfBusyPhoneNumbers(phoneNumber.getPhoneNumber()) > 0) {
            throw new BankingSystemCommonException("Такой номер телефона" +
                    "уже существует");
        }
        phoneNumberRepository.save(phoneNumber);
    }

    @Override
    public void deletePhoneNumber(UUID phoneId) {
        if(phoneNumberRepository.countPhoneNumbersByUserWithPhoneId(phoneId)
                <= 1 ) {
            throw new BankingSystemCommonException("Невозможно удалить" +
                    " единственный номер телефона");
        }
        phoneNumberRepository.deleteById(phoneId);
    }

    @Override
    public void updateEmail(Email email) {
        if(emailRepository.countOfBusyEmails(email.getEmail()) > 0) {
            throw new BankingSystemCommonException("такой адрес электронной" +
                    "почты уже существует");
        }
        emailRepository.save(email);
    }


    @Override
    public void deleteEmail(UUID emailId) {
        if(emailRepository.countEmailsByUserWithEmailId(emailId)
                <= 1 ) {
            throw new BankingSystemCommonException("Невозможно удалить" +
                    " единственный адрес электронной почты");
        }
        phoneNumberRepository.deleteById(emailId);
    }
}
