package ru.childbasket.domain.dish;

import org.mapstruct.Mapper;
import ru.childbasket.domain.dish.dto.DishResponseDto;

@Mapper(componentModel = "spring")
public interface DishMapper {
    DishResponseDto map(Dish dish);
}
