package ru.childmarket.childmarket.domain.allergen;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.allergen.dto.AllergenResponseDto;

@Mapper(componentModel = "spring")
public interface AllergenMapper {
    AllergenResponseDto map(Allergen allergen);
}
