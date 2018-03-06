package com.my.test.domain.interactors;

import android.support.annotation.NonNull;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

public abstract class BaseInteractor {

    protected Scheduler subscriberScheduler;
    protected Scheduler observerScheduler;

    public BaseInteractor(@NonNull Scheduler subscriberScheduler, @NonNull Scheduler observerScheduler) {
        this.subscriberScheduler = subscriberScheduler;
        this.observerScheduler = observerScheduler;
    }

    protected <T> ObservableTransformer<T, T> applySchedulers() {
        return upstream -> upstream.subscribeOn(subscriberScheduler)
                .observeOn(observerScheduler);
    }

    protected CompletableTransformer applyCompletableSchedulers() {
        return upstream -> upstream.subscribeOn(subscriberScheduler)
                .observeOn(observerScheduler);
    }
}
