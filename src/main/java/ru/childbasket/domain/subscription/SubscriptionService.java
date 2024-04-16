package ru.childbasket.domain.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.childbasket.domain.child.Child;
import ru.childbasket.domain.child.ChildRepository;
import ru.childbasket.domain.parent.exceptions.ParentNotExistsException;
import ru.childbasket.domain.subscription.dto.SubscriptionCreateDto;
import ru.childbasket.domain.subscription.dto.SubscriptionResponseDto;
import ru.childbasket.domain.subscription_plan.SubscriptionPlan;
import ru.childbasket.domain.subscription_plan.SubscriptionPlanMapper;
import ru.childbasket.domain.subscription_plan.SubscriptionPlanRepository;
import ru.childbasket.domain.subscription_plan.dto.SubscriptionPlanResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final ChildRepository childRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionPlanMapper subscriptionPlanMapper;

    public List<SubscriptionPlanResponseDto> getSubscriptionPlans() {
        return subscriptionPlanRepository.findAll().stream().map(subscriptionPlanMapper::map).collect(Collectors.toList());
    }

    public List<SubscriptionResponseDto> getSubscriptions(String phoneNumber, Long childId) {
        final Child child = childRepository.findById(childId).orElseThrow();
        if (!child.getParent().getPhoneNumber().equals(phoneNumber)) {
            throw new ParentNotExistsException(phoneNumber);
        }
        return child.getSubscriptions().stream()
                .map(subscriptionMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public void subscribe(String phoneNumber, SubscriptionCreateDto subscriptionCreateDto) {
        final Child child = childRepository.findById(subscriptionCreateDto.getChildId()).orElseThrow();
        if (!child.getParent().getPhoneNumber().equals(phoneNumber)) {
            throw new ParentNotExistsException(phoneNumber);
        }

        final SubscriptionPlan subscriptionPlan =
                subscriptionPlanRepository.findById(subscriptionCreateDto.getSubscriptionPlanId()).orElseThrow();

        final Subscription subscription = new Subscription();
        subscription.setChild(child);
        subscription.setPlan(subscriptionPlan);
        subscription.setMealLeft(subscriptionPlan.getMealFrequency());
        subscription.setStartDate(subscriptionCreateDto.getStartDate());
        subscription.setEndDate(subscriptionCreateDto.getEndDate());

        child.getSubscriptions().add(subscription);

        childRepository.save(child);
    }

}
