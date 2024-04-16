package ru.childbasket.domain.address;

import org.mapstruct.Mapper;
import ru.childbasket.domain.address.dto.AddressCreateDto;
import ru.childbasket.domain.address.dto.AddressResponseDto;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponseDto map(Address address);

    Address map(AddressCreateDto addressCreateDto);
}
