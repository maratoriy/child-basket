package ru.childmarket.childmarket.domain.child.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.childmarket.childmarket.domain.parent.Parent;

@Data
public class ChildCreateDto {

    @NotEmpty
    private final String name;

    private final Timestamp birthdayDate;
}