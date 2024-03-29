package ru.childbasket.domain.subscription_plan;

import org.mapstruct.Mapper;
import ru.childbasket.domain.subscription_plan.dto.SubscriptionPlanResponseDto;

@Mapper(componentModel = "spring")
public interface SubscriptionPlanMapper {
    SubscriptionPlanResponseDto map(SubscriptionPlan subscriptionPlan);
}
