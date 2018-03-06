package com.my.test.presentation.modules.cities.assembly;

import com.my.test.presentation.di.scopes.PerActivity;
import com.my.test.presentation.modules.cities.CitiesContract;
import com.my.test.presentation.modules.cities.CitiesPresenter;

import dagger.Binds;
import dagger.Module;

@PerActivity
@Module
public interface CitiesModule {

    @PerActivity
    @Binds
    CitiesContract.Presenter bindPresenter(CitiesPresenter citiesPresenter);
}
