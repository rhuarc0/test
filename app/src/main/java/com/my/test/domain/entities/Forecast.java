package com.my.test.domain.entities;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Forecast {
    private City city;
    private List<ForecastUnit> forecastUnitList;

    private Forecast() {}

    public static Forecast createEmptyObject() {
        City city = City.builder().setCountry("").setName("").build();
        return builder().setCity(city).setForecastUnitList(new ArrayList<>()).build();
    }

    @NonNull
    public static Builder builder() {
        return new Forecast().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setCity(City city) {
            Forecast.this.city = city;
            return this;
        }

        public Builder setForecastUnitList(List<ForecastUnit> forecastUnitList) {
            Forecast.this.forecastUnitList = forecastUnitList;
            return this;
        }

        public Forecast build() {
            return Forecast.this;
        }
    }

    public City getCity() {
        return city;
    }

    public List<ForecastUnit> getForecastUnitList() {
        return forecastUnitList;
    }

}
