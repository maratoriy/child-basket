package ru.childmarket.childmarket.domain.customer_order.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import ru.childmarket.childmarket.domain.address.Address;
import ru.childmarket.childmarket.domain.delivery_status.DeliveryStatus;
import ru.childmarket.childmarket.domain.dish.Dish;

@Data
public class CustomerOrderCreateDto {
    private final Long subscriptionId;
    private final Timestamp deliveryDate;

    private final Long addressId;

    private final List<Long> dishesId;
}
