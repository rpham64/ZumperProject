package com.rpham64.android.zumperproject.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class Restaurant {

    public final Geometry geometry;

    public final String icon;

    public final String id;

    @NonNull
    public final String name;

    @SerializedName("vicinity")
    @NonNull
    public final String address;

    public final List<Photo> photos;

    public final float rating;

    public final String url;

    public final List<Review> reviews;

    public final String website;

    public Restaurant(Geometry geometry,
                      String icon,
                      String id,
                      @NonNull String name,
                      @NonNull String address,
                      List<Photo> photos,
                      float rating,
                      String url,
                      List<Review> reviews,
                      String website) {
        this.geometry = geometry;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.address = address;
        this.photos = photos;
        this.rating = rating;
        this.url = url;
        this.reviews = reviews;
        this.website = website;
    }
}
