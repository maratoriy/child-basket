package ru.childmarket.childmarket.domain.review;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.childmarket.childmarket.domain.review.dto.ReviewCreateDto;

@RestController
@RequestMapping(value = {"/api/parent/reviews"},
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    public ResponseEntity<Void> addReview(@AuthenticationPrincipal UserDetails userDetails,
                                          @RequestBody ReviewCreateDto reviewCreateDto) {
        reviewService.addReview(userDetails.getUsername(), reviewCreateDto);

        return ResponseEntity.ok().build();
    }
}
