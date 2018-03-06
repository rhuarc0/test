package com.my.test.presentation.modules.addcity.assembly;


import com.my.test.presentation.di.components.AppComponent;
import com.my.test.presentation.di.scopes.PerActivity;
import com.my.test.presentation.modules.addcity.AddCityActivity;

import dagger.Component;

@PerActivity
@Component(modules = {AddCityModule.class}, dependencies = {AppComponent.class})
public interface AddCityComponent {
    void inject(AddCityActivity activity);
}

