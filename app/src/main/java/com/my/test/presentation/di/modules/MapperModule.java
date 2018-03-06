package com.my.test.presentation.di.modules;

import com.my.test.data.mapper.DbToCityMapper;
import com.my.test.data.mapper.DbToCurrentWeatherMapper;
import com.my.test.data.mapper.DbToForecastMapper;
import com.my.test.data.mapper.DbToForecastUnitMapper;
import com.my.test.data.mapper.DtoToCurrentWeatherMapper;
import com.my.test.data.mapper.DtoToForecastMapper;
import com.my.test.data.mapper.DtoToForecastUnitMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {


    @Provides
    @Singleton
    DtoToCurrentWeatherMapper provideDtoToCurrentWeatherMapper() {
        return new DtoToCurrentWeatherMapper();
    }

    @Provides
    @Singleton
    DtoToForecastUnitMapper provideDtoToForecastUnitMapper() {
        return new DtoToForecastUnitMapper();
    }

    @Provides
    @Singleton
    DtoToForecastMapper provideDtoToForecastMapper(DtoToForecastUnitMapper dtoToForecastUnitMapper) {
        return new DtoToForecastMapper(dtoToForecastUnitMapper);
    }


    @Provides
    @Singleton
    DbToCurrentWeatherMapper provideDbToCurrentWeatherMapper() {
        return new DbToCurrentWeatherMapper();
    }

    @Provides
    @Singleton
    DbToForecastUnitMapper provideDbToForecastUnitMapper() {
        return new DbToForecastUnitMapper();
    }

    @Provides
    @Singleton
    DbToForecastMapper provideDbToForecastMapper(DbToForecastUnitMapper dtoToForecastUnitMapper) {
        return new DbToForecastMapper(dtoToForecastUnitMapper);
    }

    @Provides
    @Singleton
    DbToCityMapper provideDbToCityMapper() {
        return new DbToCityMapper();
    }
}
