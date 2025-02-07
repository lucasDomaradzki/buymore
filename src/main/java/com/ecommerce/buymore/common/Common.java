package com.ecommerce.buymore.common;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class Common {

    public static <T> Optional<T> get(T value) {
        return value == null || (value instanceof String && StringUtils.isBlank((String) value))
                ? Optional.empty()
                : Optional.of(value);
    }

}
