package com.dinogo.banking.system.controller;

import com.dinogo.banking.system.entity.DTO.EmailDTO;
import com.dinogo.banking.system.entity.DTO.PhoneNumberDTO;
import com.dinogo.banking.system.entity.Email;
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
    public void addNewPhoneNumber(@RequestBody PhoneNumberDTO phoneNumberDTO) {
        service.addNewPhoneNumber(phoneNumberDTO);
    }

    @PutMapping("/phoneNumbes/{id}")
    public void updatePhoneNumber(@RequestBody PhoneNumberDTO phoneNumberDTO,
                                  @PathVariable UUID id) {
        service.updatePhoneNumber(phoneNumberDTO, id);
    }

    @DeleteMapping("/phoneNumbes/{id}")
    public void deletePhoneNumber(@PathVariable UUID id) {
        service.deletePhoneNumber(id);
    }

    @PostMapping("/emails")
    public UUID addNewEmail(@RequestBody EmailDTO emailDTO) {
        return service.addNewEmail(emailDTO);
    }

    @PutMapping("/emails")
    public UUID updateEmail(@RequestBody EmailDTO emailDTO,
                            @PathVariable UUID id) {
        return service.updateEmail(emailDTO, id);
    }

    @DeleteMapping("/emails/{id}")
    public void deleteEmail(@PathVariable UUID id) {
        service.deleteEmail(id);
    }

//    @GetMapping("/users/search")
//    public UserDTO usersSearch(@RequestParam(required = false) Date date,
//                               @RequestParam(required = false) String phoneNumber,
//                               @RequestParam(required = false) String fullName,
//                               @RequestParam(required = false) String email) {
//
//    }
}
