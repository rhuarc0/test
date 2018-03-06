package com.my.test.data.repositories;

import com.my.test.data.datasource.CityDataSource;
import com.my.test.domain.entities.City;
import com.my.test.domain.repository.CityRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;


public class CityRepositoryImpl implements CityRepository {

    private CityDataSource cityDataSource;

    public CityRepositoryImpl(CityDataSource cityDataSource) {
        this.cityDataSource = cityDataSource;
    }


    @Override
    public Observable<List<City>> fetchCities() {
        return cityDataSource.fetchCities();
    }

    @Override
    public Observable<List<City>> findCities(String query) {
        return cityDataSource.findCity(query);
    }

    @Override
    public Completable addCity(City city) {
        return cityDataSource.addCity(city);
    }

    @Override
    public Completable deleteCity(City city) {
        return cityDataSource.deleteCity(city);
    }
}
