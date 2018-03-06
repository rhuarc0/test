package com.my.test.data.mapper;

import com.my.test.data.dto.CurrentWeatherDto;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;

import java.util.Date;

import javax.inject.Inject;

public class DtoToCurrentWeatherMapper extends BaseMapper<CurrentWeatherDto, CurrentWeather> {

    @Override
    public CurrentWeather map(CurrentWeatherDto source) {
        City city = City.builder()
                .setId(source.getCityId())
                .setName(source.getCityName())
                .setCountry(source.getSys().getCountry())
                .build();

        String weatherMain = "";
        if (source.getWeather().size() > 0)
            weatherMain = source.getWeather().get(0).getMain();

        Integer temperature = null;
        if (source.getMain().getTemp() != null)
            temperature = Math.round(source.getMain().getTemp());

        Integer pressure = null;
        if (source.getMain().getPressure() != null)
            pressure = Math.round(source.getMain().getPressure());

        Integer humidity = source.getMain().getHumidity();

        Integer windSpeed = null;
        if (source.getWind().getSpeed() != null)
            windSpeed = Math.round(source.getWind().getSpeed());

        Integer windDirectionInDegrees = null;
        if (source.getWind().getDeg() != null)
            windDirectionInDegrees = Math.round(source.getWind().getDeg());

        Integer cloudiness = null;
        if (source.getClouds() != null)
            cloudiness = source.getClouds().getAll();

        Integer rainVolume = null;
        if (source.getRain() != null)
            rainVolume = Math.round(source.getRain().getVolume());

        Integer snowVolume = null;
        if (source.getSnow() != null)
            snowVolume = Math.round(source.getSnow().getVolume());

        Date timeOfDataCalculation = new Date(source.getDt() * 1000);

        return CurrentWeather.builder()
                .setCity(city)
                .setWeatherMain(weatherMain)
                .setTemperature(temperature)
                .setPressure(pressure)
                .setHumidity(humidity)
                .setWindSpeed(windSpeed)
                .setWindDirectionInDegrees(windDirectionInDegrees)
                .setCloudiness(cloudiness)
                .setRainVolume(rainVolume)
                .setSnowVolume(snowVolume)
                .setTimeOfDataCalculation(timeOfDataCalculation)
                .build();
    }
}
