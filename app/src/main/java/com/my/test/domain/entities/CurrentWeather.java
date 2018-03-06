package com.my.test.domain.entities;

import android.support.annotation.NonNull;

import java.util.Date;

public class CurrentWeather {
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
    private City city;

    private CurrentWeather() {}

    public static CurrentWeather createEmptyObject() {
        City city = City.builder().setCountry("").setName("").build();
        return builder().setCity(city).build();
    }

    public class Builder {
        private Builder() {}

        public Builder setWeatherMain(String weatherMain) {
            CurrentWeather.this.weatherMain = weatherMain;
            return this;
        }

        public Builder setTemperature(Integer temperature) {
            CurrentWeather.this.temperature = temperature;
            return this;
        }

        public Builder setPressure(Integer pressure) {
            CurrentWeather.this.pressure = pressure;
            return this;
        }

        public Builder setHumidity(Integer humidity) {
            CurrentWeather.this.humidity = humidity;
            return this;
        }

        public Builder setWindSpeed(Integer windSpeed) {
            CurrentWeather.this.windSpeed = windSpeed;
            return this;
        }

        public Builder setWindDirectionInDegrees(Integer windDirectionInDegrees) {
            CurrentWeather.this.windDirectionInDegrees = windDirectionInDegrees;
            return this;
        }

        public Builder setCloudiness(Integer cloudiness) {
            CurrentWeather.this.cloudiness = cloudiness;
            return this;
        }

        public Builder setRainVolume(Integer rainVolume) {
            CurrentWeather.this.rainVolume = rainVolume;
            return this;
        }

        public Builder setSnowVolume(Integer snowVolume) {
            CurrentWeather.this.snowVolume = snowVolume;
            return this;
        }

        public Builder setTimeOfDataCalculation(Date timeOfDataCalculation) {
            CurrentWeather.this.timeOfDataCalculation = timeOfDataCalculation;
            return this;
        }

        public Builder setCity(City city) {
            CurrentWeather.this.city = city;
            return this;
        }

        public CurrentWeather build() {
            return CurrentWeather.this;
        }
    }

    @NonNull
    public static Builder builder() {
        return new CurrentWeather().new Builder();
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public Integer getTemperature() {
        return temperature;
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

    public Date getTimeOfDataCalculation() {
        return timeOfDataCalculation;
    }

    public City getCity() {
        return city;
    }

}
