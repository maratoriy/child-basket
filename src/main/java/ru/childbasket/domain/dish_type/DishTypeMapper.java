package ru.childbasket.domain.dish_type;

import org.mapstruct.Mapper;
import ru.childbasket.domain.dish_type.dto.DishTypeResponseDto;

@Mapper(componentModel = "spring")
public interface DishTypeMapper {
    DishTypeResponseDto map(DishType dishType);
}
