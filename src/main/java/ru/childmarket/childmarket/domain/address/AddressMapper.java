package ru.childmarket.childmarket.domain.address;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.address.dto.AddressCreateDto;
import ru.childmarket.childmarket.domain.address.dto.AddressResponseDto;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponseDto map(Address address);
    Address map(AddressCreateDto addressCreateDto);
}
