package com.bilalbererk.Dukkan.services;

import com.bilalbererk.Dukkan.Exceptions.UserAlreadyExistException;
import com.bilalbererk.Dukkan.Exceptions.UserDidNotFoundException;
import com.bilalbererk.Dukkan.dao.request.SignInRequest;
import com.bilalbererk.Dukkan.dao.request.SignUpRequest;
import com.bilalbererk.Dukkan.dao.response.JwtAuthenticationResponse;
import com.bilalbererk.Dukkan.jwtutils.JwtService;
import com.bilalbererk.Dukkan.models.User;
import com.bilalbererk.Dukkan.repositories.UserRepository;
import com.bilalbererk.Dukkan.utils.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistException("User with email " + request.getEmail() + " already exists.");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRoles.CUSTOMER)
                .build();

        var savedUser = userRepository.save(user);

        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(
                "saved_user_id",
                savedUser.getId()
        );
        extraClaims.put(
                "saved_user_role", savedUser.
                getUserRole()
                .name()
        );
        var jwt = jwtService.generateToken(extraClaims,user);
        return JwtAuthenticationResponse
                .builder()
                .token(jwt)
                .role(savedUser.getUserRole().name())
                .build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        if (!userRepository.existsByEmail(request.getEmail())) {
            throw new UserDidNotFoundException("User with email " + request.getEmail() + " not found");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(
                "saved_user_id",
                user.getId()
        );
        extraClaims.put("saved_user_role",
                user.getUserRole().name()
        );
        var jwt = jwtService.generateToken(extraClaims,user);
        return JwtAuthenticationResponse
                .builder()
                .token(jwt)
                .role("Customer")
                .build();
    }
}