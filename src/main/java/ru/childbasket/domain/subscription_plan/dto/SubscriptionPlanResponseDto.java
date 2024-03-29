package ru.childbasket.domain.subscription_plan.dto;

import lombok.Data;

@Data
public class SubscriptionPlanResponseDto {
    private Long planId;

    private String name;

    private String description;

    private Integer price;

    private Integer mealFrequency;

    private Integer mealPerOrder;
}
