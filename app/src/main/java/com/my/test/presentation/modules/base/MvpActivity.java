package com.my.test.presentation.modules.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.my.test.presentation.ActivityNavigator;
import com.my.test.presentation.App;
import com.my.test.presentation.di.components.AppComponent;
import com.my.test.presentation.utils.Layout;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class MvpActivity extends AppCompatActivity implements BaseContract.View {

    @Inject
    protected ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout();
        injectDependencies(getApp().getAppComponent());
    }

    @Override
    protected void onDestroy() {
        beforeDestroy();
        super.onDestroy();
    }

    protected void bindLayout() {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.value());
        ButterKnife.bind(this);
    }

    protected abstract void beforeDestroy();

    protected abstract void injectDependencies(AppComponent appComponent);

    @Override
    public App getApp() {
        return (App) getApplication();
    }

    @Override
    public void showMessage(String message) {
        View rootView = this.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(@StringRes int messageResId) {
        View rootView = this.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, messageResId, Snackbar.LENGTH_SHORT).show();
    }

}
