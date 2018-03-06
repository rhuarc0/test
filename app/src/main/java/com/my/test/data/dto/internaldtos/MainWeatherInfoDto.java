package com.my.test.data.dto.internaldtos;

import com.google.gson.annotations.SerializedName;

public class MainWeatherInfoDto {

    @SerializedName("humidity")
    private Integer humidity;
    @SerializedName("pressure")
    private Float pressure;
    @SerializedName("temp")
    private Float temp;
    @SerializedName("temp_max")
    private Float tempMax;
    @SerializedName("temp_min")
    private Float tempMin;
    @SerializedName("sea_level")
    private Float pressureOnSeaLevel;
    @SerializedName("grnd_level")
    private Float pressureOnGroundLevel;
    @SerializedName("temp_kf")
    private Float tempKf;


    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Float getTempMax() {
        return tempMax;
    }

    public void setTempMax(Float tempMax) {
        this.tempMax = tempMax;
    }

    public Float getTempMin() {
        return tempMin;
    }

    public void setTempMin(Float tempMin) {
        this.tempMin = tempMin;
    }

    public Float getPressureOnSeaLevel() {
        return pressureOnSeaLevel;
    }

    public void setPressureOnSeaLevel(Float pressureOnSeaLevel) {
        this.pressureOnSeaLevel = pressureOnSeaLevel;
    }

    public Float getPressureOnGroundLevel() {
        return pressureOnGroundLevel;
    }

    public void setPressureOnGroundLevel(Float pressureOnGroundLevel) {
        this.pressureOnGroundLevel = pressureOnGroundLevel;
    }

    public Float getTempKf() {
        return tempKf;
    }

    public void setTempKf(Float tempKf) {
        this.tempKf = tempKf;
    }
}
