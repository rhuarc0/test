package com.my.test.presentation.modules.weather.assembly;

import com.my.test.presentation.di.scopes.PerActivity;
import com.my.test.presentation.modules.weather.WeatherContract;
import com.my.test.presentation.modules.weather.WeatherPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@PerActivity
@Module
public interface WeatherModule {
    @PerActivity
    @Binds
    WeatherContract.Presenter bindPresenter(WeatherPresenter weatherPresenter);
}
