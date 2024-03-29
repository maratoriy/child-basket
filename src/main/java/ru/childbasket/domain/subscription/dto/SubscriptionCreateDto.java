package ru.childbasket.domain.subscription.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SubscriptionCreateDto {
    private final Long childId;
    private final Long subscriptionPlanId;
    private Timestamp startDate;

    private Timestamp endDate;
}
