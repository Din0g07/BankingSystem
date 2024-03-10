package com.dinogo.banking.system.service;

import com.dinogo.banking.system.entity.User;
import com.dinogo.banking.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedDelay = 60000)
    public void balanceUpdate() {
        List<User> users = userRepository.findAll();

        users.forEach(user -> user.setBalance(user.getBalance() * 1.05));
        userRepository.saveAll(users);
    }
}
