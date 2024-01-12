package ru.childmarket.childmarket.domain.parent.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ParentLoginDto {
    @NotEmpty
    private final String phoneNumber;

    @NotEmpty
    private final String password;
}
