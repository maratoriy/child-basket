package ru.childbasket.domain.review.dto;

import lombok.Data;

@Data
public class ReviewCreateDto {
    private Integer rating;

    private String comments;
}
