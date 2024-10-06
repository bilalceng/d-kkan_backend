package com.bilalbererk.Dukkan.controllers;

import com.bilalbererk.Dukkan.services.AddressService;
import dtos.AddressRequestDto;
import dtos.AddressResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/main/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponseDto> saveAddress(
            @RequestBody AddressRequestDto addressRequestDto
            ){
       return  ResponseEntity.ok(addressService.saveAddress(addressRequestDto));
    }
}
