package com.my.test.data.dto.internaldtos;

import com.google.gson.annotations.SerializedName;

public class CloudsDto {
    @SerializedName("all")
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
