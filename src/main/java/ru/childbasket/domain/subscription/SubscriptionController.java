package ru.childbasket.domain.subscription;

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
import org.springframework.web.bind.annotation.RestController;
import ru.childbasket.domain.subscription.dto.SubscriptionCreateDto;
import ru.childbasket.domain.subscription.dto.SubscriptionResponseDto;

@RestController
@RequestMapping(value = {"/api/parent/subscriptions"},
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getSubscriptions(@AuthenticationPrincipal UserDetails userDetails,
                                                                          @RequestParam Long childId) {
        final List<SubscriptionResponseDto> subscriptionResponseDtos =
                subscriptionService.getSubscriptions(userDetails.getUsername(), childId);

        return ResponseEntity.ok().body(subscriptionResponseDtos);
    }

    @PostMapping
    public ResponseEntity<Void> subscribe(@AuthenticationPrincipal UserDetails userDetails,
                                                                   @RequestBody SubscriptionCreateDto subscriptionCreateDto) {
        subscriptionService.subscribe(userDetails.getUsername(), subscriptionCreateDto);

        return ResponseEntity.ok().build();
    }
}
