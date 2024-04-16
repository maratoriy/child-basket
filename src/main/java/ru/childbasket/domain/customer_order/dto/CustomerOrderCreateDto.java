package ru.childbasket.domain.customer_order.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CustomerOrderCreateDto {
    private final Long subscriptionId;
    private final Timestamp deliveryDate;

    private final Long addressId;

    private final List<Long> dishesId;
}
