package com.weatherforecast.exception;

import com.weatherforecast.model.ErrorResponse;
import lombok.Getter;

/**
 * Created by shridhar on 15/01/19.
 */
@Getter
public class WeatherForecastException extends RuntimeException {

    private ErrorResponse errorResponse;

    public WeatherForecastException() {

    }
    public WeatherForecastException(ErrorResponse errorResponse) {
       this.errorResponse = errorResponse;
    }

    public WeatherForecastException(String message) {
        super(message);
    }

}
