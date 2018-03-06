package com.my.test.data.datasource;

import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;

import java.util.List;

import io.reactivex.Observable;

public interface WeatherDataSource {
    Observable<List<CurrentWeather>> fetchCurrentWeatherForCities(List<City> cities);
    Observable<CurrentWeather> fetchCurrentWeatherData(City city);
    Observable<Forecast> fetchForecast(City city);
}
