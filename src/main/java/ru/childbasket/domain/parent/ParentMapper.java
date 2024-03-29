package ru.childbasket.domain.parent;

import org.mapstruct.Mapper;
import ru.childbasket.domain.parent.dto.ParentRegisterDto;

@Mapper(componentModel = "spring")
public interface ParentMapper {

    Parent map(ParentRegisterDto parentRegisterDto);
}
