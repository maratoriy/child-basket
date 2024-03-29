package ru.childbasket.domain.parent.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedGeneratePasswordException extends RuntimeException {
    public FailedGeneratePasswordException() {
        super("Failed to generate password");
    }
}
