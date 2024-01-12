package ru.childmarket.childmarket.domain.parent.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ParentRegisterDto {
    @NotEmpty
    private final String surname;

    @NotEmpty
    private final String firstName;

    @NotEmpty
    private final String middleName;

    @NotEmpty
    private final String phoneNumber;

    @NotEmpty
    private final String password;
}
