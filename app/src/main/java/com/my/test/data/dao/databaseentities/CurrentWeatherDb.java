package com.my.test.data.dao.databaseentities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity( indices = {@Index(value = {"id"}), @Index(value = {"cityId"})},
        foreignKeys = @ForeignKey(entity = CityDb.class,
        parentColumns = "id",
        childColumns = "cityId")
)
public class CurrentWeatherDb {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long weatherId;

    private String weatherMain;
    private Integer temperature;
    private Integer pressure;
    private Integer humidity;
    private Integer windSpeed;
    private Integer windDirectionInDegrees;
    private Integer cloudiness;
    private Integer rainVolume;
    private Integer snowVolume;
    private Date timeOfDataCalculation;

    private Integer cityId;

    public void setWeatherId(long id) {
        this.weatherId = id;
    }

    public long getWeatherId() {
        return weatherId;
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

    public Date getTimeOfDataCalculation() {
        return timeOfDataCalculation;
    }

    public void setTimeOfDataCalculation(Date timeOfDataCalculation) {
        this.timeOfDataCalculation = timeOfDataCalculation;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

}

