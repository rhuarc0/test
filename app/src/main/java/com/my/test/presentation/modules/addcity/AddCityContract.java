package com.my.test.presentation.modules.addcity;

import com.my.test.domain.entities.City;
import com.my.test.presentation.modules.base.BaseContract;

import java.util.List;

public interface AddCityContract {

    interface View extends BaseContract.View {

        void showCities(List<City> cities);
        void showEmptyState();
        void finish();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void findCities(String query);
        void onCityClicked(int position);
    }

}
