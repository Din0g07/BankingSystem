package com.dinogo.banking.system.controller;

import com.dinogo.banking.system.entity.DTO.PhoneNumberDTO;
import com.dinogo.banking.system.entity.Email;
import com.dinogo.banking.system.entity.PhoneNumber;
import com.dinogo.banking.system.entity.User;
import com.dinogo.banking.system.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private Service service;

    @GetMapping("/phoneNumbers/{id}")
    public List<PhoneNumberDTO> getAllPhoneNumbersByUser(@PathVariable UUID id) {
        return service.getAllNumbersByUser(id);
    }

    @PostMapping("/phoneNumbes")
    public void addNewPhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        service.updatePhoneNumber(phoneNumber);
    }

    @PutMapping("/phoneNumbes/{id}")
    public void updatePhoneNumber(@RequestBody PhoneNumber phoneNumber
    , @PathVariable UUID id) {
        service.updatePhoneNumber(phoneNumber);
    }

    @DeleteMapping("/phoneNumbes/{id}")
    public void deletePhoneNumber(@PathVariable UUID id) {
        service.deletePhoneNumber(id);
    }

    @PostMapping("/emails")
    public void addNewEmail(@RequestBody Email email) {
        service.updateEmail(email);
    }

    @PutMapping("/emails")
    public void updateEmail(@RequestBody Email email) {
        service.updateEmail(email);
    }

    @DeleteMapping("/emails/{id}")
    public void deleteEmail(@PathVariable UUID id) {
        service.deleteEmail(id);
    }

    public User usersSearch() {

    }
}
