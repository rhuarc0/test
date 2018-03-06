package com.my.test.data.dto;

import com.google.gson.annotations.SerializedName;
import com.my.test.data.dto.internaldtos.CloudsDto;
import com.my.test.data.dto.internaldtos.MainWeatherInfoDto;
import com.my.test.data.dto.internaldtos.PrecipitationVolumeDto;
import com.my.test.data.dto.internaldtos.WeatherConditionDto;
import com.my.test.data.dto.internaldtos.WindDto;

import java.util.List;

public class ForecastUnitDto {
    @SerializedName("dt")
    private long forecastUnixTime;
    @SerializedName("main")
    private MainWeatherInfoDto main;
    @SerializedName("weather")
    private List<WeatherConditionDto> weather;
    @SerializedName("clouds")
    private CloudsDto clouds;
    @SerializedName("wind")
    private WindDto wind;
    @SerializedName("rain")
    private PrecipitationVolumeDto rain;
    @SerializedName("snow")
    private PrecipitationVolumeDto snow;
    @SerializedName("dt_txt")
    private String calculationTime;

    public long getForecastUnixTime() {
        return forecastUnixTime;
    }

    public void setForecastUnixTime(long forecastUnixTime) {
        this.forecastUnixTime = forecastUnixTime;
    }

    public MainWeatherInfoDto getMain() {
        return main;
    }

    public void setMain(MainWeatherInfoDto main) {
        this.main = main;
    }

    public List<WeatherConditionDto> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherConditionDto> weather) {
        this.weather = weather;
    }

    public CloudsDto getClouds() {
        return clouds;
    }

    public void setClouds(CloudsDto clouds) {
        this.clouds = clouds;
    }

    public WindDto getWind() {
        return wind;
    }

    public void setWind(WindDto wind) {
        this.wind = wind;
    }

    public PrecipitationVolumeDto getRain() {
        return rain;
    }

    public void setRain(PrecipitationVolumeDto rain) {
        this.rain = rain;
    }

    public PrecipitationVolumeDto getSnow() {
        return snow;
    }

    public void setSnow(PrecipitationVolumeDto snow) {
        this.snow = snow;
    }

    public String getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(String calculationTime) {
        this.calculationTime = calculationTime;
    }
}
