package com.sena.leonardo.algamoneyapi.domain.repositories;

import com.sena.leonardo.algamoneyapi.domain.models.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSystemRepository extends JpaRepository<UserSystem, Long> {
    public Optional<UserSystem> findByEmail(String email);
}
