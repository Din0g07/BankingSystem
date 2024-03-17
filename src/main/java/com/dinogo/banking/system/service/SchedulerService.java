package com.dinogo.banking.system.service;

import com.dinogo.banking.system.entity.User;
import com.dinogo.banking.system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class SchedulerService {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedDelay = 60000)
    public void balanceUpdate() {
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            if (user.getInitialBalance().multiply(BigDecimal.valueOf(2.07)).compareTo(user.getBalance()) > 0) {
                user.setBalance(user.getBalance().multiply( BigDecimal.valueOf(1.05)));
            }
        });
        userRepository.saveAll(users);
        log.info("Balance updated");
    }
}
