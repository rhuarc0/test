package com.my.test.presentation;

import android.app.Application;
import android.util.Log;

import com.my.test.presentation.di.components.AppComponent;
import com.my.test.presentation.di.components.DaggerAppComponent;
import com.my.test.presentation.di.modules.AppModule;
import com.my.test.presentation.di.modules.DatabaseModule;
import com.my.test.presentation.di.modules.MapperModule;
import com.my.test.presentation.di.modules.NetworkModule;
import com.my.test.presentation.di.modules.RepositoryModule;

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .repositoryModule(new RepositoryModule())
                .mapperModule(new MapperModule())
                .databaseModule(new DatabaseModule())
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
