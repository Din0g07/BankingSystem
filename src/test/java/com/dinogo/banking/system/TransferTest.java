package com.dinogo.banking.system;

import com.dinogo.banking.system.entity.DTO.BalanceRqDTO;
import com.dinogo.banking.system.entity.User;
import com.dinogo.banking.system.exceptionhandling.BankingSystemCommonException;
import com.dinogo.banking.system.repository.UserRepository;
import com.dinogo.banking.system.service.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Collections;
import java.util.UUID;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferTest {

    @Autowired
    private Service service;

    @Autowired
    private UserRepository repository;

    private BalanceRqDTO dto;

    private final UUID testUUID1 = UUID.randomUUID();
    private final UUID testUUID2 = UUID.randomUUID();

    @BeforeEach
    public void prepareData() {
        dto = new BalanceRqDTO();
        dto.setIdSender(UUID.randomUUID());
        dto.setIdReceiver(UUID.randomUUID());
        dto.setSum(100);

        repository.save(new User(testUUID1, "", new Date(2014, 1, 1),
                Collections.emptyList(), Collections.emptyList(), 123, 123));
        repository.save(new User(testUUID2, "", new Date(2014, 1, 1),
                Collections.emptyList(), Collections.emptyList(), 123, 123));
    }

    @AfterEach
    public void clearData() {
        repository.deleteById(testUUID1);
        repository.deleteById(testUUID2);
    }

    @Test
    public void TransferCorrectTest() {
        dto.setIdSender(testUUID1);
        dto.setIdReceiver(testUUID2);
        Assertions.assertDoesNotThrow(() -> service.transfer(dto));
    }

    @Test
    public void TransferWithUnknownUserTest() {
        Assertions.assertThrows(BankingSystemCommonException.class,
                () -> service.transfer(dto), "Пользователь не найден");
    }

    @Test
    public void TransferWithWrongSumTest() {
        dto.setIdSender(testUUID1);
        dto.setSum(2000);
        Assertions.assertThrows(BankingSystemCommonException.class,
                () -> service.transfer(dto), "Недостаточно средств на счету");
    }
}
