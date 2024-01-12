package ru.childmarket.childmarket.domain.dish;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.allergen.AllergenMapper;
import ru.childmarket.childmarket.domain.dish.dto.DishResponseDto;

@Mapper(componentModel = "spring")
public interface DishMapper {
    DishResponseDto map(Dish dish);
}
