package ru.childbasket.domain.dish_type.dto;

import lombok.Data;

@Data
public class DishTypeResponseDto {
    private Long typeId;

    private String name;

    private String description;
}
