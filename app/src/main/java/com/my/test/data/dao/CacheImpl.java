package com.my.test.data.dao;

import android.util.Log;

import com.annimon.stream.Stream;
import com.my.test.data.dao.databaseentities.CurrentWeatherDb;
import com.my.test.data.dao.databaseentities.ForecastDb;
import com.my.test.data.dao.databaseentities.WeatherWithCity;
import com.my.test.data.dao.room.AppDatabase;
import com.my.test.data.mapper.DbToCurrentWeatherMapper;
import com.my.test.data.mapper.DbToForecastMapper;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;

import java.util.List;

import javax.inject.Inject;

public class CacheImpl implements Cache {

    @Inject
    AppDatabase appDatabase;

    @Inject
    DbToCurrentWeatherMapper dbToCurrentWeatherMapper;

    @Inject
    DbToForecastMapper dbToForecastMapper;

    @Inject
    public CacheImpl() { }

    @Override
    public List<CurrentWeather> getAllCurrentWeathers() {
        List<WeatherWithCity> weathers = appDatabase.getWeatherDao().getWeatherForAllCities();
        Stream.of(weathers)
                .forEach(weatherWithCity -> {
                    if (weatherWithCity.getWeatherDb() == null)
                        weatherWithCity.setWeatherDb(new CurrentWeatherDb());
                });
        return dbToCurrentWeatherMapper.map(weathers);
    }

    @Override
    public CurrentWeather getCurrentWeather(City city) {
        WeatherWithCity weatherDb = appDatabase.getWeatherDao().getCurrentWeather(city.getId());
        if (weatherDb == null)
            return CurrentWeather.builder().build();

        return dbToCurrentWeatherMapper.map(weatherDb);
    }

    @Override
    public void putCurrentWeather(CurrentWeather currentWeather) {
        WeatherWithCity weatherWithCity = dbToCurrentWeatherMapper.reverseMap(currentWeather);
        appDatabase.getWeatherDao().putCurrentWeather(weatherWithCity);
    }

    @Override
    public void putCurrentWeathers(List<CurrentWeather> currentWeatherList) {
        List<WeatherWithCity> weatherWithCityList = dbToCurrentWeatherMapper.reverseMap(currentWeatherList);
        appDatabase.getWeatherDao().putCurrentWeathers(weatherWithCityList);
    }

    @Override
    public void deleteCurrentWeather(City city) {
        appDatabase.getWeatherDao().deleteWeatherByCityId(city.getId());
    }

    @Override
    public List<Forecast> getAllForecasts() {
        List<ForecastDb> forecast = appDatabase.getForecastDao().getForecastForAllCities();
        return dbToForecastMapper.map(forecast);
    }

    @Override
    public Forecast getForecast(City city) {
        ForecastDb forecastDb = appDatabase.getForecastDao().getForecastByCityId(city.getId());
        return dbToForecastMapper.map(forecastDb);
    }

    @Override
    public void putForecast(Forecast forecast) {
        ForecastDb forecastDb = dbToForecastMapper.reverseMap(forecast);
        appDatabase.getForecastDao().putForecast(forecastDb);
    }

    @Override
    public void deleteForecast(City city) {
        appDatabase.getForecastDao().deleteForecastByCityId(city.getId());
    }
}
