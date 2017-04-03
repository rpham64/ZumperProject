package com.rpham64.android.zumperproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class Review implements Parcelable {

    public final List<AspectRating> aspects;

    @SerializedName("author_name")
    public final String authorName;

    @SerializedName("author_url")
    public final String authorUrl;

    public final int rating;

    public final String text;

    public final long time;

    public Review(List<AspectRating> aspects,
                  String authorName,
                  String authorUrl,
                  int rating,
                  String text,
                  long time) {
        this.aspects = aspects;
        this.authorName = authorName;
        this.authorUrl = authorUrl;
        this.rating = rating;
        this.text = text;
        this.time = time;
    }

    protected Review(Parcel in) {
        if (in.readByte() == 0x01) {
            aspects = new ArrayList<AspectRating>();
            in.readList(aspects, AspectRating.class.getClassLoader());
        } else {
            aspects = null;
        }
        authorName = in.readString();
        authorUrl = in.readString();
        rating = in.readInt();
        text = in.readString();
        time = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (aspects == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(aspects);
        }
        dest.writeString(authorName);
        dest.writeString(authorUrl);
        dest.writeInt(rating);
        dest.writeString(text);
        dest.writeLong(time);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}