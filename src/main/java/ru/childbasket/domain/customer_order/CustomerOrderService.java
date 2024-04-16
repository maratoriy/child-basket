package ru.childbasket.domain.customer_order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.childbasket.domain.address.AddressRepository;
import ru.childbasket.domain.customer_order.dto.CustomerOrderCreateDto;
import ru.childbasket.domain.customer_order.dto.CustomerOrderResponseDto;
import ru.childbasket.domain.customer_order.exceptions.FailedToCreateOrderException;
import ru.childbasket.domain.delivery_status.DeliveryStatusRepository;
import ru.childbasket.domain.dish.DishRepository;
import ru.childbasket.domain.parent.exceptions.ParentNotExistsException;
import ru.childbasket.domain.subscription.Subscription;
import ru.childbasket.domain.subscription.SubscriptionRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderService {
    private static final Long PENDING_STATUS_ID = 1L;
    private final CustomerOrderRepository orderRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final AddressRepository addressRepository;
    private final DishRepository dishRepository;
    private final CustomerOrderMapper orderMapper;

    @Transactional
    public List<CustomerOrderResponseDto> getCustomerOrders(String phoneNumber, Long subscriptionId) {
        final Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();
        if (!subscription.getChild().getParent().getPhoneNumber().equals(phoneNumber)) {
            throw new ParentNotExistsException(phoneNumber);
        }

        return subscription.getCustomerOrders().stream()
                .map(orderMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public void placeCustomerOrder(String phoneNumber, CustomerOrderCreateDto customerOrderCreateDto) {
        final Subscription subscription =
                subscriptionRepository.findById(customerOrderCreateDto.getSubscriptionId()).orElseThrow();

        if (!subscription.getChild().getParent().getPhoneNumber().equals(phoneNumber)) {
            throw new ParentNotExistsException(phoneNumber);
        }

        final Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        if (subscription.getEndDate().after(now)
                || subscription.getMealLeft() <= 0
                || customerOrderCreateDto.getDishesId().size() != subscription.getPlan().getMealPerOrder()
                || customerOrderCreateDto.getDeliveryDate().after(now)) {
            throw new FailedToCreateOrderException();
        }

        final CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setAddress(addressRepository.findById(customerOrderCreateDto.getAddressId()).orElseThrow());
        customerOrder.setDeliveryDate(customerOrderCreateDto.getDeliveryDate());
        customerOrder.setDishes(dishRepository.findAllById(customerOrderCreateDto.getDishesId()));
        customerOrder.setSubscription(subscription);
        customerOrder.setDeliveryStatus(deliveryStatusRepository.findById(PENDING_STATUS_ID).orElseThrow());

        orderRepository.save(customerOrder);

        subscription.setMealLeft(subscription.getMealLeft() - 1);

        if (subscription.getMealLeft() == 0) {
            subscription.setEndDate(now);
        }

        subscriptionRepository.save(subscription);
    }

}
