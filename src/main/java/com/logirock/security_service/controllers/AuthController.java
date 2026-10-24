package com.logirock.security_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logirock.security_service.dto.RegistroRequest;
import com.logirock.security_service.dto.UsuarioResponse;
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

        UsuarioResponse response = usuarioService.registrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
