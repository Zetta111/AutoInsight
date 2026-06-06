package com.carsrec.app.repository;

import com.carsrec.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Provides DB access for the users table.
// findAll, findById, save, delete, etc. are inherited from JpaRepository.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Looks up a user by email — used during login and JWT validation
    Optional<User> findByEmail(String email);

    // Checks if an email is already registered — used during registration to prevent duplicates
    boolean existsByEmail(String email);
}
