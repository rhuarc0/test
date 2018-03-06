package com.my.test.data.mapper;

import com.my.test.data.dao.databaseentities.WeatherWithCity;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.data.dao.databaseentities.CityDb;
import com.my.test.data.dao.databaseentities.CurrentWeatherDb;

public class DbToCurrentWeatherMapper extends BaseTwoWayMapper<WeatherWithCity, CurrentWeather> {

    @Override
    public CurrentWeather map(WeatherWithCity source) {
        City city = City.builder()
                .setId(source.getWeatherDb().getCityId())
                .setCountry(source.getCountry())
                .setName(source.getCityName())
                .build();

        return CurrentWeather.builder()
                .setCity(city)
                .setWeatherMain(source.getWeatherDb().getWeatherMain())
                .setTemperature(source.getWeatherDb().getTemperature())
                .setPressure(source.getWeatherDb().getPressure())
                .setHumidity(source.getWeatherDb().getHumidity())
                .setWindSpeed(source.getWeatherDb().getWindSpeed())
                .setWindDirectionInDegrees(source.getWeatherDb().getWindDirectionInDegrees())
                .setCloudiness(source.getWeatherDb().getCloudiness())
                .setRainVolume(source.getWeatherDb().getRainVolume())
                .setSnowVolume(source.getWeatherDb().getSnowVolume())
                .setTimeOfDataCalculation(source.getWeatherDb().getTimeOfDataCalculation())
                .build();
    }

    @Override
    public WeatherWithCity reverseMap(CurrentWeather source) {
        WeatherWithCity result = new WeatherWithCity();
        CurrentWeatherDb currentWeatherDb = new CurrentWeatherDb();

        currentWeatherDb.setCityId(source.getCity().getId());
        currentWeatherDb.setWeatherMain(source.getWeatherMain());
        currentWeatherDb.setTemperature(source.getTemperature());
        currentWeatherDb.setPressure(source.getPressure());
        currentWeatherDb.setHumidity(source.getHumidity());
        currentWeatherDb.setWindSpeed(source.getWindSpeed());
        currentWeatherDb.setWindDirectionInDegrees(source.getWindDirectionInDegrees());
        currentWeatherDb.setCloudiness(source.getCloudiness());
        currentWeatherDb.setRainVolume(source.getRainVolume());
        currentWeatherDb.setSnowVolume(source.getSnowVolume());
        currentWeatherDb.setTimeOfDataCalculation(source.getTimeOfDataCalculation());

        result.setWeatherDb(currentWeatherDb);
        result.setCityName(source.getCity().getName());
        result.setCountry(source.getCity().getCountry());

        return result;
    }
}
