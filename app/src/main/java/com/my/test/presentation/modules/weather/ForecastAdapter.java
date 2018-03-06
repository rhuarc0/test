package com.my.test.presentation.modules.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.test.R;
import com.my.test.domain.entities.Forecast;
import com.my.test.domain.entities.ForecastUnit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<ForecastUnit> forecasts = new ArrayList<>();

    public ForecastAdapter() { }

    public void setItems(List<ForecastUnit> forecasts) {
        this.forecasts.clear();
        this.forecasts.addAll(forecasts);
        notifyDataSetChanged();
    }

    public void clearItems() {
        forecasts.clear();
        notifyDataSetChanged();
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.bind(forecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        @BindView(R.id.li_forecast_tv_date)
        TextView tvDate;
        @BindView(R.id.li_forecast_tv_main_weather)
        TextView tvMainWeather;
        @BindView(R.id.li_forecast_tv_temp_range)
        TextView tvTempRange;

        ForecastViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        void bind(ForecastUnit forecastUnit) {
            Context context = itemView.getContext();
            SimpleDateFormat format = new SimpleDateFormat("EEE, MMM dd", Locale.US);

            tvDate.setText(format.format(forecastUnit.getForecastTime()));
            tvMainWeather.setText(forecastUnit.getWeatherMain());
            tvTempRange.setText(context.getString(R.string.degree_range, forecastUnit.getTempMin(), forecastUnit.getTempMax()));
        }
    }
}
