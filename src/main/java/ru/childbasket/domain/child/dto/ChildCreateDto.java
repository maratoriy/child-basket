package ru.childbasket.domain.child.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ChildCreateDto {
    @NotEmpty
    private final String name;

    private final Timestamp birthdayDate;
}