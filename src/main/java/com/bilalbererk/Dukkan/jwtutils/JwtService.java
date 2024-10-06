package com.bilalbererk.Dukkan.jwtutils;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(HashMap<String,Object> extraClaims, UserDetails userDetails);

    boolean isTokenValid(String token,UserDetails userDetails);
}
