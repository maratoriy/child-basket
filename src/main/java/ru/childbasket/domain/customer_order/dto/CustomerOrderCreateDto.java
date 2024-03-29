package ru.childbasket.domain.customer_order.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class CustomerOrderCreateDto {
    private final Long subscriptionId;
    private final Timestamp deliveryDate;

    private final Long addressId;

    private final List<Long> dishesId;
}
