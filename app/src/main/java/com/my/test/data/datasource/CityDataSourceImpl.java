package com.my.test.data.datasource;

import com.annimon.stream.Stream;
import com.my.test.data.dao.room.AppDatabase;
import com.my.test.data.mapper.DbToCityMapper;
import com.my.test.data.mapper.DtoToCurrentWeatherMapper;
import com.my.test.data.rest.WebService;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class CityDataSourceImpl implements CityDataSource {

    @Inject
    AppDatabase appDatabase;
    @Inject
    WebService webService;
    @Inject
    DbToCityMapper mapper;
    @Inject
    DtoToCurrentWeatherMapper dtoMapper;

    @Inject
    public CityDataSourceImpl() {}

    @Override
    public Observable<List<City>> fetchCities() {
        return Observable.fromCallable(() -> mapper.map(appDatabase.getCityDao().fetchItems()));
    }

    @Override
    public Observable<List<City>> findCity(String query) {
        return webService.findCity(query)
                .map(findCityDto -> dtoMapper.map(findCityDto.getList()))
                .map(currentWeathers -> Stream.of(currentWeathers)
                        .map(CurrentWeather::getCity)
                        .toList());
    }

    @Override
    public Completable addCity(City city) {
        return Completable.fromAction(() -> appDatabase.getCityDao().addItem(mapper.reverseMap(city)));
    }

    @Override
    public Completable deleteCity(City city) {
        return Completable.fromAction(() -> {
            appDatabase.getCityDao().delete(city.getId());
            appDatabase.getWeatherDao().deleteWeatherByCityId(city.getId());
            appDatabase.getForecastDao().deleteForecastByCityId(city.getId());
        });
    }
}
