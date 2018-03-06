package com.my.test.data.datasource;

import com.my.test.domain.entities.City;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface CityDataSource {
    Observable<List<City>> fetchCities();
    Observable<List<City>> findCity(String query);
    Completable addCity(City city);
    Completable deleteCity(City city);
}
