package com.my.test.data.dto.internaldtos;

import com.google.gson.annotations.SerializedName;

public class WindDto {

    @SerializedName("deg")
    private Float deg;
    @SerializedName("speed")
    private Float speed;

    public Float getDeg() {
        return deg;
    }

    public void setDeg(Float deg) {
        this.deg = deg;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

}
