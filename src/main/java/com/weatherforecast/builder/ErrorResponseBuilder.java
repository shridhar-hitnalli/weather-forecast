package com.weatherforecast.builder;

import com.weatherforecast.model.ErrorResponse;
import org.springframework.http.HttpStatus;

/**
 * Created by shridhar on 15/01/19.
 */
public final class ErrorResponseBuilder {
    private ErrorResponseBuilder() {

    }

    public static ErrorResponse unauthorized(String message) {
        return ErrorResponse.builder()
                .httpStatusCode(HttpStatus.UNAUTHORIZED.value())
                .errorMessage(message)
                .build();
    }

    public static ErrorResponse notFound(String message) {
        return ErrorResponse.builder()
            .httpStatusCode(HttpStatus.NOT_FOUND.value())
            .errorMessage(message)
            .build();
    }

    public static ErrorResponse internalServerError(String message) {
        return ErrorResponse.builder()
            .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .errorMessage(message)
            .build();
    }
}

