package com.my.test;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.my.test.data.dao.databaseentities.CityDb;
import com.my.test.data.dao.databaseentities.CurrentWeatherDb;
import com.my.test.data.dao.databaseentities.ForecastDb;
import com.my.test.data.dao.databaseentities.ForecastUnitDb;
import com.my.test.data.dao.databaseentities.WeatherWithCity;
import com.my.test.data.dto.CurrentWeatherDto;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.stream.JsonReader;
import com.my.test.data.dto.ForecastDto;
import com.my.test.data.mapper.DbToCurrentWeatherMapper;
import com.my.test.data.mapper.DbToForecastMapper;
import com.my.test.data.mapper.DbToForecastUnitMapper;
import com.my.test.data.mapper.DtoToCurrentWeatherMapper;
import com.my.test.data.mapper.DtoToForecastMapper;
import com.my.test.data.mapper.DtoToForecastUnitMapper;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;
import com.my.test.domain.entities.ForecastUnit;

import static org.junit.Assert.assertEquals;

public class MapperTest {
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


    @Test
    public void isDtoToCurrentWeatherMappingCorrect() throws Exception {
        DtoToCurrentWeatherMapper mapper = new DtoToCurrentWeatherMapper();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("current_weather.json").getFile());

        Gson gson = new Gson();
        Type type = new TypeToken<CurrentWeatherDto>() {}.getType();
        JsonReader reader = new JsonReader(new FileReader(file.getPath()));
        CurrentWeatherDto data = gson.fromJson(reader, type);

        CurrentWeather currentWeather = mapper.map(data);

