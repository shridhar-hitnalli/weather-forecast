package com.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shridhar on 15/01/19.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    @ApiModelProperty(required = true, notes = "One of the standard http status codes", example = "400")
    private int httpStatusCode;

    @ApiModelProperty(required = true, notes = "Textual description of the error code", example = "Bad request")
    private String errorMessage;

}
