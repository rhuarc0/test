package com.my.test.data.rest;

import com.my.test.data.dto.CurrentWeatherDto;
import com.my.test.data.dto.CurrentWeatherListDto;
import com.my.test.data.dto.FindCityDto;
import com.my.test.data.dto.ForecastDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

// api.openweathermap.org/data/2.5/weather?id=2172797
public interface WebService {

    String URL = "http://api.openweathermap.org/data/2.5/";

    @GET("group?units=metric")
    Observable<CurrentWeatherListDto> fetchCurrentWeatherForCities(@Query("id") String ids);

    @GET("weather?units=metric")
    Observable<CurrentWeatherDto> fetchCurrentWeather(@Query("id") int id);

    @GET("forecast?units=metric")
    Observable<ForecastDto> fetchForecast(@Query("id") int id);

    @GET("find?type=like")
    Observable<FindCityDto> findCity(@Query("q") String city);
}
