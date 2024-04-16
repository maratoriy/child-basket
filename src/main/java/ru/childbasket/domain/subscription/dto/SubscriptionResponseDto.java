package ru.childbasket.domain.subscription.dto;

import lombok.Data;
import ru.childbasket.domain.child.Child;
import ru.childbasket.domain.subscription_plan.SubscriptionPlan;

import java.sql.Timestamp;

@Data
public class SubscriptionResponseDto {
    private Long subscriptionId;

    private SubscriptionPlan plan;

    private Child child;

    private Integer mealLeft;

    private Timestamp startDate;

    private Timestamp endDate;
}
