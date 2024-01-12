package ru.childmarket.childmarket.domain.subscription;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.subscription.dto.SubscriptionResponseDto;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponseDto map(Subscription subscription);
}
