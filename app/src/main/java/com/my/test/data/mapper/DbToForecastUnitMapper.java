package com.my.test.data.mapper;

import com.my.test.data.dao.databaseentities.ForecastUnitDb;
import com.my.test.domain.entities.ForecastUnit;

public class DbToForecastUnitMapper extends BaseTwoWayMapper<ForecastUnitDb, ForecastUnit> {
    @Override
    public ForecastUnit map(ForecastUnitDb source) {
            return ForecastUnit.builder()
                    .setWeatherMain(source.getWeatherMain())
                    .setTemperature(source.getTemperature())
                    .setTempMax(source.getTempMax())
                    .setTempMin(source.getTempMin())
                    .setPressure(source.getPressure())
                    .setHumidity(source.getHumidity())
                    .setWindSpeed(source.getWindSpeed())
                    .setWindDirectionInDegrees(source.getWindDirectionInDegrees())
                    .setCloudiness(source.getCloudiness())
                    .setRainVolume(source.getRainVolume())
                    .setSnowVolume(source.getSnowVolume())
                    .setForecastTime(source.getForecastTime())
                    .build();

    }

    @Override
    public ForecastUnitDb reverseMap(ForecastUnit forecastUnit) {
        ForecastUnitDb result = new ForecastUnitDb();

        result.setWeatherMain(forecastUnit.getWeatherMain());
        result.setTemperature(forecastUnit.getTemperature());
        result.setTempMax(forecastUnit.getTempMax());
        result.setTempMin(forecastUnit.getTempMin());
        result.setPressure(forecastUnit.getPressure());
        result.setHumidity(forecastUnit.getHumidity());
        result.setWindSpeed(forecastUnit.getWindSpeed());
        result.setWindDirectionInDegrees(forecastUnit.getWindDirectionInDegrees());
        result.setCloudiness(forecastUnit.getCloudiness());
        result.setRainVolume(forecastUnit.getRainVolume());
        result.setSnowVolume(forecastUnit.getSnowVolume());
        result.setForecastTime(forecastUnit.getForecastTime());

        return result;
    }
}
