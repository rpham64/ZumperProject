package com.rpham64.android.zumperproject.network;

import com.rpham64.android.zumperproject.network.response.RestaurantsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rudolf on 4/2/2017.
 */

public interface RestClient {

    @GET("nearbysearch/json?type=restaurant")
    Call<RestaurantsResponse> getRestaurants();
}
