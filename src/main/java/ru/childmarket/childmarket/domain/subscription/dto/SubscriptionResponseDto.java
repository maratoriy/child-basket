package ru.childmarket.childmarket.domain.subscription.dto;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ru.childmarket.childmarket.domain.child.Child;
import ru.childmarket.childmarket.domain.subscription_plan.SubscriptionPlan;

@Data
public class SubscriptionResponseDto {
    private Long subscriptionId;

    private SubscriptionPlan plan;

    private Child child;

    private Integer mealLeft;

    private Timestamp startDate;

    private Timestamp endDate;
}
