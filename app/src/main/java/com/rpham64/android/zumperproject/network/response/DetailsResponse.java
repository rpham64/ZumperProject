package com.rpham64.android.zumperproject.network.response;

import com.google.gson.annotations.SerializedName;
import com.rpham64.android.zumperproject.models.Restaurant;

/**
 * Created by Rudolf on 4/3/2017.
 */

public class DetailsResponse {

    @SerializedName("result")
    public final Restaurant restaurant;

    public DetailsResponse(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
