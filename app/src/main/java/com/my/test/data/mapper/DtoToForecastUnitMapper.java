package com.my.test.data.mapper;

import com.my.test.data.dto.ForecastUnitDto;
import com.my.test.domain.entities.ForecastUnit;

import java.util.Date;

public class DtoToForecastUnitMapper extends BaseMapper<ForecastUnitDto, ForecastUnit> {

    @Override
    public ForecastUnit map(ForecastUnitDto source) {
        String weatherMain = "";
        if (source.getWeather().size() > 0)
            weatherMain = source.getWeather().get(0).getMain();

        Integer temperature = null;
        if (source.getMain().getTemp() != null)
            temperature = Math.round(source.getMain().getTemp());

        Integer tempMin = null;
        if (source.getMain().getTempMin() != null)
            tempMin = Math.round(source.getMain().getTempMin());

        Integer tempMax = null;
        if (source.getMain().getTempMax() != null)
            tempMax = Math.round(source.getMain().getTempMax());

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
        if (source.getRain() != null && source.getRain().getVolume() != null)
            rainVolume = Math.round(source.getRain().getVolume());

        Integer snowVolume = null;
        if (source.getSnow() != null && source.getSnow().getVolume() != null)
            snowVolume = Math.round(source.getSnow().getVolume());

        Date forecastTime = new Date(source.getForecastUnixTime() * 1000);

        return ForecastUnit.builder()
                .setWeatherMain(weatherMain)
                .setTemperature(temperature)
                .setTempMax(tempMax)
                .setTempMin(tempMin)
                .setPressure(pressure)
                .setHumidity(humidity)
                .setWindSpeed(windSpeed)
                .setWindDirectionInDegrees(windDirectionInDegrees)
                .setCloudiness(cloudiness)
                .setRainVolume(rainVolume)
                .setSnowVolume(snowVolume)
                .setForecastTime(forecastTime)
                .build();

    }
}
