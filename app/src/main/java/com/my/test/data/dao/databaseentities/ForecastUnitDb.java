package com.my.test.data.dao.databaseentities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class ForecastUnitDb {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long cityId;

    private Date forecastTime;
    private String weatherMain;
    private Integer temperature;
    private Integer tempMin;
    private Integer tempMax;
    private Integer pressure;
    private Integer humidity;
    private Integer windSpeed;
    private Integer windDirectionInDegrees;
    private Integer cloudiness;
    private Integer rainVolume;
    private Integer snowVolume;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public Date getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(Date forecastTime) {
        this.forecastTime = forecastTime;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDirectionInDegrees() {
        return windDirectionInDegrees;
    }

    public void setWindDirectionInDegrees(Integer windDirectionInDegrees) {
        this.windDirectionInDegrees = windDirectionInDegrees;
    }

    public Integer getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(Integer cloudiness) {
        this.cloudiness = cloudiness;
    }

    public Integer getRainVolume() {
        return rainVolume;
    }

    public void setRainVolume(Integer rainVolume) {
        this.rainVolume = rainVolume;
    }

    public Integer getSnowVolume() {
        return snowVolume;
    }

    public void setSnowVolume(Integer snowVolume) {
        this.snowVolume = snowVolume;
    }
}
