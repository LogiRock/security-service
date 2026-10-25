package com.logirock.security_service.repositories;

import java.util.UUID;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.logirock.security_service.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
}
