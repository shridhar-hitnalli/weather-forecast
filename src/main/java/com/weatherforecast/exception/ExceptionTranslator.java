package com.weatherforecast.exception;

import com.weatherforecast.model.ErrorResponse;
import com.weatherforecast.model.WeatherForecastDTO;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by shridhar on 15/01/19.
 */
@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(WeatherForecastException.class)
    public ResponseEntity<?> handleWeatherForecastException (WeatherForecastException e) {
        int statusCode = e != null ? e.getErrorResponse().getHttpStatusCode() : 500;
        WeatherForecastDTO response = new WeatherForecastDTO();
        response.setErrorResponse(e != null ? e.getErrorResponse() : null);
        return new ResponseEntity<>(response, HttpStatus.valueOf(statusCode));

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException (ConstraintViolationException e) {
        WeatherForecastDTO response = new WeatherForecastDTO();
        ErrorResponse errorResponse = ErrorResponse.builder()
            .errorMessage(e.getMessage())
            .httpStatusCode(HttpStatus.BAD_REQUEST.value())
            .build();
        response.setErrorResponse(errorResponse);
        return new ResponseEntity<>(response, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));

    }
}
