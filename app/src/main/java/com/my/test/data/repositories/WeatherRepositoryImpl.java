package com.my.test.data.repositories;

import com.my.test.data.datasource.WeatherDataSource;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;
import com.my.test.domain.repository.WeatherRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Репозиторий разруливает процесс получения получения данных в соответствии с выбранной стратегией.
 * В данном случае сперва кэш (быстро), затем догрузка актуальных данных с сети.
 */
public class WeatherRepositoryImpl implements WeatherRepository {

    private WeatherDataSource localDataSource;
    private WeatherDataSource remoteDataSource;

    public WeatherRepositoryImpl(WeatherDataSource localDataSource, WeatherDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<List<CurrentWeather>> fetchCurrentWeatherForCities(List<City> cities) {
        Observable<List<CurrentWeather>> localData = localDataSource.fetchCurrentWeatherForCities(cities);
        Observable<List<CurrentWeather>> remoteData = remoteDataSource.fetchCurrentWeatherForCities(cities);
        return Observable.concat(localData, remoteData);
    }

    @Override
    public Observable<CurrentWeather> fetchCurrentWeather(City city) {
        Observable<CurrentWeather> localData = localDataSource.fetchCurrentWeatherData(city);
        Observable<CurrentWeather> remoteData = remoteDataSource.fetchCurrentWeatherData(city);
        return Observable.concat(localData, remoteData);
    }

    @Override
    public Observable<Forecast> fetchForecast(City city) {
        Observable<Forecast> localData = localDataSource.fetchForecast(city);
        Observable<Forecast> remoteData = remoteDataSource.fetchForecast(city);
        return Observable.concat(localData, remoteData);
    }
}
