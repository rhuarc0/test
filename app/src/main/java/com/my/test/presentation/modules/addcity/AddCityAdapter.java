package com.my.test.presentation.modules.addcity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.test.R;
import com.my.test.domain.entities.City;
import com.my.test.presentation.modules.OnCityClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.CityViewHolder> {

    private List<City> cities;
    private OnCityClickListener listener;

    public AddCityAdapter(List<City> cityList, OnCityClickListener listener) {
        this.cities = cityList;
        this.listener = listener;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_find_city, parent, false);
        return new CityViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(cities.get(position), position);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnCityClickListener listener;
        private int position;

        @BindView(R.id.li_find_city_tv_name)
        TextView tvCityName;

        CityViewHolder(View itemView, OnCityClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        void bind(City city, int position) {
            this.position = position;
            tvCityName.setText(String.format("%s, %s", city.getName(), city.getCountry()));
        }

        @Override
        public void onClick(View view) {
            listener.onCityClicked(position);
        }
    }
}
