package com.dinogo.banking.system.service;

import com.dinogo.banking.system.entity.DTO.EmailDTO;
import com.dinogo.banking.system.entity.DTO.PhoneNumberDTO;
import com.dinogo.banking.system.entity.Email;
import com.dinogo.banking.system.entity.PhoneNumber;
import com.dinogo.banking.system.exceptionhandling.BankingSystemCommonException;
import com.dinogo.banking.system.mapper.EmailMapper;
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

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public List<PhoneNumberDTO> getAllNumbersByUser(UUID userId) {
        return phoneNumberMapper.map(
                phoneNumberRepository.findAllByUserId(userId));
    }

    @Override
    public UUID addNewPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        checkIfPhoneExist(phoneNumberDTO.getPhoneNumber());
        PhoneNumber phoneNumber = phoneNumberMapper.map(phoneNumberDTO);
        phoneNumber.setId(UUID.randomUUID());
        phoneNumberRepository.save(phoneNumber);
        return phoneNumber.getId();
    }

    @Override
    public UUID updatePhoneNumber(PhoneNumberDTO phoneNumberDTO, UUID id) {
        checkIfPhoneExist(phoneNumberDTO.getPhoneNumber());
        PhoneNumber phoneNumber = phoneNumberMapper.map(phoneNumberDTO);
        phoneNumber.setId(id);
        phoneNumberRepository.save(phoneNumber);
        return id;
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
    public UUID addNewEmail(EmailDTO emailDTO) {
        checkIfEmailExist(emailDTO.getEmail());
        Email email = emailMapper.map(emailDTO);
        email.setId(UUID.randomUUID());
        emailRepository.save(email);
        return email.getId();
    }

    @Override
    public UUID updateEmail(EmailDTO emailDTO, UUID id) {
        checkIfEmailExist(emailDTO.getEmail());
        Email email = emailMapper.map(emailDTO);
        email.setId(id);
        emailRepository.save(email);
        return id;
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

    private void checkIfPhoneExist(String phoneNumber) {
        if(phoneNumberRepository.countOfBusyPhoneNumbers(phoneNumber) > 0) {
            throw new BankingSystemCommonException("Такой номер телефона" +
                    "уже существует");
        }
    }

    private void checkIfEmailExist(String email) {
        if(emailRepository.countOfBusyEmails(email) > 0) {
            throw new BankingSystemCommonException("такой адрес электронной" +
                    "почты уже существует");
        }
    }
}
