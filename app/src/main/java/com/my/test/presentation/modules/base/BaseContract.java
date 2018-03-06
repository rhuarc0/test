package com.my.test.presentation.modules.base;

import android.support.annotation.StringRes;

import com.my.test.presentation.App;

public interface BaseContract {

    interface View {
        App getApp();
        void showMessage(String message);
        void showMessage(@StringRes int messageResId);
    }

    interface Presenter<V extends View> {
        void attachView(V view);
        void detachView();

        void onViewAttached();
        void onViewDetached();
    }

}
