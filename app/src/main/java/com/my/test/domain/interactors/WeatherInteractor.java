package com.my.test.domain.interactors;

import android.support.annotation.NonNull;

import com.my.test.data.datasource.DefaultsDataSource;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;
import com.my.test.domain.repository.CityRepository;
import com.my.test.domain.repository.WeatherRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class WeatherInteractor extends BaseInteractor {

    private DefaultsDataSource defaultsDataSource;
    private WeatherRepository weatherRepository;
    private CityRepository cityRepository;

    public WeatherInteractor(@NonNull Scheduler subscriberScheduler,
                             @NonNull Scheduler observerScheduler,
                             WeatherRepository weatherRepository,
                             CityRepository cityRepository,
                             DefaultsDataSource defaultsDataSource) {
        super(subscriberScheduler, observerScheduler);
        this.weatherRepository = weatherRepository;
        this.cityRepository = cityRepository;
        this.defaultsDataSource = defaultsDataSource;
    }

    public Observable<List<CurrentWeather>> fetchCurrentWeatherForAllCities() {
        return cityRepository.fetchCities()
                .flatMap(cities -> weatherRepository.fetchCurrentWeatherForCities(cities))
                .compose(this.applySchedulers());
    }

    public Observable<CurrentWeather> fetchCurrentWeather(City city) {
        return weatherRepository.fetchCurrentWeather(city).compose(this.applySchedulers());
    }

    public Observable<Forecast> fetchForecast(City city) {
        return weatherRepository.fetchForecast(city).compose(this.applySchedulers());
    }

    public Observable<List<City>> fetchCities() {
        return cityRepository.fetchCities()
                .subscribeOn(this.observerScheduler)
                .observeOn(this.observerScheduler);
    }

    public void setDefaultCityPosition(int position) {
        defaultsDataSource.setCurrentCityPosition(position);
    }

    public int getDefaultCityPosition() {
        return defaultsDataSource.getCurrentCityPosition();
    }
}
