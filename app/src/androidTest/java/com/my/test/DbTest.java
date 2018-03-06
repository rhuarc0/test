package com.my.test;


import android.app.Instrumentation;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;

import com.my.test.data.dao.databaseentities.CityDb;
import com.my.test.data.dao.databaseentities.CurrentWeatherDb;
import com.my.test.data.dao.databaseentities.ForecastDb;
import com.my.test.data.dao.databaseentities.ForecastUnitDb;
import com.my.test.data.dao.databaseentities.WeatherWithCity;
import com.my.test.data.dao.room.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DbTest {

    private final Integer cityId1 = 1234;
    private final Integer cityId2 = 12345;
    private final String cityName = "TestName";
    private final String cityCountry = "RU";
    private final String weatherMain = "CloudyTest";
    private final Integer temp = 30;
    private final Integer pressure = 740;
    private final Integer humidity = 50;
    private final Integer windSpeed = 2;
    private final Integer windDir = 250;
    private final Integer cloud = 80;
    private final Integer rain = 3;
    private final Integer snow = 4;
    private final Date date = Calendar.getInstance().getTime();

    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void addWeatherTest() throws Exception {
        List<WeatherWithCity> list;
        WeatherWithCity currentWeatherDb1 = createTestObject1();
        WeatherWithCity currentWeatherDb2 = createTestObject2();

        db.getWeatherDao().putCurrentWeather(currentWeatherDb1);

        list = db.getWeatherDao().getWeatherForAllCities();
        assertEquals(1, list.size());
        assertEquals(1, db.getCityDao().fetchItems().size());

        db.getWeatherDao().putCurrentWeather(currentWeatherDb1);

        list = db.getWeatherDao().getWeatherForAllCities();
        assertEquals(1, list.size());
        assertEquals(1, db.getCityDao().fetchItems().size());

        db.getWeatherDao().putCurrentWeather(currentWeatherDb2);

        list = db.getWeatherDao().getWeatherForAllCities();
        assertEquals(2, list.size());
        assertEquals(2, db.getCityDao().fetchItems().size());

        CurrentWeatherDb fromDb = list.get(0).getWeatherDb();
        assertEquals(cityId1, fromDb.getCityId());
        assertEquals(weatherMain, fromDb.getWeatherMain());
        assertEquals(temp, fromDb.getTemperature());
        assertEquals(pressure, fromDb.getPressure());
        assertEquals(humidity, fromDb.getHumidity());
        assertEquals(windSpeed, fromDb.getWindSpeed());
        assertEquals(windDir, fromDb.getWindDirectionInDegrees());
        assertEquals(cloud, fromDb.getCloudiness());
        assertEquals(rain, fromDb.getRainVolume());
        assertEquals(snow, fromDb.getSnowVolume());
        assertEquals(date, fromDb.getTimeOfDataCalculation());
    }

    @Test
    public void addForecastTest() throws Exception {
        List<ForecastDb> list;
        ForecastDb forecastDb = createForecastTestObject();

        db.getForecastDao().putForecast(forecastDb);

        list = db.getForecastDao().getForecastForAllCities();
        assertEquals(1, list.size());
        assertEquals(2, list.get(0).getForecastUnitList().size());

        db.getForecastDao().putForecast(forecastDb);

        list = db.getForecastDao().getForecastForAllCities();
        assertEquals(1, list.size());
        assertEquals(2, list.get(0).getForecastUnitList().size());
    }

    private WeatherWithCity createTestObject1() {

        WeatherWithCity result = new WeatherWithCity();

        CurrentWeatherDb currentWeatherDb = new CurrentWeatherDb();

        currentWeatherDb.setCityId(cityId1);
        currentWeatherDb.setWeatherMain(weatherMain);
        currentWeatherDb.setTemperature(temp);
        currentWeatherDb.setPressure(pressure);
        currentWeatherDb.setHumidity(humidity);
        currentWeatherDb.setWindSpeed(windSpeed);
        currentWeatherDb.setWindDirectionInDegrees(windDir);
        currentWeatherDb.setCloudiness(cloud);
        currentWeatherDb.setRainVolume(rain);
        currentWeatherDb.setSnowVolume(snow);
        currentWeatherDb.setTimeOfDataCalculation(date);

        result.setCityName(cityName);
        result.setCountry(cityCountry);
        result.setWeatherDb(currentWeatherDb);

        return result;
    }

    private WeatherWithCity createTestObject2() {

        WeatherWithCity result = new WeatherWithCity();

        CurrentWeatherDb currentWeatherDb = new CurrentWeatherDb();

        currentWeatherDb.setCityId(cityId2);
        currentWeatherDb.setWeatherMain(weatherMain);
        currentWeatherDb.setTemperature(temp);
        currentWeatherDb.setPressure(pressure);
        currentWeatherDb.setHumidity(humidity);
        currentWeatherDb.setWindSpeed(windSpeed);
        currentWeatherDb.setWindDirectionInDegrees(windDir);
        currentWeatherDb.setCloudiness(cloud);
        currentWeatherDb.setRainVolume(rain);
        currentWeatherDb.setSnowVolume(snow);
        currentWeatherDb.setTimeOfDataCalculation(date);

        result.setCityName(cityName);
        result.setCountry(cityCountry);
        result.setWeatherDb(currentWeatherDb);

        return result;
    }

    private ForecastDb createForecastTestObject() {
        ForecastDb forecastDb = new ForecastDb();

        CityDb cityDb = new CityDb();
        cityDb.setId(cityId1);
        cityDb.setName(cityName);
        cityDb.setCountry(cityCountry);
        forecastDb.setCity(cityDb);

        List<ForecastUnitDb> list = new ArrayList<>();
        ForecastUnitDb unitDb1 = new ForecastUnitDb();
        ForecastUnitDb unitDb2 = new ForecastUnitDb();

        unitDb1.setCityId(cityId1);
        unitDb1.setForecastTime(date);
        unitDb1.setWeatherMain(weatherMain);
        unitDb1.setTemperature(temp);
        unitDb1.setTempMin(temp);
        unitDb1.setTempMax(temp);
        unitDb1.setPressure(pressure);
        unitDb1.setHumidity(humidity);
        unitDb1.setWindSpeed(windSpeed);
        unitDb1.setWindDirectionInDegrees(windDir);
        unitDb1.setCloudiness(cloud);
        unitDb1.setRainVolume(rain);
        unitDb1.setSnowVolume(snow);

        unitDb2.setCityId(cityId1);
        unitDb2.setForecastTime(date);
        unitDb2.setWeatherMain(weatherMain);
        unitDb2.setTemperature(temp);
        unitDb2.setTempMin(temp);
        unitDb2.setTempMax(temp);
        unitDb2.setPressure(pressure);
        unitDb2.setHumidity(humidity);
        unitDb2.setWindSpeed(windSpeed);
        unitDb2.setWindDirectionInDegrees(windDir);
        unitDb2.setCloudiness(cloud);
        unitDb2.setRainVolume(rain);
        unitDb2.setSnowVolume(snow);

        list.add(unitDb1);
        list.add(unitDb2);
        forecastDb.setForecastUnitList(list);

        return forecastDb;
    }
}
