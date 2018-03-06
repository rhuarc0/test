package com.my.test.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FindCityDto {
    @SerializedName("message")
    private String message;

    @SerializedName("cod")
    private String cod;

    @SerializedName("count")
    private Integer count;

    @SerializedName("list")
    private List<CurrentWeatherDto> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CurrentWeatherDto> getList() {
        return list;
    }

    public void setList(List<CurrentWeatherDto> list) {
        this.list = list;
    }
}
