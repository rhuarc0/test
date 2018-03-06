package com.my.test.domain.interactors;

import android.support.annotation.NonNull;

import com.my.test.domain.entities.City;
import com.my.test.domain.repository.CityRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class CitiesInteractor extends BaseInteractor {
    private CityRepository cityRepository;

    public CitiesInteractor(@NonNull Scheduler subscriberScheduler,
                            @NonNull Scheduler observerScheduler,
                            CityRepository cityRepository) {
        super(subscriberScheduler, observerScheduler);
        this.cityRepository = cityRepository;
    }

    public Observable<List<City>> findCities(String query) {
        return cityRepository.findCities(query).compose(this.applySchedulers());
    }

    public Completable addCity(City city) {
        return cityRepository.addCity(city).compose(this.applyCompletableSchedulers());
    }

    public Completable deleteCity(City city) {
        return cityRepository.deleteCity(city).compose(this.applyCompletableSchedulers());
    }
}
