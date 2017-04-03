package com.rpham64.android.zumperproject.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rudolf on 4/2/2017.
 */
public class Restaurant implements Parcelable {

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

    protected Restaurant(Parcel in) {
        geometry = (Geometry) in.readValue(Geometry.class.getClassLoader());
        icon = in.readString();
        id = in.readString();
        name = in.readString();
        address = in.readString();
        if (in.readByte() == 0x01) {
            photos = new ArrayList<Photo>();
            in.readList(photos, Photo.class.getClassLoader());
        } else {
            photos = null;
        }
        rating = in.readFloat();
        url = in.readString();
        if (in.readByte() == 0x01) {
            reviews = new ArrayList<Review>();
            in.readList(reviews, Review.class.getClassLoader());
        } else {
            reviews = null;
        }
        website = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(geometry);
        dest.writeString(icon);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(address);
        if (photos == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(photos);
        }
        dest.writeFloat(rating);
        dest.writeString(url);
        if (reviews == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(reviews);
        }
        dest.writeString(website);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
}