package com.my.test.presentation;

import android.content.Context;

import com.my.test.presentation.modules.addcity.AddCityActivity;
import com.my.test.presentation.modules.cities.CitiesActivity;


public class ActivityNavigator {
/*
    @Inject
    public ActivityNavigator() {
        //empty
    }
*/

    public void navigateToCitiesScreen(Context context) {
        context.startActivity(CitiesActivity.newIntent(context));
    }

    public void navigateToAddCityScreen(Context context) {
        context.startActivity(AddCityActivity.newIntent(context));
    }

}
