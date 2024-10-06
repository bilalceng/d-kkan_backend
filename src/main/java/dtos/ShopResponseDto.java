package dtos;

import com.bilalbererk.Dukkan.utils.ShopCategory;

public record ShopResponseDto(
        Integer shopId,
        String name,
        String description,
        String contactNumber,
        double rating,
        ShopCategory category,
        String openingHours
) {
}
