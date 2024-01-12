package ru.childmarket.childmarket.domain.parent;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.childmarket.childmarket.domain.parent.dto.ParentRegisterDto;

@Mapper(componentModel = "spring")
public interface ParentMapper {

    Parent map(ParentRegisterDto parentRegisterDto);
}
