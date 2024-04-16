package ru.childbasket.domain.subscription.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SubscriptionCreateDto {
    private final Long childId;
    private final Long subscriptionPlanId;
    private Timestamp startDate;

    private Timestamp endDate;
}
