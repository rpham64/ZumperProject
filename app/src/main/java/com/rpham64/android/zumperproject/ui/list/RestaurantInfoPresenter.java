package com.rpham64.android.zumperproject.ui.list;

import com.orhanobut.logger.Logger;
import com.rpham64.android.zumperproject.models.Restaurant;
import com.rpham64.android.zumperproject.network.response.DetailsResponse;
import com.rpham64.android.zumperproject.ui.utils.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rudolf on 4/3/2017.
 */

public class RestaurantInfoPresenter extends BasePresenter<RestaurantInfoPresenter.View> {

    private static final String TAG = RestaurantInfoPresenter.class.getName();

    public RestaurantInfoPresenter() {
    }

    public void fetchDetails(String placeId) {

        Call<DetailsResponse> call = getRestClient().getRestaurantDetailsWithId(placeId);
        call.enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                getView().showInfo(response.body().restaurant);
            }

            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {
                t.printStackTrace();
                Logger.d(t);
            }
        });
    }

    public interface View {
        void showInfo(Restaurant restaurant);
    }
}
