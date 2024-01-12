package ru.childmarket.childmarket.domain.dish_type;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.dish_type.dto.DishTypeResponseDto;

@Mapper(componentModel = "spring")
public interface DishTypeMapper {
    DishTypeResponseDto map(DishType dishType);
}
