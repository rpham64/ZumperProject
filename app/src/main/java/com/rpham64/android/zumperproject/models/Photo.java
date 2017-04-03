package com.rpham64.android.zumperproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class Photo implements Parcelable {

    public final int height;

    public final int width;

    @SerializedName("photo_reference")
    public final String reference;

    public Photo(int height, int width, String reference) {
        this.height = height;
        this.width = width;
        this.reference = reference;
    }

    protected Photo(Parcel in) {
        height = in.readInt();
        width = in.readInt();
        reference = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(height);
        dest.writeInt(width);
        dest.writeString(reference);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
