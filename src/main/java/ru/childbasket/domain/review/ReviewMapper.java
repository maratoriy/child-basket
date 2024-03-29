package ru.childbasket.domain.review;

import org.mapstruct.Mapper;
import ru.childbasket.domain.review.dto.ReviewCreateDto;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewCreateDto map(Review review);
}
