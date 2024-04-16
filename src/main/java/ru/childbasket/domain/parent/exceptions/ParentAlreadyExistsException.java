package ru.childbasket.domain.parent.exceptions;

import jakarta.persistence.PersistenceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
@Getter
public class ParentAlreadyExistsException extends PersistenceException {
    private final String phoneNumber;

    public ParentAlreadyExistsException(String phoneNumber) {
        super(String.format("Parent with phone number %s already exists", phoneNumber));
        this.phoneNumber = phoneNumber;
    }
}
