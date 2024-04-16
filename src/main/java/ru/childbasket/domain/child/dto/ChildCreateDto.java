package ru.childbasket.domain.child.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChildCreateDto {
    @NotEmpty
    private final String name;

    private final Timestamp birthdayDate;
}