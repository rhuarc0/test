package com.my.test.data.mapper;

import com.my.test.data.dto.ForecastDto;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.Forecast;
import com.my.test.domain.entities.ForecastUnit;

import java.util.List;

public class DtoToForecastMapper extends BaseMapper<ForecastDto, Forecast> {

    private DtoToForecastUnitMapper unitMapper;

    public DtoToForecastMapper(DtoToForecastUnitMapper unitMapper) {
        this.unitMapper = unitMapper;
    }

    @Override
    public Forecast map(ForecastDto source) {
        City city = City.builder()
                .setId(source.getCity().getId())
                .setName(source.getCity().getName())
                .setCountry(source.getCity().getCountry())
                .build();

        List<ForecastUnit> list = unitMapper.map(source.getForecasts());

        return Forecast.builder()
                .setCity(city)
                .setForecastUnitList(list)
                .build();

    }
}
