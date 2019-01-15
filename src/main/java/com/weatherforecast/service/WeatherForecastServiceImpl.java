package com.weatherforecast.service;

import com.weatherforecast.exception.WeatherForecastException;
import com.weatherforecast.model.WeatherForecast;
import com.weatherforecast.model.WeatherForecastDTO;
import com.weatherforecast.util.DailyForecast;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.openweathermap.api.WeatherClientRequestException;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.weatherforecast.builder.ErrorResponseBuilder.internalServerError;
import static com.weatherforecast.builder.ErrorResponseBuilder.notFound;
import static com.weatherforecast.builder.ErrorResponseBuilder.unauthorized;

/**
 * Created by shridhar on 15/01/19.
 */
@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    @Value("${api_key}")
    private String apiKey;

    private DailyForecast dailyForecast;

    public WeatherForecastServiceImpl(DailyForecast dailyForecast) {
       this.dailyForecast = dailyForecast;
    }

    @Override
    public WeatherForecastDTO getWeatherForecast(String cityName) {

        ForecastInformation<HourlyForecast> forecastInformation = null;
        try {
            forecastInformation = dailyForecast.getForecast(cityName, apiKey);
        } catch (WeatherClientRequestException ex) {
            if (ex.getCause() != null ) {
                if (ex.getCause() instanceof FileNotFoundException) {
                    throw new WeatherForecastException(notFound("No data found for the city"));
                } else if (ex.getCause() instanceof IOException) {
                    throw new WeatherForecastException(unauthorized("Unauthorized"));
                }
            }
        } catch (Exception e) {
            throw new WeatherForecastException(internalServerError("Something went wrong while invoking API"));
        }

        if (forecastInformation == null) {
            return WeatherForecastDTO.builder()
                .weatherForecast(null)
                .errorResponse(notFound("No data found"))
                .build();
        }

        List<HourlyForecast> forecasts = forecastInformation.getForecasts();

        return WeatherForecastDTO.builder()
            .weatherForecast(WeatherForecast.builder()
                .city(forecastInformation.getCity().getName())
                .averageDayTemperature(dailyForecast.getAvgDailyTemperature(forecasts))
                .averageNightTemperature(dailyForecast.getAvgNightlyTemperature(forecasts))
                .averagePressure(dailyForecast.getAvgPressure(forecasts))
                .build())
            .errorResponse(null)
            .build();


    }

}
