package com.my.test.data.datasource;

import android.content.SharedPreferences;

/**
 * Хранение всяких индексов по умолчанию
 */
public class DefaultsDataSource {
    private static final String CURRENT_CITY_INDEX = "current_city_index";

    SharedPreferences sharedPreferences;

    public DefaultsDataSource(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public int getCurrentCityPosition() {
        return sharedPreferences.getInt(CURRENT_CITY_INDEX, -1);
    }

    public void setCurrentCityPosition(int index) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CURRENT_CITY_INDEX, index);
        editor.apply();
    }
}
