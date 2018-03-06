package com.my.test.data.dao;

import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;

import java.util.List;

public interface Cache {

    List<CurrentWeather> getAllCurrentWeathers();
    CurrentWeather getCurrentWeather(City city);
    void putCurrentWeather(CurrentWeather currentWeather);
    void putCurrentWeathers(List<CurrentWeather> currentWeatherList);
    void deleteCurrentWeather(City city);

    List<Forecast> getAllForecasts();
    Forecast getForecast(City city);
    void putForecast(Forecast forecast);
    void deleteForecast(City city);
}
