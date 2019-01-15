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
public class WeatherForecast {

    @ApiModelProperty(required = true, notes = "City name", example = "London")
    private String city;

    @ApiModelProperty(required = true, notes = "Average day temperature for 3 days", example = "20.5")
    private double averageDayTemperature;

    @ApiModelProperty(required = true, notes = "Average night temperature for 3 days", example = "10.2")
    private double averageNightTemperature;

    @ApiModelProperty(required = true, notes = "Average pressure for 3 days", example = "400.00")
    private double averagePressure;




}
