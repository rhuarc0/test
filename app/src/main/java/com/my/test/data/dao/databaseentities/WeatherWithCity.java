package com.my.test.data.dao.databaseentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

public class WeatherWithCity {

    @Embedded
    private CurrentWeatherDb weatherDb;

    @ColumnInfo(name = "name")
    private String cityName;

    @ColumnInfo(name = "country")
    private String country;

    public CurrentWeatherDb getWeatherDb() {
        return weatherDb;
    }

    public void setWeatherDb(CurrentWeatherDb weatherDb) {
        this.weatherDb = weatherDb;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
