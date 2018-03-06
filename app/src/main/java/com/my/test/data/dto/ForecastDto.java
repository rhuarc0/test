package com.my.test.data.dto;

import com.google.gson.annotations.SerializedName;
import com.my.test.data.dto.internaldtos.CityDto;

import java.util.List;

public class ForecastDto {
    @SerializedName("cod")
    private String cod;

    @SerializedName("message")
    private String message;

    @SerializedName("cnt")
    private int count;

    @SerializedName("list")
    private List<ForecastUnitDto> forecasts;

    @SerializedName("city")
    private CityDto city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ForecastUnitDto> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastUnitDto> forecasts) {
        this.forecasts = forecasts;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }
}
