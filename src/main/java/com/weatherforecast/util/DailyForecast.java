package com.weatherforecast.util;

import java.util.List;
import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.UrlConnectionDataWeatherClient;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;
import org.openweathermap.api.query.Language;
import org.openweathermap.api.query.QueryBuilderPicker;
import org.openweathermap.api.query.UnitFormat;
import org.openweathermap.api.query.forecast.hourly.ByCityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by shridhar on 15/01/19.
 */
@Component
public class DailyForecast {

    @Autowired
    DateUtil dateUtil;

    public ForecastInformation<HourlyForecast> getForecast(String city, String apiKey) throws Exception {
        DataWeatherClient client = new UrlConnectionDataWeatherClient(apiKey);
        ByCityName byCityNameForecast = QueryBuilderPicker.pick()
            .forecast()
            .hourly()                                           // it should be hourly forecast
            .byCityName(city)                                     // for Kharkiv city             // in Ukraine
            .unitFormat(UnitFormat.METRIC)                      // in Metric units
            .language(Language.ENGLISH)                         // in English
            .build();

        return client.getForecastInformation(byCityNameForecast);
    }

    public double getAvgDailyTemperature(List<HourlyForecast> weatherDetails) {
        return formatValue(
            weatherDetails.stream()
                .filter(wd -> dateUtil.isDateInRange(wd.getDateTime().toInstant()))
                .filter(wd -> dateUtil.isDayTimeForecast(wd.getDateTime().toInstant()))
                .mapToDouble(wd -> wd.getMainParameters().getTemperature()).average().orElse(0.0)
        );
    }

    public double getAvgNightlyTemperature(List<HourlyForecast> weatherDetails) {
        return formatValue(
            weatherDetails.stream()
                .filter(wd -> dateUtil.isDateInRange(wd.getDateTime().toInstant()))
                .filter(wd -> !dateUtil.isDayTimeForecast(wd.getDateTime().toInstant()))
                .mapToDouble(wd -> wd.getMainParameters().getTemperature()).average().orElse(0.0)
        );
    }

    public double getAvgPressure(List<HourlyForecast> weatherDetails) {
        return formatValue(
            weatherDetails.stream()
                .filter(wd -> dateUtil.isDateInRange(wd.getDateTime().toInstant()))
                .mapToDouble(wd -> wd.getMainParameters().getPressure())
                .average().orElse(0.0)
        );
    }

    private static double formatValue(double value) {
        return Math.round(value * 100) / 100D;
    }
}
