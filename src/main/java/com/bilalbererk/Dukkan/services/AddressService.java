package com.bilalbererk.Dukkan.services;

import com.bilalbererk.Dukkan.Exceptions.UserDidNotFoundException;
import com.bilalbererk.Dukkan.mapper.Mapper;
import com.bilalbererk.Dukkan.models.Address;
import com.bilalbererk.Dukkan.models.User;
import com.bilalbererk.Dukkan.repositories.AddressRepository;
import com.bilalbererk.Dukkan.repositories.UserRepository;
import dtos.AddressRequestDto;
import dtos.AddressResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    /**
     * Helper method to get the authenticated user based on the email in the security context
     */
    private User getAuthenticatedUser() {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new UserDidNotFoundException("User not found"));
    }

    public AddressResponseDto saveAddress(AddressRequestDto addressRequestDto) {
        User user = getAuthenticatedUser();
        Address address = Mapper.toAddress(addressRequestDto);

        Address savedAddress = addressRepository.save(address);

        return Mapper.toAddressResponseDto(savedAddress);
    }
}

