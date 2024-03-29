package ru.childbasket.domain.subscription;

import org.mapstruct.Mapper;
import ru.childbasket.domain.subscription.dto.SubscriptionResponseDto;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponseDto map(Subscription subscription);
}
