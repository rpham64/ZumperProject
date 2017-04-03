package com.rpham64.android.zumperproject.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class Location {

    @SerializedName("lat")
    public final float latitude;

    @SerializedName("lng")
    public final float longitude;

    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
