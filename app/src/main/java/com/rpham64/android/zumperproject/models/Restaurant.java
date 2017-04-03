package com.rpham64.android.zumperproject.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class Restaurant {

    @NonNull
    public final String name;

    @SerializedName("vicinity")
    @NonNull
    public final String address;

    public final List<Photo> photos;

    public final int rating;

    public final String url;

    public final List<Review> reviews;

    public final String website;

    public Restaurant(String name,
                      String address,
                      List<Photo> photos,
                      int rating,
                      String url,
                      List<Review> reviews,
                      String website) {
        this.name = name;
        this.address = address;
        this.photos = photos;
        this.rating = rating;
        this.url = url;
        this.reviews = reviews;
        this.website = website;
    }
}
