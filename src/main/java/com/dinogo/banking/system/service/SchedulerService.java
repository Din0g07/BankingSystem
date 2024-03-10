package com.dinogo.banking.system.service;

import com.dinogo.banking.system.entity.User;
import com.dinogo.banking.system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SchedulerService {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedDelay = 600)
    public void balanceUpdate() {
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            if (user.getInitialBalance() * 2.07 > user.getBalance()) {
                user.setBalance(user.getBalance() * 1.05);
            }
        });
        userRepository.saveAll(users);
        log.info("Balance updated");
    }
}
