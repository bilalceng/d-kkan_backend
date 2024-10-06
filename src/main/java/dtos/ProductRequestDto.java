package dtos;

import com.bilalbererk.Dukkan.utils.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductRequestDto(
        @NotBlank(message = "Category can not be be blank")
         ProductCategory category,
        @NotBlank(message = "Type can not be be blank")
         String type,
        @NotBlank(message = "Name can not be be blank")
         String name,
        @NotBlank(message = "Description can not be be blank")
        @Size(max = 500, message = "Description can include  maximum 500 characters")
         String description,
        @NotBlank(message = "Price can not be be blank")
         Double price,
        @NotBlank(message = "Quantity can not be be blank")
         Integer quantity,
        @NotBlank(message = "Brand can not be be blank")
         String brand
){
}
