package dtos;

import com.bilalbererk.Dukkan.utils.AddressType;
import jakarta.validation.constraints.NotBlank;

public record AddressRequestDto(
        @NotBlank(message = "Street can not be blank")
        String street,
        @NotBlank(message = "Street can not be blank")
        String city,
        String country,
        String postalCode,
        String state,
        Boolean isDefault,
        AddressType addresstype
) {
}
