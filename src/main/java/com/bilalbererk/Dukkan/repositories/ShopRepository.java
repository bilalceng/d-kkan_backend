package com.bilalbererk.Dukkan.repositories;

import com.bilalbererk.Dukkan.models.Shop;
import com.bilalbererk.Dukkan.models.User;
import com.bilalbererk.Dukkan.utils.ShopCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface ShopRepository  extends JpaRepository<Shop,Integer> {

    Page<Shop> findAllByUser(User user, Pageable pageable);

    @Query("SELECT s FROM Shop s WHERE s.rating >= :rating")
    Page<Shop> filterShopsAccordingToRating(@Param("rating") double rating, Pageable pageable);

    @Query("SELECT s FROM Shop s where s.messageNumber = :messageNumber")
    Page<Shop> filterShopsAccordingToMessageNumber(@Param("messageNumber") int messageNumber, Pageable pageable);

    @Query("SELECT s FROM Shop s WHERE  LOWER(s.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<Shop> filterByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    @Query("SELECT s FROM Shop s WHERE s.soldProductNumber >= :number")
    Page<Shop> filterShopsAccordingToSoldProductNumber(@Param("number") int number, Pageable pageable);

    @Query("SELECT s FROM Shop s WHERE s.shopCategory = :shopCategory")
    Page<Shop> filterShopsAccordingToCategory(@Param("shopCategory") ShopCategory shopCategory, Pageable pageable);

}
