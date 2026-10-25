
package com.logirock.security_service.services;

import com.logirock.security_service.dto.AuthResponse;
import com.logirock.security_service.dto.LoginRequest;
import com.logirock.security_service.dto.RegistroRequest;
import com.logirock.security_service.dto.UsuarioResponse;

public interface UsuarioService {

    UsuarioResponse registrar(RegistroRequest request);

    AuthResponse login(LoginRequest request);
}
