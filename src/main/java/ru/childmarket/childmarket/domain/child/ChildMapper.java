package ru.childmarket.childmarket.domain.child;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.child.dto.ChildCreateDto;
import ru.childmarket.childmarket.domain.child.dto.ChildResponseDto;

@Mapper(componentModel = "spring")
public interface ChildMapper {

    ChildResponseDto map(Child child);
    Child map(ChildCreateDto childCreateDto);
}
