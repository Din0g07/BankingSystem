package com.dinogo.banking.system.repository;

import com.dinogo.banking.system.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, UUID> {
    List<PhoneNumber> findAllByUserId(UUID userId);
    @Query(value = "SELECT COUNT(*) FROM phone_numbers WHERE user_id = " +
            "(SELECT user_id FROM phone_numbers WHERE id = :phoneId)",
            nativeQuery = true)
    int countPhoneNumbersByUserWithPhoneId(UUID phoneId);

    @Query(value = "SELECT COUNT(*)\n" +
            "FROM phone_numbers\n" +
            "WHERE phone_number = :phoneNumber",
            nativeQuery = true)
    int countOfBusyPhoneNumbers(String phoneNumber);
}
