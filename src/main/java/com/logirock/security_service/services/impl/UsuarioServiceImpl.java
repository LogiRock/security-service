package com.logirock.security_service.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.logirock.security_service.dto.AuthResponse;
import com.logirock.security_service.dto.LoginRequest;
import com.logirock.security_service.dto.RegistroRequest;
import com.logirock.security_service.dto.UsuarioResponse;
import com.logirock.security_service.enums.Rol;
import com.logirock.security_service.models.Usuario;
import com.logirock.security_service.repositories.UsuarioRepository;
import com.logirock.security_service.security.JwtService;
import com.logirock.security_service.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${security.jwt.expiration-seconds:1800}")
    private long expirationSeconds;

    @Override
    public UsuarioResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "El nombre de usuario ya existe"
            );
        }

        Usuario usuario = Usuario.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .rol(Rol.CLIENTE)
            .build();

        Usuario guardado = usuarioRepository.save(usuario);

        return new UsuarioResponse(
            guardado.getId(),
            guardado.getUsername(),
            guardado.getRol()
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository
            .findByUsername(request.username())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Credenciales incorrectas"
            ));

        if (!passwordEncoder.matches(
                request.password(),
                usuario.getPassword())) {

            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Credenciales incorrectas"
            );
        }

        String token = jwtService.generarToken(usuario);

        return new AuthResponse(
            token,
            "Bearer",
            expirationSeconds
        );
    }
}