package com.logirock.security_service.security;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import com.logirock.security_service.models.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.expiration-seconds:1800}")
    private long expirationSeconds;

    public String generarToken(Usuario usuario) {
        Instant ahora = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer(issuer)
            .issuedAt(ahora)
            .expiresAt(ahora.plusSeconds(expirationSeconds))
            .subject(usuario.getUsername())
            .claim("userId", usuario.getId().toString())
            .claim("roles", List.of("ROLE_" + usuario.getRol().name()))
            .build();

        JwsHeader header = JwsHeader
            .with(MacAlgorithm.HS256)
            .build();

        return jwtEncoder.encode(
            JwtEncoderParameters.from(header, claims)
        ).getTokenValue();
    }
}