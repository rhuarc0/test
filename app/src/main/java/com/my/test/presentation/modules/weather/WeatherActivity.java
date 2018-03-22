package com.my.test.presentation.modules.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.my.test.R;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.ForecastUnit;
import com.my.test.presentation.di.components.AppComponent;
import com.my.test.presentation.modules.base.MvpActivity;
import com.my.test.presentation.modules.weather.assembly.DaggerWeatherComponent;
import com.my.test.presentation.utils.Layout;
import com.my.test.presentation.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


@Layout(R.layout.act_main)
public class WeatherActivity extends MvpActivity implements WeatherContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.tv_toolbar_text)
    TextView tvToolbarText;

    @BindView(R.id.act_weather_tv_city)
    TextView tvCity;

    @BindView(R.id.act_weather_tv_temperature)
    TextView tvToolbarTemperature;

    @BindView(R.id.act_weather_tv_main_weather)
    TextView tvMainWeather;

    @BindView(R.id.pb_weather)
    ProgressBar pbWeather;

    @BindView(R.id.cv_outdated)
    CardView cvOutdated;

    @BindView(R.id.card_details)
    CardView cvDetails;

    @BindView(R.id.detail_layout)
    ViewGroup vgDetail;

    @BindView(R.id.pb_details)
    ProgressBar pbDetail;

    @BindView(R.id.pb_forecast)
    ProgressBar pbForecast;

    @BindView(R.id.tv_humidity_value)
    TextView tvHumidity;

    @BindView(R.id.tv_pressure_value)
    TextView tvPressure;

    @BindView(R.id.tv_wind_value)
    TextView tvWind;

    @BindView(R.id.tv_cloudiness_value)
    TextView tvCloudiness;

    @BindView(R.id.act_weather_rv_forecast)
    RecyclerView rvForecast;

    @BindView(R.id.act_weather_iv_no_cities)
    ImageView ivNoCities;



    @Inject
    WeatherContract.Presenter presenter;

    ForecastAdapter adapter = new ForecastAdapter();

    private int currentCheckedMenuItem = -1;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Показываем toolbarText в первые 33% развёрнутого CollapsingToolbar

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            float thresholdPercentage = 0.33f;
            int threshold = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (threshold == -1)
                    threshold = (int) (appBarLayout.getTotalScrollRange() * (1 - thresholdPercentage));

                appBarLayout.getTotalScrollRange();
                if (Math.abs(verticalOffset) < threshold) {
                    tvToolbarText.setAlpha(0);
                } else {
                    float alpha = (Math.abs(verticalOffset) - threshold) / (float) appBarLayout.getTotalScrollRange() / thresholdPercentage;
                    tvToolbarText.setAlpha(alpha);
                }
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Navigation View
        navigationView.setNavigationItemSelectedListener(this);

        // Forecast recycler view
        rvForecast.setLayoutManager(new LinearLayoutManager(this));
        rvForecast.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        presenter.attachView(this);
    }

    @Override
    protected void beforeDestroy() {
        presenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh every time when a user sees the screen
        presenter.refresh();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        DaggerWeatherComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void setWeatherPending(boolean state) {
        if (state) {
            pbWeather.setVisibility(View.VISIBLE);
            tvCity.setVisibility(View.GONE);
            tvToolbarTemperature.setVisibility(View.GONE);
            tvMainWeather.setVisibility(View.GONE);

            pbDetail.setVisibility(View.VISIBLE);
            vgDetail.setVisibility(View.GONE);
        } else {
            pbWeather.setVisibility(View.GONE);
            tvCity.setVisibility(View.VISIBLE);
            tvToolbarTemperature.setVisibility(View.VISIBLE);
            tvMainWeather.setVisibility(View.VISIBLE);

            pbDetail.setVisibility(View.GONE);
            vgDetail.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setForecastPending(boolean state) {
        if (state) {
            pbForecast.setVisibility(View.VISIBLE);
            rvForecast.setVisibility(View.GONE);
        } else {
            pbForecast.setVisibility(View.GONE);
            rvForecast.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showCityList(List<City> cityList) {
        Menu menu = navigationView.getMenu();
        menu.removeGroup(R.id.menu_cities_group);
        for (int i = 0; i < cityList.size(); i++) {
            MenuItem menuItem;
            menuItem = menu.add(R.id.menu_cities_group, i, 0, cityList.get(i).getName());
            menuItem.setIcon(R.drawable.ic_place_black_24px);
        }
    }

    @Override
    public void setCurrentCityMenuPosition(int position) {
        if (currentCheckedMenuItem != -1) {
            MenuItem menuItem = navigationView.getMenu().findItem(currentCheckedMenuItem);
            if (menuItem != null)
                menuItem.setChecked(false);
        }
        navigationView.getMenu().findItem(position).setChecked(true);
        currentCheckedMenuItem = position;
    }

    @Override
    public void showCurrentWeather(CurrentWeather currentWeather) {
        tvCity.setText(getString(R.string.city_country,
                currentWeather.getCity().getName(),
                currentWeather.getCity().getCountry()));
        if (currentWeather.getTemperature() != null){
            tvToolbarTemperature.setText(getString(R.string.degree, currentWeather.getTemperature()));
        } else {
            tvToolbarTemperature.setText("");
        }
        tvMainWeather.setText(currentWeather.getWeatherMain());

        if (currentWeather.getTemperature() != null) {
            tvToolbarText.setText(getString(R.string.toolbar_text,
                    currentWeather.getCity().getName(),
                    currentWeather.getTemperature()));
        } else {
            tvToolbarText.setText("");
        }

        tvHumidity.setText(getString(R.string.text_humidity_value, currentWeather.getHumidity()));
        if (currentWeather.getCloudiness() != null) {
            tvCloudiness.setText(getString(R.string.text_cloudiness_value, currentWeather.getCloudiness()));
        } else {
            tvCloudiness.setText("");
        }
        if (currentWeather.getWindDirectionInDegrees() != null) {
            tvWind.setText(getString(R.string.text_wind_value, currentWeather.getWindSpeed(),
                    Utils.determineWindDirection(currentWeather.getWindDirectionInDegrees())));
        } else {
            tvWind.setText("");
        }

        if (currentWeather.getPressure() != null) {
            tvPressure.setText(getString(R.string.text_pressure_value, currentWeather.getPressure()));
        } else {
            tvPressure.setText("");
        }
        cvDetails.setVisibility(View.VISIBLE);
        vgDetail.setVisibility(View.VISIBLE);
        ivNoCities.setVisibility(View.GONE);
    }

    @Override
    public void showForecast(List<ForecastUnit> forecastUnits) {
        adapter.setItems(forecastUnits);
        rvForecast.setAdapter(adapter);
        rvForecast.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyState() {
        tvToolbarText.setText(getString(R.string.no_cities));
        tvCity.setText("");
        tvToolbarTemperature.setText(getString(R.string.no_cities));
        tvMainWeather.setText("");

        adapter.clearItems();
        tvHumidity.setText("");
        tvPressure.setText("");
        tvWind.setText("");
        tvCloudiness.setText("");

        rvForecast.setVisibility(View.GONE);
        cvDetails.setVisibility(View.GONE);
        cvOutdated.setVisibility(View.GONE);
        ivNoCities.setVisibility(View.VISIBLE);
    }

    @Override
    public void showIfWeatherIsObsolete(boolean isObsolete) {
        if (isObsolete)
            cvOutdated.setVisibility(View.VISIBLE);
        else
            cvOutdated.setVisibility(View.GONE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getGroupId() == R.id.menu_cities_group) {
            if (currentCheckedMenuItem != -1)
                navigationView.getMenu().findItem(currentCheckedMenuItem).setChecked(false);
            item.setChecked(true);
            currentCheckedMenuItem = item.getItemId();

            presenter.selectCity(currentCheckedMenuItem);
        } else if (item.getItemId() == R.id.nav_manage_locations) {
            activityNavigator.navigateToCitiesScreen(this);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
