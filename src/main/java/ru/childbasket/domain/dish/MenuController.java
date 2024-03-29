package ru.childbasket.domain.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.childbasket.domain.dish.dto.DishResponseDto;
import ru.childbasket.domain.dish_type.DishTypeService;
import ru.childbasket.domain.dish_type.dto.DishTypeResponseDto;
import ru.childbasket.domain.subscription.SubscriptionService;
import ru.childbasket.domain.subscription_plan.dto.SubscriptionPlanResponseDto;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/menu"},
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MenuController {
    private final DishService dishService;
    private final DishTypeService dishTypeService;
    private final SubscriptionService subscriptionService;

    @GetMapping("/dishes")
    public ResponseEntity<List<DishResponseDto>> getDishesByType(@RequestParam Long dishTypeId) {
        final List<DishResponseDto> dishResponseDtos = (dishTypeId == null)
                ? dishService.getDishes()
                : dishService.getDishesByDishesType(dishTypeId);

        return ResponseEntity.ok()
                .body(dishResponseDtos);
    }

    @GetMapping("/subscription-plans")
    public ResponseEntity<List<SubscriptionPlanResponseDto>> getSubscriptionPlans() {
        final List<SubscriptionPlanResponseDto> subscriptionPlanResponseDtos =
                subscriptionService.getSubscriptionPlans();

        return ResponseEntity.ok()
                .body(subscriptionPlanResponseDtos);
    }

    @GetMapping("/dish-types")
    public ResponseEntity<List<DishTypeResponseDto>> getDishTypes() {
        final List<DishTypeResponseDto> dishTypeResponseDtos = dishTypeService.getDishTypes();

        return ResponseEntity.ok()
                .body(dishTypeResponseDtos);
    }
}
