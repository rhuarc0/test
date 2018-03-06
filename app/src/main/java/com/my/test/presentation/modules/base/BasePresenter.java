package com.my.test.presentation.modules.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {

    protected V view;
    protected CompositeDisposable subscriptions;

    {
        subscriptions = new CompositeDisposable();
    }

    @Override
    public void attachView(V view) {
        this.view = view;
        onViewAttached();
    }

    @Override
    public void detachView() {
        view = null;
        onViewDetached();
    }

    @Override
    public abstract void onViewAttached();

    @Override
    public void onViewDetached() {
        subscriptions.clear();
    }
}