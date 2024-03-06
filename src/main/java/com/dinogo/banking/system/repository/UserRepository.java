package com.dinogo.banking.system.repository;

import com.dinogo.banking.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
