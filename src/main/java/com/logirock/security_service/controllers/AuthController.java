package com.logirock.security_service.controllers;

import java.util.UUID;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logirock.security_service.dto.AuthResponse;
import com.logirock.security_service.dto.LoginRequest;
import com.logirock.security_service.dto.RegistroRequest;
import com.logirock.security_service.dto.UsuarioResponse;
import com.logirock.security_service.enums.Rol;
import com.logirock.security_service.services.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> registrar(
            @Valid @RequestBody RegistroRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.registrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(usuarioService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> usuarioActual(
            @AuthenticationPrincipal Jwt jwt) {

        UsuarioResponse response = new UsuarioResponse(
                UUID.fromString(jwt.getClaimAsString("userId")),
                jwt.getSubject(),
                Rol.valueOf(
                        jwt.getClaimAsStringList("roles")
                                .get(0)
                                .replace("ROLE_", "")));

        return ResponseEntity.ok(response);
    }
}
