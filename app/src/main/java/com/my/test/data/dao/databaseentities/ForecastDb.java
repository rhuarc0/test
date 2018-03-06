package com.my.test.data.dao.databaseentities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

public class ForecastDb {

    @Embedded
    private CityDb city;

    @Relation(parentColumn = "id",
            entityColumn = "cityId")
    private List<ForecastUnitDb> forecastUnitList;

    public CityDb getCity() {
        return city;
    }

    public void setCity(CityDb city) {
        this.city = city;
    }

    public List<ForecastUnitDb> getForecastUnitList() {
        return forecastUnitList;
    }

    public void setForecastUnitList(List<ForecastUnitDb> forecastUnitList) {
        this.forecastUnitList = forecastUnitList;
    }
}
