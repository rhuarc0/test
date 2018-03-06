package com.my.test.domain.repository;

import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;

import java.util.List;

import io.reactivex.Observable;

public interface WeatherRepository {
    Observable<List<CurrentWeather>> fetchCurrentWeatherForCities(List<City> cities);
    Observable<CurrentWeather> fetchCurrentWeather(City city);
    Observable<Forecast> fetchForecast(City city);
}
