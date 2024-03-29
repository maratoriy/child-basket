package ru.childbasket.domain.allergen;

import org.mapstruct.Mapper;
import ru.childbasket.domain.allergen.dto.AllergenResponseDto;

@Mapper(componentModel = "spring")
public interface AllergenMapper {
    AllergenResponseDto map(Allergen allergen);
}
