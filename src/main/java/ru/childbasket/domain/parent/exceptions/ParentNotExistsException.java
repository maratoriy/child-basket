package ru.childbasket.domain.parent.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
@Getter
public class ParentNotExistsException extends EntityNotFoundException {
    private final String phoneNumber;
    public ParentNotExistsException(String phoneNumber) {
        super(String.format("Parent with phone number %s not exists", phoneNumber));
        this.phoneNumber = phoneNumber;
    }
}
