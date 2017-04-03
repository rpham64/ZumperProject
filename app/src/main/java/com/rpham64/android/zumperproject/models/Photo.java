package com.rpham64.android.zumperproject.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class Photo {

    public final int height;

    public final int width;

    @SerializedName("photo_reference")
    public final String reference;

    public Photo(int height, int width, String reference) {
        this.height = height;
        this.width = width;
        this.reference = reference;
    }
}
