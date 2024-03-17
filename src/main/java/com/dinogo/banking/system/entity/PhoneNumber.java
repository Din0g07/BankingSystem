package com.dinogo.banking.system.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "phone_numbers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

}
