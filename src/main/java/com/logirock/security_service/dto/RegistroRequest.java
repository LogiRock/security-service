package com.logirock.security_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroRequest {

    @NotBlank(message = "El username es obligatorio")
    @Size(max = 100, message = "El username no puede superar 100 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 72,
          message = "La contraseña debe tener entre 8 y 72 caracteres")
    private String password;
}
