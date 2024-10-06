package dtos;

import com.bilalbererk.Dukkan.utils.ShopCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

public record ShopUpdateDto(
        @NotBlank(message = "Name can not be blank")
        String name,
        @Size(max = 500, min = 20 , message = "Description can not exceed 500 characters and can not be under 20.")
        String description,
        @NotBlank(message = "ContactNumber can not be blank")
        String contactNumber,
        @Email
        String email,
        @NotBlank(message = "Category can not be blank")
        ShopCategory shopCategory,
        @NotBlank(message = "OpeningHours can not be blank")
        String openingHours,
        String website
) {
}
