package ru.childmarket.childmarket.domain.dish.dto;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ru.childmarket.childmarket.domain.allergen.Allergen;
import ru.childmarket.childmarket.domain.allergen.dto.AllergenResponseDto;
import ru.childmarket.childmarket.domain.dish_type.DishType;
import ru.childmarket.childmarket.domain.ingredient.Ingredient;
import ru.childmarket.childmarket.domain.ingredient.dto.IngredientResponseDto;

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
