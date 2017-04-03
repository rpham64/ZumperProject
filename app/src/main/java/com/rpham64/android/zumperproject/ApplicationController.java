package com.rpham64.android.zumperproject;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.rpham64.android.zumperproject.network.RestClient;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class ApplicationController extends Application {

    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

    // Coordinates for San Francisco, California
    public static final String LATITUDE = "37.773972";
    public static final String LONGITUDE = "-122.431297";

    public static final String RADIUS = "500";

    private static ApplicationController sInstance;

    private OkHttpClient mOkHttpClient;
    private RestClient mRestClient;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Stetho.initializeWithDefaults(this);

        final String location = LATITUDE + "," + LONGITUDE;

        mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder()
                                .addQueryParameter("key", getString(R.string.google_places_key))
                                .addQueryParameter("location", location)
                                .addQueryParameter("radius", RADIUS)
                                .build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    public RestClient getRestClient() {

        if (mRestClient == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

            mRestClient = retrofit.create(RestClient.class);
        }

        return mRestClient;
    }

    public static ApplicationController getInstance() {
        return sInstance;
    }
}
