
package com.logirock.security_service.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.logirock.security_service.dto.RegistroRequest;
import com.logirock.security_service.dto.UsuarioResponse;
import com.logirock.security_service.enums.Rol;
import com.logirock.security_service.models.Usuario;
import com.logirock.security_service.repositories.UsuarioRepository;
import com.logirock.security_service.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioResponse registrar(RegistroRequest request) {

        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El username ya está registrado"
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
}
