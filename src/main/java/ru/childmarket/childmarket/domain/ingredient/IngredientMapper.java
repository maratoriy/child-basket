package ru.childmarket.childmarket.domain.ingredient;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.ingredient.dto.IngredientResponseDto;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientResponseDto map(Ingredient ingredient);
}
