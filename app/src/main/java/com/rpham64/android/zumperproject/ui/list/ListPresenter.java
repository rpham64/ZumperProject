package com.rpham64.android.zumperproject.ui.list;

import android.util.Log;

import com.orhanobut.logger.Logger;
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

public class ListPresenter extends BasePresenter<ListPresenter.View> {

    private static final String TAG = ListPresenter.class.getName();

    private String nextPageToken;

    public ListPresenter() {
        nextPageToken = "";
    }

    /**
     * Fetches data from Google Places API
     */
    public void fetchRestaurants() {
        if (nextPageToken != null) {
            Call<RestaurantsResponse> call = getRestClient().getRestaurants(nextPageToken);
            call.enqueue(new Callback<RestaurantsResponse>() {
                @Override
                public void onResponse(Call<RestaurantsResponse> call, Response<RestaurantsResponse> response) {
                    nextPageToken = response.body().nextPageToken;

                    Log.i(TAG, "Token: " + nextPageToken);

                    getView().showRestaurants(response.body().restaurants);
                }

                @Override
                public void onFailure(Call<RestaurantsResponse> call, Throwable t) {
                    t.printStackTrace();
                    Logger.d(t);
                }
            });
        }
    }

    public interface View {
        void showRestaurants(List<Restaurant> restaurants);
    }
}
