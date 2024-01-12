package ru.childmarket.childmarket.domain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.childmarket.childmarket.domain.parent.Parent;
import ru.childmarket.childmarket.domain.parent.ParentRepository;
import ru.childmarket.childmarket.domain.parent.exceptions.ParentNotExistsException;
import ru.childmarket.childmarket.domain.review.dto.ReviewCreateDto;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ParentRepository parentRepository;

    public void addReview(String phoneNumber, ReviewCreateDto reviewCreateDto) {
        final Parent parent = parentRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ParentNotExistsException(phoneNumber));
        final Review review = new Review();
        review.setParent(parent);
        review.setComments(reviewCreateDto.getComments());
        review.setRating(reviewCreateDto.getRating());

        parent.getReviews().add(review);

        parentRepository.save(parent);
    }
}
