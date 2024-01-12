package ru.childmarket.childmarket.domain.subscription_plan;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.subscription_plan.dto.SubscriptionPlanResponseDto;

@Mapper(componentModel = "spring")
public interface SubscriptionPlanMapper {
    SubscriptionPlanResponseDto map(SubscriptionPlan subscriptionPlan);
}
