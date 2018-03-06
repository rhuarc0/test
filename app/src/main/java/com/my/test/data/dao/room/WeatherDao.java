package com.my.test.data.dao.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.my.test.data.dao.databaseentities.CityDb;
import com.my.test.data.dao.databaseentities.CurrentWeatherDb;
import com.my.test.data.dao.databaseentities.WeatherWithCity;

import java.util.List;

@Dao
public abstract class WeatherDao {

    // public methods

    @Transaction
    public void putCurrentWeather(WeatherWithCity weatherWithCity) {
        deleteWeatherByCityId(weatherWithCity.getWeatherDb().getCityId());

        CityDb cityDb = new CityDb();
        cityDb.setName(weatherWithCity.getCityName());
        cityDb.setCountry(weatherWithCity.getCountry());
        cityDb.setId(weatherWithCity.getWeatherDb().getCityId());

        addCity(cityDb);
        addWeather(weatherWithCity.getWeatherDb());
    }

    @Transaction
    public void putCurrentWeathers(List<WeatherWithCity> weatherWithCityList) {
        for (WeatherWithCity weatherWithCity: weatherWithCityList) {
            deleteWeatherByCityId(weatherWithCity.getWeatherDb().getCityId());

            CityDb cityDb = new CityDb();
            cityDb.setName(weatherWithCity.getCityName());
            cityDb.setCountry(weatherWithCity.getCountry());
            cityDb.setId(weatherWithCity.getWeatherDb().getCityId());

            addCity(cityDb);
            addWeather(weatherWithCity.getWeatherDb());
        }
    }

    @Query("SELECT currentweatherdb.*, name, country FROM currentweatherdb JOIN citydb ON currentweatherdb.cityId = citydb.id WHERE citydb.id = :cityId")
    public abstract WeatherWithCity getCurrentWeather(long cityId);

    @Query("SELECT currentweatherdb.*, name, country FROM citydb LEFT OUTER JOIN currentweatherdb ON currentweatherdb.cityId = citydb.id")
    public abstract List<WeatherWithCity> getWeatherForAllCities();

    @Query("DELETE FROM currentweatherdb WHERE cityId = :cityId")
    public abstract void deleteWeatherByCityId(long cityId);

    // auxiliary

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void addWeather(CurrentWeatherDb weatherDb);

    /* Можно было бы поставить OnConflictStrategy.IGNORE,
    * но в силу косяков на стороне сервера при поиске города выдаётся одно название,
    * которое лучше потом переписать нормальным
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void addCity(CityDb cityDb);
}
