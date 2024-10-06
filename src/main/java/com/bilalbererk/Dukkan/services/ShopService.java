package com.bilalbererk.Dukkan.services;

import com.bilalbererk.Dukkan.Exceptions.*;
import com.bilalbererk.Dukkan.mapper.Mapper;
import com.bilalbererk.Dukkan.models.Address;
import com.bilalbererk.Dukkan.models.Shop;
import com.bilalbererk.Dukkan.models.User;
import com.bilalbererk.Dukkan.repositories.AddressRepository;
import com.bilalbererk.Dukkan.repositories.ShopRepository;
import com.bilalbererk.Dukkan.repositories.UserRepository;
import dtos.ShopRequestDto;
import dtos.ShopResponseDto;
import dtos.ShopUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ShopRepository shopRepository;

    private User getAuthenticatedUser() {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new UserDidNotFoundException("User not found"));
    }

    public ShopResponseDto saveShop(ShopRequestDto shopDto) {
        User user = getAuthenticatedUser();
        Address address = addressRepository.findById(shopDto.addressId())
                .orElseThrow(() -> new AddressNotFoundException("Address with ID " + shopDto.addressId() + " not found"));

        Shop shop = Mapper.toShop(shopDto);
        shop.setUser(user);
        shop.setAddress(address);
        Shop savedShop = shopRepository.save(shop);
        return Mapper.toShopResponseDto(savedShop);
    }

    public Page<ShopResponseDto> fetchAllShopsByEmail(int page, int size) {
        User user = getAuthenticatedUser();
        Pageable pageable = PageRequest.of(page, size);
        Page<Shop> allShops = shopRepository.findAllByUser(user, pageable);
        return allShops.map(Mapper::toShopResponseDto);
    }

    public Page<ShopResponseDto> filterShopsAccordingToRating(double rating, Pageable pageable) {
        Page<Shop> highRatingShops = shopRepository.filterShopsAccordingToRating(rating, pageable);
        return highRatingShops.map(Mapper::toShopResponseDto);
    }

    public Page<ShopResponseDto> filterShopsAccordingToMessageNumber(int messageNumber, Pageable pageable) {
        Page<Shop> highMessageNumberShops = shopRepository.filterShopsAccordingToMessageNumber(messageNumber, pageable);
        return highMessageNumberShops.map(Mapper::toShopResponseDto);
    }

    public Page<ShopResponseDto> filterByNameContainingIgnoreCase(String name, Pageable pageable) {
        Page<Shop> filteredShops = shopRepository.filterByNameContainingIgnoreCase(name, pageable);
        return filteredShops.map(Mapper::toShopResponseDto);
    }

    public Page<ShopResponseDto> filterShopsAccordingToSoldProductNumber(int number, Pageable pageable) {
        Page<Shop> highProductNumberShops = shopRepository.filterShopsAccordingToSoldProductNumber(number, pageable);
        return highProductNumberShops.map(Mapper::toShopResponseDto);
    }

    public void deleteShop(Integer shopId) {
        User user = getAuthenticatedUser();
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ShopNotFoundException("The shop you are trying to delete does not exist."));

        if (!shop.getUser().getId().equals(user.getId())) {
            throw new UserNotAuthorizedActionException("You are not authorized to delete this shop.");
        }
        shopRepository.delete(shop);
    }

    public Shop updateShop(ShopUpdateDto shopUpdateDto, Integer shopId) {
        User user = getAuthenticatedUser();
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ShopNotFoundException("The shop you are trying to update does not exist."));

        if (!shop.getUser().getId().equals(user.getId())) {
            throw new UserNotAuthorizedActionException("You are not authorized to update this shop.");
        }

        shop.setName(shopUpdateDto.name());
        shop.setDescription(shopUpdateDto.description());
        shop.setContactNumber(shopUpdateDto.contactNumber());
        shop.setEmail(shopUpdateDto.email());
        shop.setShopCategory(shopUpdateDto.shopCategory());
        shop.setOpeningHours(shopUpdateDto.openingHours());
        shop.setWebsite(shopUpdateDto.website());

        return shopRepository.save(shop);
    }
}
