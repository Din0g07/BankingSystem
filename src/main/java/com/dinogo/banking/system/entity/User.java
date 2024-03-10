package com.dinogo.banking.system.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private Date date;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<Email> emails;

    @OneToMany(cascade = CascadeType.ALL,
        mappedBy = "user")
    private List<PhoneNumber> phoneNumbers;

    @Column(name = "balance")
    private double balance;

    @Column(name = "initial_balance")
    private double initialBalance;

}
