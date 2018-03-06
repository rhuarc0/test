package com.my.test.presentation.di.components;

import com.my.test.domain.interactors.CitiesInteractor;
import com.my.test.domain.interactors.WeatherInteractor;
import com.my.test.presentation.ActivityNavigator;
import com.my.test.presentation.di.modules.AppModule;
import com.my.test.presentation.di.modules.DatabaseModule;
import com.my.test.presentation.di.modules.MapperModule;
import com.my.test.presentation.di.modules.NetworkModule;
import com.my.test.presentation.di.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, MapperModule.class, NetworkModule.class, DatabaseModule.class, RepositoryModule.class})
public interface AppComponent {
    ActivityNavigator provideActivityNavigator();
    WeatherInteractor provideWeatherInteractor();
    CitiesInteractor provideCitiesInteractor();
}
