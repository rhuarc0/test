package com.my.test.presentation.modules.cities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.my.test.R;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.presentation.di.components.AppComponent;
import com.my.test.presentation.modules.OnCityClickListener;
import com.my.test.presentation.modules.base.MvpActivity;
import com.my.test.presentation.modules.cities.assembly.DaggerCitiesComponent;
import com.my.test.presentation.utils.Layout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@Layout(R.layout.act_cities)
public class CitiesActivity extends MvpActivity implements CitiesContract.View, CitiesAdapter.OnCityActionListener {

    @BindView(R.id.act_cities_rv)
    RecyclerView rvContent;

    @BindView(R.id.act_cities_tv_empty_state)
    TextView tvEmptyState;

    @BindView(R.id.act_cities_fab)
    FloatingActionButton fabAdd;

    @Inject
    CitiesContract.Presenter presenter;

    CitiesAdapter adapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, CitiesActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_cities);

        rvContent.setLayoutManager(new LinearLayoutManager(this));

        presenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.refresh();
    }

    protected void beforeDestroy() {
        presenter.detachView();
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        DaggerCitiesComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showCities(List<CurrentWeather> citiesWithWeather) {
        adapter = new CitiesAdapter(citiesWithWeather, this);
        rvContent.setAdapter(adapter);

        tvEmptyState.setVisibility(View.GONE);
        rvContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyState() {
        tvEmptyState.setVisibility(View.VISIBLE);
        rvContent.setVisibility(View.GONE);
    }

    @OnClick(R.id.act_cities_fab)
    public void onFabAddClicked() {
        activityNavigator.navigateToAddCityScreen(this);
    }

    @Override
    public void onClick(int position) {
        presenter.onCityClicked(position);
        finish();
    }

    @Override
    public void onDeleteClick(int position) {
        presenter.onCityDeleteClicked(position);
    }
}
