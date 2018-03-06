package com.my.test.domain.entities;

import android.icu.text.RelativeDateTimeFormatter;
import android.support.annotation.NonNull;

import java.util.Date;

public class ForecastUnit {
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

    private ForecastUnit() {}

    @NonNull
    public static Builder builder() {
        return new ForecastUnit().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setForecastTime(Date forecastTime) {
            ForecastUnit.this.forecastTime = forecastTime;
            return this;
        }

        public Builder setWeatherMain(String weatherMain) {
            ForecastUnit.this.weatherMain = weatherMain;
            return this;
        }

        public Builder setTemperature(Integer temperature) {
            ForecastUnit.this.temperature = temperature;
            return this;
        }

        public Builder setTempMin(Integer tempMin) {
            ForecastUnit.this.tempMin = tempMin;
            return this;
        }

        public Builder setTempMax(Integer tempMax) {
            ForecastUnit.this.tempMax = tempMax;
            return this;
        }

        public Builder setPressure(Integer pressure) {
            ForecastUnit.this.pressure = pressure;
            return this;
        }

        public Builder setHumidity(Integer humidity) {
            ForecastUnit.this.humidity = humidity;
            return this;
        }

        public Builder setWindSpeed(Integer windSpeed) {
            ForecastUnit.this.windSpeed = windSpeed;
            return this;
        }

        public Builder setWindDirectionInDegrees(Integer windDirectionInDegrees) {
            ForecastUnit.this.windDirectionInDegrees = windDirectionInDegrees;
            return this;
        }

        public Builder setCloudiness(Integer cloudiness) {
            ForecastUnit.this.cloudiness = cloudiness;
            return this;
        }

        public Builder setRainVolume(Integer rainVolume) {
            ForecastUnit.this.rainVolume = rainVolume;
            return this;
        }

        public Builder setSnowVolume(Integer snowVolume) {
            ForecastUnit.this.snowVolume = snowVolume;
            return this;
        }

        public ForecastUnit build() {
            return ForecastUnit.this;
        }
    }

    public Date getForecastTime() {
        return forecastTime;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public Integer getWindDirectionInDegrees() {
        return windDirectionInDegrees;
    }

    public Integer getCloudiness() {
        return cloudiness;
    }

    public Integer getRainVolume() {
        return rainVolume;
    }

    public Integer getSnowVolume() {
        return snowVolume;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }
}
