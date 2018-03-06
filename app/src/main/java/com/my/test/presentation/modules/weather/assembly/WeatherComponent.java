package com.my.test.presentation.modules.weather.assembly;

import com.my.test.presentation.di.components.AppComponent;
import com.my.test.presentation.di.scopes.PerActivity;
import com.my.test.presentation.modules.weather.WeatherActivity;

import dagger.Component;

@PerActivity
@Component(modules = {WeatherModule.class}, dependencies = {AppComponent.class})
public interface WeatherComponent {

    void inject(WeatherActivity activity);
}
