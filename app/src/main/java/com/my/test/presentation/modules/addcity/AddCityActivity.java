package com.my.test.presentation.modules.addcity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.my.test.R;
import com.my.test.domain.entities.City;
import com.my.test.presentation.di.components.AppComponent;
import com.my.test.presentation.modules.OnCityClickListener;
import com.my.test.presentation.modules.addcity.assembly.DaggerAddCityComponent;
import com.my.test.presentation.modules.base.MvpActivity;
import com.my.test.presentation.utils.Layout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@Layout(R.layout.act_find_city)
public class AddCityActivity extends MvpActivity
        implements AddCityContract.View, SearchView.OnQueryTextListener, OnCityClickListener {


    @BindView(R.id.act_find_city_et_city)
    SearchView svCity;

    @BindView(R.id.act_find_city_rv)
    RecyclerView rvCities;

    @BindView(R.id.act_find_city_btn_search)
    Button btnSearch;

    @BindView(R.id.act_find_city_tv_empty_state)
    TextView tvEmptyState;

    @Inject
    AddCityContract.Presenter presenter;

    AddCityAdapter adapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, AddCityActivity.class);
    }

    @OnClick(R.id.act_find_city_btn_search)
    public void onButtonSearchClicked() {
        if (!"".equals(svCity.getQuery().toString()))
            presenter.findCities(svCity.getQuery().toString());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_add_city);

        svCity.setOnQueryTextListener(this);

        rvCities.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvCities.setLayoutManager(new LinearLayoutManager(this));

        SearchView.SearchAutoComplete searchPlate = svCity.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchPlate.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH)
                onButtonSearchClicked();
            return false;
        });

        svCity.onActionViewExpanded();

        presenter.attachView(this);
    }

    protected void beforeDestroy() {
        presenter.detachView();
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        DaggerAddCityComponent.builder()
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
    public void showCities(List<City> cities) {
        adapter = new AddCityAdapter(cities, this);
        rvCities.setAdapter(adapter);

        tvEmptyState.setVisibility(View.GONE);
        rvCities.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyState() {
        tvEmptyState.setVisibility(View.VISIBLE);
        rvCities.setVisibility(View.GONE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        btnSearch.setEnabled(newText.length() != 0);
        return true;
    }

    @Override
    public void onCityClicked(int position) {
        presenter.onCityClicked(position);
    }

}
