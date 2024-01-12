package ru.childmarket.childmarket.domain.customer_order;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.customer_order.dto.CustomerOrderResponseDto;

@Mapper(componentModel = "spring")
public interface CustomerOrderMapper {
    CustomerOrderResponseDto map(CustomerOrder customerOrder);
}
