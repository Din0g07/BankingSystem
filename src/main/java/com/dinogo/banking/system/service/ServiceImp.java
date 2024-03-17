package com.dinogo.banking.system.service;

import com.dinogo.banking.system.common.ActionEnum;
import com.dinogo.banking.system.entity.DTO.BalanceRqDTO;
import com.dinogo.banking.system.entity.DTO.BalanceRsDTO;
import com.dinogo.banking.system.entity.DTO.EmailDTO;
import com.dinogo.banking.system.entity.DTO.PhoneNumberDTO;
import com.dinogo.banking.system.entity.DTO.UserDTO;
import com.dinogo.banking.system.entity.Email;
import com.dinogo.banking.system.entity.PhoneNumber;
import com.dinogo.banking.system.entity.User;
import com.dinogo.banking.system.exceptionhandling.BankingSystemCommonException;
import com.dinogo.banking.system.mapper.EmailMapper;
import com.dinogo.banking.system.mapper.PhoneNumberMapper;
import com.dinogo.banking.system.mapper.UserMapper;
import com.dinogo.banking.system.repository.EmailRepository;
import com.dinogo.banking.system.repository.PhoneNumberRepository;
import com.dinogo.banking.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
public class ServiceImp implements Service{
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneNumberMapper phoneNumberMapper;

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UUID addNewUser(UserDTO userDTO) {
        User user = userMapper.map(userDTO);
        user.setId(UUID.randomUUID());
        user.setInitialBalance(user.getBalance());

        user.setPhoneNumbers(userDTO.getPhoneNumbers().stream()
                .map((phone) -> new PhoneNumber(UUID.randomUUID(), phone, user))
                .toList());
        user.setEmails(userDTO.getEmails().stream()
                .map((email) -> new Email(UUID.randomUUID(), email, user))
                .toList());

        userRepository.save(user);
        return user.getId();
    }

    @Override
    public UUID addNewPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        checkIfPhoneExist(phoneNumberDTO.getPhoneNumber(), ActionEnum.CREATE);
        PhoneNumber phoneNumber = phoneNumberMapper.map(phoneNumberDTO);
        phoneNumber.setId(UUID.randomUUID());
        phoneNumber.setUser(userRepository.findById(phoneNumberDTO.getIdUser())
                .orElseThrow(() -> new BankingSystemCommonException("Пользователь не найден")));
        phoneNumberRepository.save(phoneNumber);
        return phoneNumber.getId();
    }

    @Override
    public UUID updatePhoneNumber(PhoneNumberDTO phoneNumberDTO, UUID id) {
        checkIfPhoneExist(phoneNumberDTO.getPhoneNumber(), ActionEnum.UPDATE);
        PhoneNumber phoneNumber = phoneNumberMapper.map(phoneNumberDTO);
        phoneNumber.setId(id);
        phoneNumber.setUser(userRepository.findById(phoneNumberDTO.getIdUser())
                .orElseThrow(() -> new BankingSystemCommonException("Пользователь не найден")));
        phoneNumberRepository.save(phoneNumber);
        return id;
    }

    @Override
    @Transactional
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
        checkIfEmailExist(emailDTO.getEmail(), ActionEnum.CREATE);
        Email email = emailMapper.map(emailDTO);
        email.setId(UUID.randomUUID());
        email.setUser(userRepository.findById(emailDTO.getIdUser())
                .orElseThrow(() -> new BankingSystemCommonException("Пользователь не найден")));
        emailRepository.save(email);
        return email.getId();
    }

    @Override
    public UUID updateEmail(EmailDTO emailDTO, UUID id) {
        checkIfEmailExist(emailDTO.getEmail(), ActionEnum.UPDATE);
        Email email = emailMapper.map(emailDTO);
        email.setId(id);
        email.setUser(userRepository.findById(emailDTO.getIdUser())
                .orElseThrow(() -> new BankingSystemCommonException("Пользователь не найден")));
        emailRepository.save(email);
        return id;
    }

    @Override
    @Transactional
    public void deleteEmail(UUID emailId) {
        if(emailRepository.countEmailsByUserWithEmailId(emailId)
                <= 1 ) {
            throw new BankingSystemCommonException("Невозможно удалить" +
                    " единственный адрес электронной почты");
        }
        emailRepository.deleteById(emailId);
    }

    @Override
    public List<UserDTO> usersSearch(Date date,
                                     String phoneNumber,
                                     String fullName,
                                     String email) {
        List<User> users = userRepository.usersSearch(date, phoneNumber, fullName, email);

        return users.stream().map(user -> {
            user.setPhoneNumbers(phoneNumberRepository.findAllByUserId(user.getId()));
            user.setEmails(emailRepository.findAllByUserId(user.getId()));
            return userMapper.map(user);
        }).toList();
    }

    @Override
    public BalanceRsDTO transfer(BalanceRqDTO balanceRqDTO) {
        User sender = userRepository
                .findById(balanceRqDTO.getIdSender())
                .orElseThrow(() -> new BankingSystemCommonException("Пользователь не найден"));
        if(sender.getBalance().compareTo(balanceRqDTO.getSum()) <= 0) {
            throw new BankingSystemCommonException("Недостаточно средств на счету");
        }
        User receiver = userRepository
                .findById(balanceRqDTO.getIdReceiver())
                .orElseThrow(() -> new BankingSystemCommonException("Пользователь не найден"));

        sender.setBalance(sender.getBalance().subtract(balanceRqDTO.getSum()));
        receiver.setBalance(receiver.getBalance().add(balanceRqDTO.getSum()));

        userRepository.save(sender);
        userRepository.save(receiver);

        return new BalanceRsDTO(sender.getBalance());
    }

    //action.ordinal() - допустимое число на проверку
    private void checkIfPhoneExist(String phoneNumber, ActionEnum action) {
        if(phoneNumberRepository.countOfBusyPhoneNumbers(phoneNumber) > action.ordinal()) {
            throw new BankingSystemCommonException("Такой номер телефона" +
                    "уже существует");
        }
    }

    private void checkIfEmailExist(String email, ActionEnum action) {
        if(emailRepository.countOfBusyEmails(email) > action.ordinal()) {
            throw new BankingSystemCommonException("такой адрес электронной" +
                    "почты уже существует");
        }
    }
}
