package com.weatherforecast.service;

import com.weatherforecast.model.WeatherForecastDTO;

/**
 * Created by shridhar on 15/01/19.
 */

public interface WeatherForecastService
{
    WeatherForecastDTO getWeatherForecast(String cityName);
}
