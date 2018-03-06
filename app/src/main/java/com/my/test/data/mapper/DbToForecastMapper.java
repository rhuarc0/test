package com.my.test.data.mapper;

import com.my.test.data.dao.databaseentities.CityDb;
import com.my.test.data.dao.databaseentities.ForecastDb;
import com.my.test.data.dao.databaseentities.ForecastUnitDb;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.Forecast;
import com.my.test.domain.entities.ForecastUnit;

import java.util.List;

public class DbToForecastMapper extends BaseTwoWayMapper<ForecastDb, Forecast> {

    private DbToForecastUnitMapper unitMapper;

    public DbToForecastMapper(DbToForecastUnitMapper unitMapper) {
        this.unitMapper = unitMapper;
    }


    @Override
    public Forecast map(ForecastDb source) {
        City city = City.builder()
                .setId(source.getCity().getId())
                .setName(source.getCity().getName())
                .setCountry(source.getCity().getCountry())
                .build();

        List<ForecastUnit> list = unitMapper.map(source.getForecastUnitList());

        return Forecast.builder()
                .setCity(city)
                .setForecastUnitList(list)
                .build();
    }

    @Override
    public ForecastDb reverseMap(Forecast forecast) {
        ForecastDb forecastDb = new ForecastDb();

        CityDb cityDb = new CityDb();
        cityDb.setId(forecast.getCity().getId());
        cityDb.setName(forecast.getCity().getName());
        cityDb.setCountry(forecast.getCity().getCountry());

        List<ForecastUnitDb> list = unitMapper.reverseMap(forecast.getForecastUnitList());

        forecastDb.setCity(cityDb);
        forecastDb.setForecastUnitList(list);

        return forecastDb;
    }
}
