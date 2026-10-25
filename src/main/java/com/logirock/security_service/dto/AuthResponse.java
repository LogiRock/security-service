package com.logirock.security_service.dto;

public record AuthResponse(
    String accessToken,
    String tokenType,
    long expiresIn
) {}
