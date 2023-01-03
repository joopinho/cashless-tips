package com.jjj.cashlesstips.exception;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jjj.cashlesstips.dto.ApiError;

@ControllerAdvice
public class TipsExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiError> noSuchElementExceptionHandler(NoSuchElementException ex) {
        return new ResponseEntity<ApiError>(
            new ApiError(ex.getMessage(), 
                    OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS)), 
            HttpStatus.NOT_FOUND
        ); 
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
      return new ResponseEntity<ApiError>(
        new ApiError(ex.getFieldErrors().stream().map(f -> f.getDefaultMessage()).findFirst().get(), 
                    OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS)), 
        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> paymentProviderException(PaymentProviderException ex) {
      return new ResponseEntity<ApiError>(
        new ApiError(ex.getMessage(), 
                    OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS)), 
        HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> illegalArgumentException(IllegalArgumentException ex) {
      return new ResponseEntity<ApiError>(
        new ApiError(ex.getMessage(), 
                    OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS)), 
        HttpStatus.BAD_REQUEST);
    }
}
