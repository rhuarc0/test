package com.my.test.presentation.di.modules;

import android.content.SharedPreferences;

import com.my.test.data.dao.Cache;
import com.my.test.data.dao.CacheImpl;
import com.my.test.data.dao.room.AppDatabase;
import com.my.test.data.datasource.CityDataSource;
import com.my.test.data.datasource.CityDataSourceImpl;
import com.my.test.data.datasource.DefaultsDataSource;
import com.my.test.data.datasource.LocalDataSource;
import com.my.test.data.datasource.RemoteDataSource;
import com.my.test.data.datasource.WeatherDataSource;
import com.my.test.data.mapper.DbToCityMapper;
import com.my.test.data.mapper.DtoToCurrentWeatherMapper;
import com.my.test.data.mapper.DtoToForecastMapper;
import com.my.test.data.repositories.CityRepositoryImpl;
import com.my.test.data.repositories.WeatherRepositoryImpl;
import com.my.test.data.rest.WebService;
import com.my.test.domain.interactors.CitiesInteractor;
import com.my.test.domain.interactors.WeatherInteractor;
import com.my.test.domain.repository.CityRepository;
import com.my.test.domain.repository.WeatherRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module(includes = {RepositoryModule.Declarations.class})
public class RepositoryModule {

    @Provides
    @Singleton
    public DefaultsDataSource provideDefaultsDataSource(SharedPreferences sharedPreferences) {
        return new DefaultsDataSource(sharedPreferences);
    }

    @Provides
    @Named("local")
    @Singleton
    public WeatherDataSource provideLocalDataSource(Cache cache) {
        return new LocalDataSource(cache);
    }

    @Provides
    @Named("remote")
    @Singleton
    public WeatherDataSource provideRemoteDataSource(DtoToCurrentWeatherMapper currentWeatherMapper,
                                              DtoToForecastMapper forecastMapper,
                                              Cache cache,
                                              WebService webService) {
        return new RemoteDataSource(currentWeatherMapper, forecastMapper, cache, webService);
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(@Named("local") WeatherDataSource local,
                                               @Named("remote") WeatherDataSource remote) {
        return new WeatherRepositoryImpl(local, remote);
    }

    @Provides
    @Singleton
    CityRepository provideCityRepository(CityDataSource cityDataSource) {
        return new CityRepositoryImpl(cityDataSource);
    }

    @Provides
    @Singleton
    WeatherInteractor provideWeatherInteractor(@Named(AppModule.COMPUTATION) Scheduler subscriberScheduler,
                                               @Named(AppModule.MAIN) Scheduler observerScheduler,
                                               WeatherRepository weatherRepository,
                                               CityRepository cityRepository,
                                               DefaultsDataSource defaultsDataSource) {
        return new WeatherInteractor(subscriberScheduler,
                observerScheduler,
                weatherRepository,
                cityRepository,
                defaultsDataSource);
    }

    @Provides
    @Singleton
    CitiesInteractor provideCitiesInteractor(@Named(AppModule.COMPUTATION) Scheduler subscriberScheduler,
                                           @Named(AppModule.MAIN) Scheduler observerScheduler,
                                           CityRepository cityRepository) {
        return new CitiesInteractor(subscriberScheduler, observerScheduler, cityRepository);
    }

    @Module
    public interface Declarations {
        @Binds
        @Singleton
        Cache bindCache(CacheImpl cache);

        @Binds
        @Singleton
        CityDataSource bindCityDataSource(CityDataSourceImpl cityDataSource);
    }

}
