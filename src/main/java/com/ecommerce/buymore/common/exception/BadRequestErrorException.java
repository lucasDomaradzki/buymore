package com.ecommerce.buymore.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestErrorException extends BuyMoreException {

    public BadRequestErrorException(String message) {
        super(message);
    }

    public BadRequestErrorException(String message, Object... args) {
        super(message, args);
    }

}
