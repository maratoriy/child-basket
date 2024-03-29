package ru.childbasket.domain.ingredient;

import org.mapstruct.Mapper;
import ru.childbasket.domain.ingredient.dto.IngredientResponseDto;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientResponseDto map(Ingredient ingredient);
}
