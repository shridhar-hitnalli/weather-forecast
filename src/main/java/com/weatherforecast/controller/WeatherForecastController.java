package com.weatherforecast.controller;

import com.weatherforecast.model.WeatherForecastDTO;
import com.weatherforecast.service.WeatherForecastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shridhar on 15/01/19.
 */
@Validated
@RestController
@RequestMapping(value = "v1/weather", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "Weather forecast API", value = "Retrieve weather metrics of a specific city.")
public class WeatherForecastController {

    private static final String CITY_PARAM = "city";

    private final WeatherForecastService weatherForcastService;

    public WeatherForecastController(WeatherForecastService weatherForcastService) {
        this.weatherForcastService = weatherForcastService;
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful read"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "User Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Retrieve the next 3 days from today’s date for Day time (06:00 – 18:00) and Night time (18:00 – 06:00) also," +
                        "average of pressure for the next 3 days from today’s date.")
    @GetMapping(value = "/data/{city}")
    public ResponseEntity<WeatherForecastDTO> getWeatherForecast(
            @ApiParam(example = "London", value="City name")
            @Valid
            @Size(min=1,message="City name must be more than 1 char")
            @Pattern(regexp = "[a-zA-Z]*", message = "City name must not contain special characters or numeric values")
            @PathVariable(value= CITY_PARAM) String cityName) {

            return new ResponseEntity<>(weatherForcastService.getWeatherForecast(cityName), HttpStatus.OK);

    }



}
