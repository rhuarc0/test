package com.my.test.presentation.modules.cities;

import com.my.test.domain.entities.CurrentWeather;
import com.my.test.presentation.modules.base.BaseContract;

import java.util.List;

public interface CitiesContract {
    interface View extends BaseContract.View {
        void showCities(List<CurrentWeather> citiesWithWeather);

        void showEmptyState();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void refresh();

        void onCityClicked(int position);
        void onCityDeleteClicked(int position);
    }
}
