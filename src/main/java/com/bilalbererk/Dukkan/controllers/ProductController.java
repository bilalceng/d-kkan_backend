package com.bilalbererk.Dukkan.controllers;

import com.bilalbererk.Dukkan.services.ProductService;
import com.bilalbererk.Dukkan.services.ShopService;
import dtos.ProductRequestDto;
import dtos.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ShopService shopService;

    // Retrieve all products by Shop ID
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<Page<ProductResponseDto>> getAllProductsByShopId(
            @PathVariable Integer shopId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponseDto> products = productService.findAllByShopId(shopId, page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/shop/{shopId}/type/{type}")
    public ResponseEntity<Page<ProductResponseDto>> getProductsByTypeAndShopId(
            @PathVariable Integer shopId,
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponseDto> products = productService.findAllByTypeAndShopId(shopId, type, page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDto>> searchProductsByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponseDto> products = productService.findByNameContainingIgnoreCase(name, page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter/price")
    public ResponseEntity<Page<ProductResponseDto>> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponseDto> products = productService.findByPriceBetween(min, max, page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/sort/sold-quantity")
    public ResponseEntity<Page<ProductResponseDto>> getProductsSortedBySoldQuantity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponseDto> products = productService.findByOrderBySoldQuantityDesc(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter/discount")
    public ResponseEntity<Page<ProductResponseDto>> getProductsByDiscount(
            @RequestParam double discount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponseDto> products = productService.findByDiscountGreaterThan(discount, page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter/quantity")
    public ResponseEntity<Page<ProductResponseDto>> getProductsByQuantity(
            @RequestParam int quantity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponseDto> products = productService.findByQuantityEquals(quantity, page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter/quantity-range")
    public ResponseEntity<Page<ProductResponseDto>> getProductsByQuantityRange(
            @RequestParam Integer min,
            @RequestParam Integer max,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponseDto> products = productService.findByQuantityBetween(min, max, page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PutMapping("/shop/{shopId}/product/{productId}")
    public ResponseEntity<Void> updateProductInShop(
            @PathVariable Integer productId,
            @PathVariable Integer shopId,
            @RequestBody ProductRequestDto productRequestDto){
        productService.updateProductInShop(productId,shopId, productRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/shop/{shopId}")
    public ResponseEntity<Void> addProductToSpecificShop(
            @PathVariable Integer shopId,
            @RequestBody ProductRequestDto productRequestDto){
        productService.addProductToSpecificShop(shopId, productRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
