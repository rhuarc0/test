package com.my.test.data.dao.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.annimon.stream.Stream;
import com.my.test.data.dao.databaseentities.CityDb;
import com.my.test.data.dao.databaseentities.ForecastDb;
import com.my.test.data.dao.databaseentities.ForecastUnitDb;

import java.util.List;

@Dao
public abstract class ForecastDao  {

    @Transaction
    public void putForecast(ForecastDb forecastDb) {
        CityDb cityDb = forecastDb.getCity();
        addCity(cityDb);

        Stream.of(forecastDb.getForecastUnitList())
              .forEach(unit -> unit.setCityId(cityDb.getId()));

        deleteForecastByCityId(cityDb.getId());
        addUnits(forecastDb.getForecastUnitList());
    }

    /* Можно было бы поставить OnConflictStrategy.IGNORE,
    * но в силу косяков на стороне сервера при поиске города выдаётся одно название,
    * которое лучше потом переписать нормальным
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void addCity(CityDb item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void addUnits(List<ForecastUnitDb> units);

    @Query("DELETE FROM forecastunitdb WHERE cityId = :cityId")
    public abstract void deleteForecastByCityId(long cityId);

    @Transaction
    @Query("SELECT * FROM citydb")
    public abstract List<ForecastDb> getForecastForAllCities();

    @Transaction
    @Query("SELECT * FROM citydb WHERE id = :cityId")
    public abstract ForecastDb getForecastByCityId(long cityId);
}
