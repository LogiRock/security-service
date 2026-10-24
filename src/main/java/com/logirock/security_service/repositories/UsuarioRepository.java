package com.logirock.security_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logirock.security_service.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByUsername(String username);
}
