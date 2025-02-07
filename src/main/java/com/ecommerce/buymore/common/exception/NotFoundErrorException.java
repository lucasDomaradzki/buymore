package com.ecommerce.buymore.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundErrorException extends BuyMoreException {

    public NotFoundErrorException(String message) {
        super(message);
    }

    public NotFoundErrorException(String message, Object... args) {
        super(message, args);
    }

}
