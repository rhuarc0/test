package com.my.test.presentation.modules.cities.assembly;

import com.my.test.presentation.di.components.AppComponent;
import com.my.test.presentation.di.scopes.PerActivity;
import com.my.test.presentation.modules.cities.CitiesActivity;

import dagger.Component;

@PerActivity
@Component(modules = {CitiesModule.class}, dependencies = {AppComponent.class})
public interface CitiesComponent {
    void inject(CitiesActivity activity);
}
