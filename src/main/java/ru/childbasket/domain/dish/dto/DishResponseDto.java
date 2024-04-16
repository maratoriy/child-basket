package ru.childbasket.domain.dish.dto;

import lombok.Data;
import ru.childbasket.domain.allergen.dto.AllergenResponseDto;
import ru.childbasket.domain.ingredient.dto.IngredientResponseDto;

import java.util.Set;

@Data
public class DishResponseDto {
    private Long dishId;

    private String name;

    private String description;

    private Integer calories;

    private Integer proteins;

    private Integer fats;

    private Integer carbohydrates;

    private Set<IngredientResponseDto> ingredients;

    private Set<AllergenResponseDto> allergens;
}
