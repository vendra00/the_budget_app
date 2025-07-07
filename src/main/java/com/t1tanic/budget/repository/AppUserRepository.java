package com.t1tanic.budget.repository;

import com.t1tanic.budget.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // You can add custom queries here later, like:
    Optional<AppUser> findByEmail(String email);
}
