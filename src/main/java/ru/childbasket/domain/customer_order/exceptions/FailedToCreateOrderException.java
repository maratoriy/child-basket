package ru.childbasket.domain.customer_order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FailedToCreateOrderException extends RuntimeException {
    public FailedToCreateOrderException() {
        super("Failed to create order");
    }
}
