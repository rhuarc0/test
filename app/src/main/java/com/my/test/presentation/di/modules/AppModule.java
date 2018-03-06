package com.my.test.presentation.di.modules;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.my.test.presentation.ActivityNavigator;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {

    private Application application;

    public static final String COMPUTATION = "computation";
    public static final String MAIN = "main";

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    @Named(COMPUTATION)
    public Scheduler provideComputationScheduler() {
        return Schedulers.computation();
    }

    @Provides
    @Singleton
    @Named(MAIN)
    public Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }


    @Provides
    @Singleton
    public ActivityNavigator provideActivityNavigator() {
        return new ActivityNavigator();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
