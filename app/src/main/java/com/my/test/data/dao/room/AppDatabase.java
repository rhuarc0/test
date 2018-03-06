package com.my.test.data.dao.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.my.test.data.dao.databaseentities.CityDb;
import com.my.test.data.dao.databaseentities.CurrentWeatherDb;
import com.my.test.data.dao.databaseentities.ForecastDb;
import com.my.test.data.dao.databaseentities.ForecastUnitDb;

@Database(entities = {CityDb.class, ForecastUnitDb.class, CurrentWeatherDb.class},
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ForecastDao getForecastDao();
    public abstract WeatherDao getWeatherDao();
    public abstract CityDao getCityDao();


}
