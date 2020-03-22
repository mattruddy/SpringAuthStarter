package com.pwa.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ServiceException extends RuntimeException {

    final int code;

    public ServiceException(Error errorMessages) {
        super(errorMessages.getMessage());
        this.code = errorMessages.getCode();
    }

    public ServiceException(String message) {
        super(message);
        this.code = 400;
    }

    public ServiceException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.code = 400;
    }

}
