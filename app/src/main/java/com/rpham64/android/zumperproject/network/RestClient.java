package com.rpham64.android.zumperproject.network;

import com.rpham64.android.zumperproject.network.response.DetailsResponse;
import com.rpham64.android.zumperproject.network.response.RestaurantsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rudolf on 4/2/2017.
 */

public interface RestClient {

    @GET("nearbysearch/json?type=restaurant")
    Call<RestaurantsResponse> getRestaurants(@Query("pagetoken") String nextPageToken);

    @GET("nearbysearch/json?type=restaurant")
    Call<RestaurantsResponse> getRestaurants(@Query("pagetoken") String nextPageToken, @Query("location") String location);

    @GET("details/json?")
    Call<DetailsResponse> getRestaurantDetailsWithId(@Query("placeid") String placeId);

    @GET("details/json?")
    Call<DetailsResponse> getRestaurantDetailsWithReference(@Query("reference") String reference);
}
