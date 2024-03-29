package com.dinogo.banking.system.repository;

import com.dinogo.banking.system.entity.Email;
import com.dinogo.banking.system.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {
    List<Email> findAllByUserId(UUID userId);
    @Query(value = "SELECT COUNT(*) FROM emails WHERE user_id = " +
            "(SELECT user_id FROM emails WHERE id = :emailId)",
            nativeQuery = true)
    int countEmailsByUserWithEmailId(UUID emailId);

    @Query(value = "SELECT COUNT(*) FROM emails " +
            "WHERE email = :email",
            nativeQuery = true)
    int countOfBusyEmails(String email);

}
