package ru.childmarket.childmarket.domain.review;

import org.mapstruct.Mapper;
import ru.childmarket.childmarket.domain.review.dto.ReviewCreateDto;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewCreateDto map(Review review);
}
