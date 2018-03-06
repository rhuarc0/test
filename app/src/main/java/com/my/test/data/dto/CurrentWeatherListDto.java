package com.my.test.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherListDto {
    @SerializedName("cnt")
    private Integer count;

    @SerializedName("list")
    private List<CurrentWeatherDto> list;

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
