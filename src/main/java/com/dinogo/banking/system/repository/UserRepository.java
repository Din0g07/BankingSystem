package com.dinogo.banking.system.repository;

import com.dinogo.banking.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "SELECT * FROM users u " +
            "JOIN bank_accounts.phone_numbers pn on u.id = pn.user_id " +
            "JOIN bank_accounts.emails e ON u.id = e.user_id " +
            "WHERE (:date IS NOT NULL AND u.date_of_birth = :date) " +
            "AND (:phoneNumber IS NOT NULL AND pn.phone_number = :phoneNumber) " +
            "AND (:fullName IS NOT NULL AND u.full_name = :fullName) " +
            "AND (:email IS NOT NULL AND e.email = :email)", nativeQuery = true)
    List<User> usersSearch(Date date,
                           String phoneNumber,
                           String fullName,
                           String email);
}
