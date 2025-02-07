package com.ecommerce.buymore.common.config;

import com.ecommerce.buymore.common.exception.AlreadyReportedException;
import com.ecommerce.buymore.common.exception.BadRequestErrorException;
import com.ecommerce.buymore.common.exception.InternalServerErrorException;
import com.ecommerce.buymore.common.exception.NotFoundErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InternalServerErrorException.class})
    protected ResponseEntity<BuyMoreDefaultResponse> handleInternalServerException(InternalServerErrorException exception, WebRequest request) {
        return ResponseEntity.internalServerError().body(new BuyMoreDefaultResponse(INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = {AlreadyReportedException.class})
    protected ResponseEntity<BuyMoreDefaultResponse> handleAlreadyReportedException(AlreadyReportedException exception, WebRequest request) {
        return ResponseEntity.status(ALREADY_REPORTED).body(new BuyMoreDefaultResponse(ALREADY_REPORTED.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = {NotFoundErrorException.class})
    protected ResponseEntity<BuyMoreDefaultResponse> handleNotFoundException(NotFoundErrorException exception, WebRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(new BuyMoreDefaultResponse(NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = {BadRequestErrorException.class})
    protected ResponseEntity<BuyMoreDefaultResponse> handleBadRequestException(BadRequestErrorException exception, WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(new BuyMoreDefaultResponse(BAD_REQUEST.value(), exception.getMessage()));
    }

}
