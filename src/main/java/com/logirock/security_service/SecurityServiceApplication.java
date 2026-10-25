package com.logirock.security_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.logirock.security_service.enums.Rol;
import com.logirock.security_service.models.Usuario;
import com.logirock.security_service.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner crearAdmin(
			UsuarioRepository usuarioRepository,
			PasswordEncoder passwordEncoder,
			@Value("${ADMIN_USERNAME}") String username,
			@Value("${ADMIN_PASSWORD}") String password) {

		return args -> {
			if (!usuarioRepository.existsByUsername(username)) {
				Usuario admin = Usuario.builder()
						.username(username)
						.password(passwordEncoder.encode(password))
						.rol(Rol.ADMIN)
						.build();

				usuarioRepository.save(admin);

				System.out.println("Administrador inicial creado.");
			}
		};
	}
}
