package com.rpham64.android.zumperproject.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rpham64.android.zumperproject.R;
import com.rpham64.android.zumperproject.models.Restaurant;
import com.rpham64.android.zumperproject.ui.utils.SFCoordinates;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class MapFragment extends Fragment implements MapPresenter.View, OnMapReadyCallback {

    private static final String TAG = MapFragment.class.getName();

    @BindView(R.id.view_map) MapView viewMap;

    private Unbinder mUnbinder;
    private MapPresenter mPresenter;

    private GoogleMap mMap;

    private List<Restaurant> mRestaurants;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new MapPresenter();
        mPresenter.fetch();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter.attachView(this);

        viewMap.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void onResume() {
        viewMap.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewMap.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        viewMap.onLowMemory();
    }

    @Override
    public void showRestaurants(List<Restaurant> restaurants) {
        mRestaurants = restaurants;

        // Only start map after retrieving list of restaurants
        viewMap.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        setupMap(map);

        for (Restaurant restaurant : mRestaurants) {

            double latitude = restaurant.geometry.location.latitude;
            double longitude = restaurant.geometry.location.longitude;

            MarkerOptions marker = new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title(restaurant.name)
                    .snippet("Address: " + restaurant.address)
                    .snippet("Contact: " + restaurant.website)
                    .snippet("Rating: " + restaurant.rating);

            map.addMarker(marker);
        }
    }

    private void setupMap(GoogleMap map) {
        double sfLatitude = Double.valueOf(SFCoordinates.LATITUDE);
        double sfLongitude = Double.valueOf(SFCoordinates.LONGITUDE);

        mMap = map;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(sfLatitude, sfLongitude)));
    }
}
