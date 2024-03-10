package com.dinogo.banking.system.repository;

import com.dinogo.banking.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "SELECT u.* FROM users u " +
            "JOIN phone_numbers pn on u.id = pn.user_id " +
            "JOIN emails e ON u.id = e.user_id " +
            "WHERE (:date IS NULL OR u.date_of_birth > :date) " +
            "AND (:phoneNumber IS NULL OR pn.phone_number = :phoneNumber) " +
            "AND (:fullName IS NULL OR u.full_name LIKE CONCAT(:fullName, '%')) " +
            "AND (:email IS NULL OR e.email = :email)",
            nativeQuery = true)
    List<User> usersSearch(Date date,
                           String phoneNumber,
                           String fullName,
                           String email);
}
