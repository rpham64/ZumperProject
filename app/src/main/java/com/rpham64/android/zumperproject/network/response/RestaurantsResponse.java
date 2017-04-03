package com.rpham64.android.zumperproject.network.response;

import com.google.gson.annotations.SerializedName;
import com.rpham64.android.zumperproject.models.Restaurant;

import java.util.List;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class RestaurantsResponse {

    public final String nextPageToken;

    @SerializedName("results")
    public final List<Restaurant> restaurants;

    public RestaurantsResponse(String nextPageToken, List<Restaurant> restaurants) {
        this.nextPageToken = nextPageToken;
        this.restaurants = restaurants;
    }
}
