package com.my.test.presentation.modules.addcity.assembly;

import com.my.test.presentation.di.scopes.PerActivity;
import com.my.test.presentation.modules.addcity.AddCityContract;
import com.my.test.presentation.modules.addcity.AddCityPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@PerActivity
@Module
public interface AddCityModule {
    @PerActivity
    @Binds
    AddCityContract.Presenter bindPresenter(AddCityPresenter addCityPresenter);
}

