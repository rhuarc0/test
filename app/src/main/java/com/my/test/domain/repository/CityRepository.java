package com.my.test.domain.repository;

import com.my.test.domain.entities.City;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface CityRepository {
    Observable<List<City>> fetchCities();
    Observable<List<City>> findCities(String query);
    Completable addCity(City city);
    Completable deleteCity(City city);
}
