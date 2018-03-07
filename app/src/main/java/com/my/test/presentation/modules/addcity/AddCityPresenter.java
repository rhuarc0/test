package com.my.test.presentation.modules.addcity;

import com.my.test.R;
import com.my.test.domain.entities.City;
import com.my.test.domain.interactors.CitiesInteractor;
import com.my.test.domain.interactors.DefaultObserver;
import com.my.test.presentation.modules.base.BasePresenter;

import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class AddCityPresenter extends BasePresenter<AddCityContract.View> implements AddCityContract.Presenter {

    private List<City> cityList = new ArrayList<>();

    @Inject
    CitiesInteractor citiesInteractor;

    @Inject
    public AddCityPresenter() {}

    @Override
    public void onViewAttached() { }

    @Override
    public void findCities(String query) {
        Disposable disposable = citiesInteractor.findCities(query)
                .subscribeWith(createObserver());
        subscriptions.add(disposable);
    }

    private DefaultObserver<List<City>> createObserver() {
        return new DefaultObserver<List<City>>() {
            @Override
            public void onNext(List<City> cities) {
                super.onNext(cities);
                cityList.clear();
                cityList.addAll(cities);
                if (cities.isEmpty())
                    view.showEmptyState();
                else
                    view.showCities(cities);
            }

            @Override
            protected void onHttpError(Throwable throwable) {
                super.onHttpError(throwable);
                cityList.clear();
                view.showEmptyState();
            }

            @Override
            protected void onUnknownError(Throwable throwable) {
                super.onUnknownError(throwable);
                cityList.clear();
                view.showMessage(R.string.unknown_error);
            }
        };
    }

    @Override
    public void onCityClicked(int position) {
        citiesInteractor.addCity(cityList.get(position))
                .subscribe(
                        () -> view.finish(),
                        throwable -> view.showMessage(throwable.getMessage()));
    }

}
