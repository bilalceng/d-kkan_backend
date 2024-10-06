package com.bilalbererk.Dukkan.repositories;

import com.bilalbererk.Dukkan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String Email);
    Boolean existsByEmail(String email);
}
