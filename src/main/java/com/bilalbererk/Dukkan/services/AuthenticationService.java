package com.bilalbererk.Dukkan.services;

import com.bilalbererk.Dukkan.dao.request.SignInRequest;
import com.bilalbererk.Dukkan.dao.request.SignUpRequest;
import com.bilalbererk.Dukkan.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
