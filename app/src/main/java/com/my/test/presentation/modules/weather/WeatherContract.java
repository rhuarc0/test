package com.my.test.presentation.modules.weather;

import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;
import com.my.test.domain.entities.ForecastUnit;
import com.my.test.presentation.modules.base.BaseContract;

import java.util.List;

public interface WeatherContract {

    interface View extends BaseContract.View {

        void setWeatherPending(boolean state);
        void setForecastPending(boolean state);

        void showCityList(List<City> cityList);
        void setCurrentCityMenuPosition(int position);

        void showCurrentWeather(CurrentWeather currentWeather);
        void showForecast(List<ForecastUnit> forecastUnits);

        void showEmptyState();

        void showIfWeatherIsObsolete(boolean isObsolete);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void selectCity(int position);
        void refresh();
    }

}
