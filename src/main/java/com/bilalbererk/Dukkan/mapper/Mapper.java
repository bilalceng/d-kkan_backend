package com.bilalbererk.Dukkan.mapper;

import com.bilalbererk.Dukkan.models.Address;
import com.bilalbererk.Dukkan.models.Product;
import com.bilalbererk.Dukkan.models.Shop;
import dtos.*;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public static Shop toShop(
      ShopRequestDto shopDto
    ){
        return  Shop.builder()
                .name(shopDto.name())
                .description(shopDto.description())
                .contactNumber(shopDto.contactNumber())
                .email(shopDto.email())
                .shopCategory(shopDto.shopCategory())
                .openingHours(shopDto.openingHours())
                .website(shopDto.website())
                .rating(shopDto.rating())
                .soldProductNumber(shopDto.soldProductNumber())
                .messageNumber(shopDto.messageNumber())
                .build();
    }

    public static ShopResponseDto toShopResponseDto(
            Shop shop
    ){
        return new ShopResponseDto(
                shop.getId(),
                shop.getName(),
                shop.getDescription(),
                shop.getContactNumber(),
                shop.getRating(),
                shop.getShopCategory(),
                shop.getContactNumber()

        );
    }

    public static Address toAddress(AddressRequestDto addressRequestDto){
        return Address.builder()
                .street(addressRequestDto.street())
                .city(addressRequestDto.city())
                .country(addressRequestDto.country())
                .state(addressRequestDto.state())
                .addressType(addressRequestDto.addresstype())
                .isDefault(addressRequestDto.isDefault())
                .build();

    }

    public static AddressResponseDto toAddressResponseDto(Address address){
        return new AddressResponseDto(
                address.getId()
        );
    }

    public static ProductResponseDto toProductResponseDto(Product product){
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public static Product  fromProductRequestDto(ProductRequestDto productRequestDto){
        return  Product.builder()
                .category(productRequestDto.category())
                .description(productRequestDto.description())
                .brand(productRequestDto.brand())
                .name(productRequestDto.name())
                .price(productRequestDto.price())
                .quantity(productRequestDto.quantity())
                .type(productRequestDto.type())
                .build();
    }
}
