package ru.childmarket.childmarket.domain.customer_order;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.childmarket.childmarket.domain.customer_order.dto.CustomerOrderCreateDto;
import ru.childmarket.childmarket.domain.customer_order.dto.CustomerOrderResponseDto;

@RequestMapping(value = {"/api/orders"},
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    @GetMapping
    public ResponseEntity<List<CustomerOrderResponseDto>> getOrders(@AuthenticationPrincipal UserDetails userDetails,
                                                                    @RequestParam Long subscriptionId) {
        final List<CustomerOrderResponseDto> customerOrderResponseDtos =
            customerOrderService.getCustomerOrders(userDetails.getUsername(), subscriptionId);

        return ResponseEntity.ok().body(customerOrderResponseDtos);
    }


    @PostMapping("/place")
    public ResponseEntity<List<CustomerOrderResponseDto>> getOrders(@AuthenticationPrincipal UserDetails userDetails,
                                                                    @RequestBody CustomerOrderCreateDto customerOrderCreateDto) {
        customerOrderService.placeCustomerOrder(userDetails.getUsername(), customerOrderCreateDto);

        return ResponseEntity.ok().build();
    }
}
