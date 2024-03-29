package ru.childbasket.domain.customer_order.dto;

import java.sql.Timestamp;
import java.util.List;

import ru.childbasket.domain.delivery_status.DeliveryStatus;
import ru.childbasket.domain.address.Address;
import ru.childbasket.domain.dish.Dish;

public class CustomerOrderResponseDto {
    private Long orderId;

    private DeliveryStatus deliveryStatus;

    private Timestamp deliveryDate;

    private Timestamp createdDate;

    private Timestamp updatedDate;

    private Address address;

    private List<Dish> dishes;
}
