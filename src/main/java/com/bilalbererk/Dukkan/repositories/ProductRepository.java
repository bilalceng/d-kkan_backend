package com.bilalbererk.Dukkan.repositories;

import com.bilalbererk.Dukkan.models.Product;
import com.bilalbererk.Dukkan.models.Shop;
import com.bilalbererk.Dukkan.utils.ProductCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByShopId(Integer shopId, Pageable pageable);

    Page<Product> findAllByTypeAndShopId(String type,Integer ShopId,Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findByPriceBetween(Double min, Double max, Pageable pageable);

    Page<Product> findByOrderBySoldQuantityDesc(Pageable pageable);

    Page<Product> findByDiscountGreaterThan(double discount, Pageable pageable);

    Page<Product> findByQuantityEquals(int quantity, Pageable pageable);

    Page<Product> findByQuantityBetween(Integer min, Integer max, Pageable pageable);


    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.category = :category, p.type = :type, p.name = :name, " +
            "p.description = :description, p.price = :price, p.quantity = :quantity, p.brand = :brand " +
            "WHERE p.id = :id AND p.shop.id = :shopId")
    void updateProductByIdAndShopId(
            @Param("id") Integer id,
            @Param("shopId") Integer shopId,
            @Param("category") ProductCategory category,
            @Param("type") String type,
            @Param("name") String name,
            @Param("description") String description,
            @Param("price") Double price,
            @Param("quantity") Integer quantity,
            @Param("brand") String brand);

    Product findByIdAndShopId(Integer productId, Integer shopId);
}
