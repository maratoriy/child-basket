package ru.childmarket.childmarket.domain.dish;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.childmarket.childmarket.domain.allergen.AllergenMapper;
import ru.childmarket.childmarket.domain.dish.dto.DishResponseDto;
import ru.childmarket.childmarket.domain.ingredient.IngredientMapper;

@Component
@RequiredArgsConstructor
public class DishFullMapper {
    private final DishMapper dishMapper;
    private final AllergenMapper allergenMapper;
    private final IngredientMapper ingredientMapper;

    public DishResponseDto map(Dish dish) {
        final DishResponseDto dishResponseDto = dishMapper.map(dish);

        dishResponseDto.setAllergens(dish.getAllergens().stream().map(allergenMapper::map).collect(Collectors.toSet()));
        dishResponseDto.setIngredients(dish.getIngredients().stream().map(ingredientMapper::map).collect(Collectors.toSet()));

        return dishResponseDto;
    }
}
