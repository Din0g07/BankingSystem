package com.dinogo.banking.system.controller;

import com.dinogo.banking.system.entity.Email;
import com.dinogo.banking.system.entity.PhoneNumber;
import com.dinogo.banking.system.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private Service service;

    @GetMapping("phoneNumbers/{id}")
    public void getAllPhoneNumbersByUser(@PathVariable UUID id) {
        service.getAllNumbersByUser(id);
    }

    @DeleteMapping("phoneNumbes/{id}")
    public void deletePhoneNumber(@PathVariable UUID id) {
        service.deletePhoneNumber(id);
    }

    @PutMapping("phoneNumbes/{id}")
    public void updatePhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        service.updatePhoneNumber(phoneNumber);
    }

    @PutMapping("phoneNumbes/{id}")
    public void updateEmail(@RequestBody Email email) {
//        service.updatePhoneNumber(phoneNumber);
    }

    @DeleteMapping("emails/{id}")
    public void deleteEmail(@PathVariable UUID id) {
        service.deleteEmail(id);
    }
}
