package com.logirock.security_service.dto;

import java.util.UUID;

import com.logirock.security_service.enums.Rol;

public record UsuarioResponse(
        UUID id,
        String username,
        Rol rol) {
}