        // 01/30/2017 @ 3:20pm
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 1, 30, 15, 20, 0);

        assertEquals("Drizzle", currentWeather.getWeatherMain());
        assertEquals(280, (int) currentWeather.getTemperature());
        assertEquals(1012, (int) currentWeather.getPressure());
        assertEquals(81, (int) currentWeather.getHumidity());
        assertEquals(4, (int) currentWeather.getWindSpeed());
        assertEquals(80, (int) currentWeather.getWindDirectionInDegrees());
        assertEquals(90, (int) currentWeather.getCloudiness());
        assertEquals(null, currentWeather.getRainVolume());
        assertEquals(null, currentWeather.getSnowVolume());
        int val = calendar.getTime().compareTo(currentWeather.getTimeOfDataCalculation());
        assertEquals(1, val);
        assertEquals(2643743, (int) currentWeather.getCity().getId());
        assertEquals("London", currentWeather.getCity().getName());
    }

    @Test
    public void isDtoToForecastMappingCorrect() throws Exception {
        DtoToForecastUnitMapper unitMapper = new DtoToForecastUnitMapper();
        DtoToForecastMapper mapper = new DtoToForecastMapper(unitMapper);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("forecast.json").getFile());

        Gson gson = new Gson();
        Type type = new TypeToken<ForecastDto>() {}.getType();
        JsonReader reader = new JsonReader(new FileReader(file.getPath()));
        ForecastDto data = gson.fromJson(reader, type);

        Forecast forecast = mapper.map(data);

        assertEquals("Altstadt", forecast.getCity().getName());
        assertEquals(6940463, (int) forecast.getCity().getId());

        assertEquals(2, forecast.getForecastUnitList().size());

        // Unit 1

        ForecastUnit forecastUnit = forecast.getForecastUnitList().get(0);
        // "2017-02-17 21:00:00"
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 2, 17, 21, 0, 0);

        assertEquals("Rain", forecastUnit.getWeatherMain());
        assertEquals(275, (int) forecastUnit.getTemperature());
        assertEquals(276, (int) forecastUnit.getTempMax());
        assertEquals(271, (int) forecastUnit.getTempMin());
        assertEquals(966, (int) forecastUnit.getPressure());
        assertEquals(96, (int) forecastUnit.getHumidity());
        assertEquals(4, (int) forecastUnit.getWindSpeed());
        assertEquals(266, (int) forecastUnit.getWindDirectionInDegrees());
        assertEquals(88, (int) forecastUnit.getCloudiness());
        assertEquals(1, (int) forecastUnit.getRainVolume());
        assertEquals(0, (int) forecastUnit.getSnowVolume());
        int val = calendar.getTime().compareTo(forecastUnit.getForecastTime());
        assertEquals(1, val);

        // Unit 2

        forecastUnit = forecast.getForecastUnitList().get(1);

        // "2017-02-20 21:00:00"
        calendar.set(2017, 2, 20, 21, 0, 0);

        assertEquals("Clouds", forecastUnit.getWeatherMain());
        assertEquals(272, (int) forecastUnit.getTemperature());
        assertEquals(274, (int) forecastUnit.getTempMax());
        assertEquals(270, (int) forecastUnit.getTempMin());
        assertEquals(968, (int) forecastUnit.getPressure());
        assertEquals(85, (int) forecastUnit.getHumidity());
        assertEquals(4, (int) forecastUnit.getWindSpeed());
        assertEquals(256, (int) forecastUnit.getWindDirectionInDegrees());
        assertEquals(20, (int) forecastUnit.getCloudiness());
        assertEquals(null, forecastUnit.getRainVolume());
        assertEquals(null, forecastUnit.getSnowVolume());
        val = calendar.getTime().compareTo(forecastUnit.getForecastTime());
        assertEquals(1, val);


    }

    @Test
    public void isDbToCurrentWeatherMappingCorrect() throws Exception {
        DbToCurrentWeatherMapper mapper = new DbToCurrentWeatherMapper();

        WeatherWithCity data = createWeatherTestObject();

        CurrentWeather currentWeather = mapper.map(data);

        assertEquals(weatherMain, currentWeather.getWeatherMain());
        assertEquals(temp, currentWeather.getTemperature());
        assertEquals(pressure, currentWeather.getPressure());
        assertEquals(humidity, currentWeather.getHumidity());
        assertEquals(windSpeed, currentWeather.getWindSpeed());
        assertEquals(windDir, currentWeather.getWindDirectionInDegrees());
        assertEquals(cloud, currentWeather.getCloudiness());
        assertEquals(rain, currentWeather.getRainVolume());
        assertEquals(null, currentWeather.getSnowVolume());
        assertEquals(date, currentWeather.getTimeOfDataCalculation());
        assertEquals(cityId1, currentWeather.getCity().getId());
        assertEquals(cityName, currentWeather.getCity().getName());
    }

    @Test
    public void isDbToForecastMappingCorrect() throws Exception {
        DbToForecastUnitMapper unitMapper = new DbToForecastUnitMapper();
        DbToForecastMapper mapper = new DbToForecastMapper(unitMapper);

        ForecastDb data = createForecastTestObject();

        Forecast forecast = mapper.map(data);

        assertEquals(cityName, forecast.getCity().getName());
        assertEquals(cityId1, forecast.getCity().getId());

        assertEquals(2, forecast.getForecastUnitList().size());

        // Unit 1

        ForecastUnit forecastUnit = forecast.getForecastUnitList().get(0);

        assertEquals(weatherMain, forecastUnit.getWeatherMain());
        assertEquals(temp, forecastUnit.getTemperature());
        assertEquals(temp, forecastUnit.getTempMax());
        assertEquals(temp, forecastUnit.getTempMin());
        assertEquals(pressure, forecastUnit.getPressure());
        assertEquals(humidity, forecastUnit.getHumidity());
        assertEquals(windSpeed, forecastUnit.getWindSpeed());
        assertEquals(windDir, forecastUnit.getWindDirectionInDegrees());
        assertEquals(cloud, forecastUnit.getCloudiness());
        assertEquals(rain, forecastUnit.getRainVolume());
        assertEquals(null, forecastUnit.getSnowVolume());
        assertEquals(date, forecastUnit.getForecastTime());
    }

    private WeatherWithCity createWeatherTestObject() {

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
