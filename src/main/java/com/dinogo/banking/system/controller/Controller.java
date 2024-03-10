package com.dinogo.banking.system.controller;

import com.dinogo.banking.system.entity.DTO.BalanceRqDTO;
import com.dinogo.banking.system.entity.DTO.BalanceRsDTO;
import com.dinogo.banking.system.entity.DTO.EmailDTO;
import com.dinogo.banking.system.entity.DTO.PhoneNumberDTO;
import com.dinogo.banking.system.entity.DTO.UserDTO;
import com.dinogo.banking.system.service.Service;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class Controller {
    @Autowired
    private Service service;

    @PostMapping("/users")
    public UUID addNewUser(@RequestBody @Valid UserDTO userDTO) {
        return service.addNewUser(userDTO);
    }

    @PostMapping("/phoneNumbers")
    public UUID addNewPhoneNumber(@RequestBody @Valid PhoneNumberDTO phoneNumberDTO) {
        return service.addNewPhoneNumber(phoneNumberDTO);
    }

    @PutMapping("/phoneNumbers/{id}")
    public UUID updatePhoneNumber(@RequestBody @Valid PhoneNumberDTO phoneNumberDTO,
                                  @NotNull(message = "ИД не должен быть пустым")
                                  @PathVariable UUID id) {
        return service.updatePhoneNumber(phoneNumberDTO, id);
    }

    @DeleteMapping("/phoneNumbers/{id}")
    public void deletePhoneNumber(@PathVariable UUID id) {
        service.deletePhoneNumber(id);
    }

    @PostMapping("/emails")
    public UUID addNewEmail(@RequestBody @Valid EmailDTO emailDTO) {
        return service.addNewEmail(emailDTO);
    }

    @PutMapping("/emails/{id}")
    public UUID updateEmail(@RequestBody @Valid EmailDTO emailDTO,
                            @NotNull(message = "ИД не должен быть пустым")
                            @PathVariable UUID id) {
        return service.updateEmail(emailDTO, id);
    }

    @DeleteMapping("/emails/{id}")
    public void deleteEmail(@NotNull(message = "ИД не должен быть пустым")
                            @PathVariable UUID id) {
        service.deleteEmail(id);
    }

    @GetMapping("/users/search")
    public List<UserDTO> usersSearch(@RequestParam(required = false) Date date,
                               @RequestParam(required = false) String phoneNumber,
                               @RequestParam(required = false) String fullName,
                               @RequestParam(required = false) String email) {
        return service.usersSearch(date, phoneNumber, fullName, email);
    }

    @PostMapping("/transfer")
    public BalanceRsDTO transfer(@RequestBody @Valid BalanceRqDTO balanceRqDTO) {
        return service.transfer(balanceRqDTO);
    }
}
