package com.my.test.presentation.modules.cities;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.test.R;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.presentation.modules.OnCityClickListener;
import com.my.test.presentation.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private List<CurrentWeather> citiesWithWeather;
    private OnCityActionListener listener;

    public CitiesAdapter(List<CurrentWeather> list, OnCityActionListener listener) {
        citiesWithWeather = list;
        this.listener = listener;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_city, parent, false);
        return new CityViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(citiesWithWeather.get(position), position);
    }

    @Override
    public int getItemCount() {
        return citiesWithWeather.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnCityActionListener listener;
        private int position;

        @BindView(R.id.li_city_tv_name)
        TextView tvName;

        @BindView(R.id.li_city_tv_temperature)
        TextView tvTemperature;

        @BindView(R.id.li_city_tv_info)
        TextView tvInfo;



        CityViewHolder(View itemView, OnCityActionListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        void bind(CurrentWeather weather, int position) {
            this.position = position;
            Context context = tvName.getContext();

            tvName.setText(context.getString(R.string.city_country,
                    weather.getCity().getName(),
                    weather.getCity().getCountry()));
            tvTemperature.setText(context.getString(R.string.degree, weather.getTemperature()));

            String infoLine;
            if (weather.getWindDirectionInDegrees() != null) {
                infoLine = String.format("%s | %s",
                        context.getString(R.string.text_humidity_value, weather.getHumidity()),
                        context.getString(R.string.text_wind_value, weather.getWindSpeed(),
                                Utils.determineWindDirection(weather.getWindDirectionInDegrees())));
            } else {
                infoLine = context.getString(R.string.text_humidity_value, weather.getHumidity());
            }
            tvInfo.setText(infoLine);
        }

        @OnClick(R.id.li_city_iv_delete)
        void onRemoveCityButtonClicked() {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle(R.string.delete_city)
                .setMessage(R.string.delete_city_warning)
                .setNegativeButton(android.R.string.no, (dialogInterface, i) -> {})
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> listener.onDeleteClick(position))
                .create()
                .show();
        }

        @Override
        public void onClick(View view) {
            listener.onClick(position);
        }
    }

    public interface OnCityActionListener {
        void onClick(int position);
        void onDeleteClick(int position);
    }
}
