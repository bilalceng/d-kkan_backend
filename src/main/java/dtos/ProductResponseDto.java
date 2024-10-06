package dtos;

import com.bilalbererk.Dukkan.models.BaseEntity;
import jakarta.persistence.*;

public record ProductResponseDto(
        Integer id,
        String name,
        String description,
        Double price,
        Integer quantity
){

}

