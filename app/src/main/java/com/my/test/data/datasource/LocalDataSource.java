package com.my.test.data.datasource;

import com.my.test.data.dao.Cache;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;

import java.util.List;

import io.reactivex.Observable;

/**
 * Возвращает данные из кэша.
 */
public class LocalDataSource implements WeatherDataSource {

    private Cache cache;

    public LocalDataSource(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Observable<List<CurrentWeather>> fetchCurrentWeatherForCities(List<City> cities) {
        return Observable.fromCallable(() -> cache.getAllCurrentWeathers());
    }

    @Override
    public Observable<CurrentWeather> fetchCurrentWeatherData(City city) {
        return Observable.fromCallable(() -> cache.getCurrentWeather(city));
    }

    @Override
    public Observable<Forecast> fetchForecast(City city) {
        return Observable.fromCallable(() -> cache.getForecast(city));
    }
}
