package ru.childbasket.domain.child;

import org.mapstruct.Mapper;
import ru.childbasket.domain.child.dto.ChildCreateDto;
import ru.childbasket.domain.child.dto.ChildResponseDto;

@Mapper(componentModel = "spring")
public interface ChildMapper {

    ChildResponseDto map(Child child);
    Child map(ChildCreateDto childCreateDto);
}
