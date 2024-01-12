package ru.childmarket.childmarket.domain.customer_order.dto;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.childmarket.childmarket.domain.address.Address;
import ru.childmarket.childmarket.domain.delivery_status.DeliveryStatus;
import ru.childmarket.childmarket.domain.dish.Dish;
import ru.childmarket.childmarket.domain.subscription.Subscription;

public class CustomerOrderResponseDto {
    private Long orderId;

    private DeliveryStatus deliveryStatus;

    private Timestamp deliveryDate;

    private Timestamp createdDate;

    private Timestamp updatedDate;

    private Address address;

    private List<Dish> dishes;
}
