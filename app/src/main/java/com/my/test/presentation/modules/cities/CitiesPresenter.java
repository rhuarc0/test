package com.my.test.presentation.modules.cities;

import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.interactors.CitiesInteractor;
import com.my.test.domain.interactors.DefaultObserver;
import com.my.test.domain.interactors.WeatherInteractor;
import com.my.test.presentation.modules.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class CitiesPresenter extends BasePresenter<CitiesContract.View> implements CitiesContract.Presenter {

    private List<CurrentWeather> cityWithWeatherList = new ArrayList<>();

    @Inject
    WeatherInteractor weatherInteractor;

    @Inject
    CitiesInteractor citiesInteractor;

    @Inject
    public CitiesPresenter() {}


    @Override
    public void onViewAttached() { }

    @Override
    public void refresh() {
        Disposable disposable = weatherInteractor.fetchCurrentWeatherForAllCities()
                .subscribeWith(createObserver());

        subscriptions.add(disposable);
    }

    private DefaultObserver<List<CurrentWeather>> createObserver() {
        return new DefaultObserver<List<CurrentWeather>>() {
            @Override
            public void onNext(List<CurrentWeather> currentWeathers) {
                super.onNext(cityWithWeatherList);
                cityWithWeatherList.clear();
                cityWithWeatherList.addAll(currentWeathers);
                view.showCities(currentWeathers);
            }

            @Override
            protected void onHttpError(Throwable throwable) {
                super.onHttpError(throwable);
                cityWithWeatherList.clear();
                view.showEmptyState();
            }

            @Override
            protected void onUnknownError(Throwable throwable) {
                super.onUnknownError(throwable);
                cityWithWeatherList.clear();
                view.showMessage(throwable.getMessage());
            }
        };
    }

    @Override
    public void onCityClicked(int position) {
        weatherInteractor.setDefaultCityPosition(position);
    }

    @Override
    public void onCityDeleteClicked(int position) {
        citiesInteractor.deleteCity(cityWithWeatherList.get(position).getCity())
                .subscribe(() -> {

                    cityWithWeatherList.remove(position);
                    if (cityWithWeatherList.size() > 0)
                        weatherInteractor.setDefaultCityPosition(0);
                    else
                        weatherInteractor.setDefaultCityPosition(-1);

                    view.showCities(cityWithWeatherList);

                });
    }
}
