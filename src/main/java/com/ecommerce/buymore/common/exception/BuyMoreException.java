package com.ecommerce.buymore.common.exception;

import java.text.MessageFormat;

public class BuyMoreException extends Exception {

    public BuyMoreException(final String message) {
        super(message);
    }

    public BuyMoreException(final String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

}
