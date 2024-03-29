package ru.childbasket.domain.subscription.dto;

import java.sql.Timestamp;

import lombok.Data;
import ru.childbasket.domain.subscription_plan.SubscriptionPlan;
import ru.childbasket.domain.child.Child;

@Data
public class SubscriptionResponseDto {
    private Long subscriptionId;

    private SubscriptionPlan plan;

    private Child child;

    private Integer mealLeft;

    private Timestamp startDate;

    private Timestamp endDate;
}
