package com.bilalbererk.Dukkan.controllers;

import com.bilalbererk.Dukkan.models.Shop;
import com.bilalbererk.Dukkan.services.ShopService;
import dtos.ShopRequestDto;
import dtos.ShopResponseDto;
import dtos.ShopUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<Void> saveShop(
            @Valid @RequestBody ShopRequestDto shopDto
    ){
        ShopResponseDto dto = shopService.saveShop(shopDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.shopId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Page<ShopResponseDto>> fetchAllShopsByEmail(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
        Page<ShopResponseDto> shops = shopService.fetchAllShopsByEmail(page, size);
        return ResponseEntity.ok(shops);
    }

    @GetMapping("/rating")
    public ResponseEntity<Page<ShopResponseDto>> filterShopsByRating(
            @RequestParam(value = "rating", defaultValue = "4.0") double rating,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<ShopResponseDto> highRatingShops = shopService.filterShopsAccordingToRating(rating, pageable);
        return ResponseEntity.ok(highRatingShops);
    }

    @GetMapping("/message-number")
    public ResponseEntity<Page<ShopResponseDto>> filterShopsByMessageNumber(
            @RequestParam(value = "messageNumber", defaultValue = "0") int messageNumber,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<ShopResponseDto> highMessageNumberShops = shopService.filterShopsAccordingToMessageNumber(messageNumber, pageable);
        return ResponseEntity.ok(highMessageNumberShops);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ShopResponseDto>> filterShopsByName(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<ShopResponseDto> searchedResult = shopService.filterByNameContainingIgnoreCase(name, pageable);
        return ResponseEntity.ok(searchedResult);
    }

    @GetMapping("/top-products")
    public ResponseEntity<Page<ShopResponseDto>> filterShopsBySoldProductNumber(
            @RequestParam(value = "number", defaultValue = "10") int number,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<ShopResponseDto> topProductNumberList = shopService.filterShopsAccordingToSoldProductNumber(number, pageable);
        return ResponseEntity.ok(topProductNumberList);
    }

    @DeleteMapping("/{shopId}")
    public ResponseEntity<Void> deleteShop(
            @PathVariable Integer shopId
    ){
        shopService.deleteShop(shopId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{shopId}")
    public ResponseEntity<Shop> updateShop(
            @PathVariable Integer shopId,
            @Valid @RequestBody ShopUpdateDto shopUpdateDto
    ){
        Shop updatedShop = shopService.updateShop(shopUpdateDto, shopId);
        return ResponseEntity.ok(updatedShop);
    }
}
