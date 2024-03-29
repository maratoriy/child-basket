package ru.childbasket.domain.customer_order;

import org.mapstruct.Mapper;
import ru.childbasket.domain.customer_order.dto.CustomerOrderResponseDto;

@Mapper(componentModel = "spring")
public interface CustomerOrderMapper {
    CustomerOrderResponseDto map(CustomerOrder customerOrder);
}
