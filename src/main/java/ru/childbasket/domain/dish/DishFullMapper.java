package ru.childbasket.domain.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.childbasket.domain.allergen.AllergenMapper;
import ru.childbasket.domain.dish.dto.DishResponseDto;
import ru.childbasket.domain.ingredient.IngredientMapper;

import java.util.stream.Collectors;

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
