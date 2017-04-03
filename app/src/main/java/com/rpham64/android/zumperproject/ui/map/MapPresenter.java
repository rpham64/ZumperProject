package com.rpham64.android.zumperproject.ui.map;

import android.util.Log;

import com.rpham64.android.zumperproject.models.Restaurant;
import com.rpham64.android.zumperproject.network.response.RestaurantsResponse;
import com.rpham64.android.zumperproject.ui.utils.BasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class MapPresenter extends BasePresenter<MapPresenter.View> {

    private static final String TAG = MapPresenter.class.getName();

    public MapPresenter() {
    }

    /**
     * Fetches data from Google Places API
     */
    public void fetch() {

        Call<RestaurantsResponse> call = getRestClient().getRestaurants();
        call.enqueue(new Callback<RestaurantsResponse>() {
            @Override
            public void onResponse(Call<RestaurantsResponse> call, Response<RestaurantsResponse> response) {
                getView().showRestaurants(response.body().restaurants);
            }

            @Override
            public void onFailure(Call<RestaurantsResponse> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "Something broke in ListPresenter#fetch", t);
            }
        });

    }

    public interface View {
        void showRestaurants(List<Restaurant> restaurants);
    }
}
