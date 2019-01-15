package com.weatherforecast.controller;

import com.weatherforecast.model.WeatherForecast;
import com.weatherforecast.model.WeatherForecastDTO;
import com.weatherforecast.service.WeatherForecastService;
import javax.validation.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by shridhar on 15/01/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherForecastControllerTest {

    @InjectMocks
    private  WeatherForecastController controller;

    @Mock
    private WeatherForecastService weatherForcastService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(controller, "weatherForcastService", weatherForcastService);
    }

    @Test
    public void testPositiveResponse() throws Exception {

        WeatherForecastDTO expected = getWeatherData();
        when(weatherForcastService.getWeatherForecast(anyString())).thenReturn(expected);

        WeatherForecastDTO actual = controller.getWeatherForecast(anyString()).getBody();

        assertNotNull(actual);
        assertEquals(expected.getWeatherForecast().getAverageDayTemperature(), actual.getWeatherForecast().getAverageDayTemperature(), 0.001);
        assertEquals(expected.getWeatherForecast().getAverageNightTemperature(), actual.getWeatherForecast().getAverageNightTemperature(), 0.001);
        assertEquals(expected.getWeatherForecast().getAveragePressure(), actual.getWeatherForecast().getAveragePressure(), 0.001);

    }

    @Test
    public void testConstraintViolationException() throws Exception {
        when(controller.getWeatherForecast(eq("sds2"))).thenThrow(ConstraintViolationException.class);
    }

    private WeatherForecastDTO getWeatherData() {
        return WeatherForecastDTO.builder()
            .weatherForecast(WeatherForecast.builder()
                .city("Bangalore")
                .averageDayTemperature(28.3)
                .averageNightTemperature(18.2)
                .averagePressure(200.00)
                .build())
            .errorResponse(null)
            .build();
    }

}
