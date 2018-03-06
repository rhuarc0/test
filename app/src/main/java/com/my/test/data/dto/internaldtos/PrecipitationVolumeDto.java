package com.my.test.data.dto.internaldtos;

import com.google.gson.annotations.SerializedName;

public class PrecipitationVolumeDto {

    @SerializedName("3h")
    private Float volume;

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }
}
