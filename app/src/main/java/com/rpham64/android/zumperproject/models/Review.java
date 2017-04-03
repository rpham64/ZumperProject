package com.rpham64.android.zumperproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class Review {

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
}
