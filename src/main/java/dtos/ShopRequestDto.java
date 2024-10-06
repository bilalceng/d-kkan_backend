package dtos;

import com.bilalbererk.Dukkan.utils.ShopCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ShopRequestDto(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Description cannot be blank")
        @Size(max = 500, message = "Description can include  maximum 500 characters")
        String description,
        @NotBlank(message = "Contact Number cannot be blank")
        String contactNumber,
        @Email(message = "email should be in correct format")
        String email,
        @NotNull(message = "Category Number cannot be blank")
        ShopCategory shopCategory,
        @NotBlank(message = "Opening Hours  cannot be blank")
        String openingHours,
        String website,

        double rating,
        Integer addressId,
        int soldProductNumber,
        int messageNumber
        ){}



