package com.my.test.data.mapper;

import com.my.test.data.dao.databaseentities.CityDb;
import com.my.test.domain.entities.City;

public class DbToCityMapper extends BaseTwoWayMapper<CityDb, City> {
    @Override
    public City map(CityDb source) {
        return City.builder()
                .setId(source.getId())
                .setCountry(source.getCountry())
                .setName(source.getName())
                .build();
    }

    @Override
    public CityDb reverseMap(City source) {
        CityDb cityDb = new CityDb();
        cityDb.setId(source.getId());
        cityDb.setName(source.getName());
        cityDb.setCountry(source.getCountry());
        return cityDb;
    }
}
