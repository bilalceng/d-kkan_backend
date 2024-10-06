package com.bilalbererk.Dukkan.services;

import com.bilalbererk.Dukkan.Exceptions.ProductNotFoundException;
import com.bilalbererk.Dukkan.Exceptions.ShopNotFoundException;
import com.bilalbererk.Dukkan.Exceptions.UserDidNotFoundException;
import com.bilalbererk.Dukkan.mapper.Mapper;
import com.bilalbererk.Dukkan.models.Product;
import com.bilalbererk.Dukkan.models.User;
import com.bilalbererk.Dukkan.models.Shop;
import com.bilalbererk.Dukkan.repositories.ProductRepository;
import com.bilalbererk.Dukkan.repositories.ShopRepository;
import com.bilalbererk.Dukkan.repositories.UserRepository;
import dtos.ProductRequestDto;
import dtos.ProductResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;

    private User getAuthenticatedUser() {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new UserDidNotFoundException("User Not Found"));
    }


    private Shop getShopById(Integer shopId) {
        return shopRepository.findById(shopId)
                .orElseThrow(() -> new ShopNotFoundException("Shop not found"));
    }


    private Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    public void addProductToSpecificShop(Integer shopId, ProductRequestDto productRequestDto){
        getAuthenticatedUser();
        Shop shop = getShopById(shopId);
        Product product = Mapper.fromProductRequestDto(productRequestDto);
        shop.getProducts().add(product);
        product.setShop(shop);
        productRepository.save(product);
    }

    public Page<ProductResponseDto> findAllByShopId(Integer shopId, int page, int size) {
        getAuthenticatedUser();
        getShopById(shopId);

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByShopId(shopId, pageable);

        return products.map(Mapper::toProductResponseDto);
    }

    public Page<ProductResponseDto> findAllByTypeAndShopId(Integer shopId, String type, int page, int size) {
        getAuthenticatedUser();
        getShopById(shopId);

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByTypeAndShopId(type, shopId, pageable);

        return products.map(Mapper::toProductResponseDto);
    }

    public Page<ProductResponseDto> findByNameContainingIgnoreCase(String name, int page, int size) {
        getAuthenticatedUser();

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, pageable);

        return products.map(Mapper::toProductResponseDto);
    }

    public Page<ProductResponseDto> findByPriceBetween(Double min, Double max, int page, int size) {
        getAuthenticatedUser();

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByPriceBetween(min, max, pageable);

        return products.map(Mapper::toProductResponseDto);
    }

    public Page<ProductResponseDto> findByOrderBySoldQuantityDesc(int page, int size) {
        getAuthenticatedUser();

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByOrderBySoldQuantityDesc(pageable);

        return products.map(Mapper::toProductResponseDto);
    }

    public Page<ProductResponseDto> findByDiscountGreaterThan(double discount, int page, int size) {
        getAuthenticatedUser();

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByDiscountGreaterThan(discount, pageable);

        return products.map(Mapper::toProductResponseDto);
    }

    public Page<ProductResponseDto> findByQuantityEquals(int quantity, int page, int size) {
        getAuthenticatedUser();

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByQuantityEquals(quantity, pageable);

        return products.map(Mapper::toProductResponseDto);
    }

    public Page<ProductResponseDto> findByQuantityBetween(Integer min, Integer max, int page, int size) {
        getAuthenticatedUser();

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByQuantityBetween(min, max, pageable);

        return products.map(Mapper::toProductResponseDto);
    }

    public void updateProductInShop(Integer productId, Integer shopId, ProductRequestDto productRequestDto) {
        Shop shop = getShopById(shopId);
        Product product = productRepository.findByIdAndShopId(productId, shopId);
        productRepository.updateProductByIdAndShopId(
                productId,
                shopId,
                productRequestDto.category(),
                productRequestDto.type(),
                productRequestDto.name(),
                productRequestDto.description(),
                productRequestDto.price(),
                productRequestDto.quantity(),
                productRequestDto.brand()

        );
    }

}
