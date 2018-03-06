package com.my.test.data.dto;

import com.google.gson.annotations.SerializedName;
import com.my.test.data.dto.internaldtos.CloudsDto;
import com.my.test.data.dto.internaldtos.CoordinateDto;
import com.my.test.data.dto.internaldtos.MainWeatherInfoDto;
import com.my.test.data.dto.internaldtos.PrecipitationVolumeDto;
import com.my.test.data.dto.internaldtos.SystemInfoDto;
import com.my.test.data.dto.internaldtos.WeatherConditionDto;
import com.my.test.data.dto.internaldtos.WindDto;

import java.util.List;

public class CurrentWeatherDto {

    @SerializedName("coord")
    private CoordinateDto coord;
    @SerializedName("weather")
    private List<WeatherConditionDto> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private MainWeatherInfoDto main;
    @SerializedName("wind")
    private WindDto wind;
    @SerializedName("clouds")
    private CloudsDto clouds;
    @SerializedName("rain")
    private PrecipitationVolumeDto rain;
    @SerializedName("snow")
    private PrecipitationVolumeDto snow;
    @SerializedName("dt")
    private Long dt;
    @SerializedName("sys")
    private SystemInfoDto sys;
    @SerializedName("id")
    private Integer cityId;
    @SerializedName("name")
    private String cityName;
    @SerializedName("cod")
    private Integer cod;

    public CoordinateDto getCoord() {
        return coord;
    }

    public void setCoord(CoordinateDto coord) {
        this.coord = coord;
    }

    public List<WeatherConditionDto> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherConditionDto> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainWeatherInfoDto getMain() {
        return main;
    }

    public void setMain(MainWeatherInfoDto main) {
        this.main = main;
    }

    public WindDto getWind() {
        return wind;
    }

    public void setWind(WindDto wind) {
        this.wind = wind;
    }

    public CloudsDto getClouds() {
        return clouds;
    }

    public void setClouds(CloudsDto clouds) {
        this.clouds = clouds;
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

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public SystemInfoDto getSys() {
        return sys;
    }

    public void setSys(SystemInfoDto sys) {
        this.sys = sys;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

}