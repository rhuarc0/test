package com.my.test.data.datasource;

import android.text.TextUtils;
import android.util.Log;

import com.annimon.stream.Stream;
import com.my.test.data.dao.Cache;
import com.my.test.data.mapper.DtoToCurrentWeatherMapper;
import com.my.test.data.mapper.DtoToForecastMapper;
import com.my.test.data.rest.WebService;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

/**
 * Возвращает данные из сети, параллельно кэшируя их.
 */
public class RemoteDataSource implements WeatherDataSource {

    DtoToCurrentWeatherMapper dtoToCurrentWeatherMapper;
    DtoToForecastMapper dtoToForecastMapper;

    Cache cache;
    WebService webService;

    public RemoteDataSource(DtoToCurrentWeatherMapper dtoToCurrentWeatherMapper,
                            DtoToForecastMapper dtoToForecastMapper,
                            Cache cache,
                            WebService webService) {
        this.dtoToCurrentWeatherMapper = dtoToCurrentWeatherMapper;
        this.dtoToForecastMapper = dtoToForecastMapper;
        this.cache = cache;
        this.webService = webService;
    }

    @Override
    public Observable<List<CurrentWeather>> fetchCurrentWeatherForCities(List<City> cities) {
        List<String> ids = Stream.of(cities).map(City::getId).map(String::valueOf).toList();
        String param = TextUtils.join(",", ids);
        return webService.fetchCurrentWeatherForCities(param)
                .map(dto -> dtoToCurrentWeatherMapper.map(dto.getList()))
                .doOnNext(currentWeathers -> cache.putCurrentWeathers(currentWeathers))
                .onErrorResumeNext(throwable -> {
                    return Observable.empty();
                });
    }

    @Override
    public Observable<CurrentWeather> fetchCurrentWeatherData(City city) {
        return webService.fetchCurrentWeather(city.getId())
                .map(dto -> dtoToCurrentWeatherMapper.map(dto))
                .doOnNext(currentWeather -> cache.putCurrentWeather(currentWeather))
                .onErrorResumeNext(throwable -> {
                    return Observable.empty();
                });
    }

    @Override
    public Observable<Forecast> fetchForecast(City city) {
        return webService.fetchForecast(city.getId())
                .map(dto -> dtoToForecastMapper.map(dto))
                .doOnNext(forecast -> cache.putForecast(forecast))
                .onErrorResumeNext(throwable -> {
                    return Observable.empty();
                });
    }

}
