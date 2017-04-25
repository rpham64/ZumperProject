package com.rpham64.android.zumperproject.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rpham64.android.zumperproject.R;
import com.rpham64.android.zumperproject.models.Restaurant;
import com.rpham64.android.zumperproject.ui.utils.SFCoordinates;
import com.rpham64.android.zumperproject.ui.utils.adapters.CustomInfoWindowAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MapFragment extends Fragment implements MapPresenter.View, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final String TAG = MapFragment.class.getName();

    @BindView(R.id.view_map) MapView viewMap;

    private Unbinder mUnbinder;
    private MapPresenter mPresenter;

    private CustomInfoWindowAdapter mWindowAdapter;
    private GoogleMap mMap;

    // Coordinates
    private LatLng mCoordinates;
    private double mLatitude;
    private double mLongitude;
    private String mLocation;

    // For keeping track of marker's restaurant position in mRestaurants
    private HashMap<Marker, Restaurant> mHashMap = new HashMap<>();

    // List of Restaurants
    private List<Restaurant> mRestaurants;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLatitude = Double.valueOf(SFCoordinates.LATITUDE);
        mLongitude = Double.valueOf(SFCoordinates.LONGITUDE);

        mLocation = mLatitude + "," + mLongitude;

        mPresenter = new MapPresenter();
        mPresenter.fetchRestaurants(mLocation);

        mWindowAdapter = new CustomInfoWindowAdapter(getContext());
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
        if (viewMap != null) {
            try {
                viewMap.onDestroy();
            } catch (NullPointerException e) {
                Log.e(TAG, "Error while attempting MapView.onDestroy(), ignoring exception", e);
            }
        }
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (viewMap != null) {
            viewMap.onLowMemory();
        }
    }

    @Override
    public void showRestaurants(List<Restaurant> restaurants) {
        mRestaurants = restaurants;

        // Only start map after retrieving list of restaurants
        if (mMap == null) {
            viewMap.getMapAsync(this);
        } else {
            addMarkersToMap();
        }
    }

    @Override
    public void showDetails(Restaurant restaurant) {

    }

    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;

        addMarkersToMap();
        setupMap();
    }

    private void addMarkersToMap() {

        for (int i = 0; i < mRestaurants.size(); ++i) {

            Restaurant restaurant = mRestaurants.get(i);
            double latitude = restaurant.geometry.location.latitude;
            double longitude = restaurant.geometry.location.longitude;

            MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude, longitude));

            Marker marker = mMap.addMarker(markerOptions);
            mHashMap.put(marker, restaurant);
        }
    }

    private void setupMap() {
        double sfLatitude = Double.valueOf(SFCoordinates.LATITUDE);
        double sfLongitude = Double.valueOf(SFCoordinates.LONGITUDE);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(mWindowAdapter);
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                mCoordinates = mMap.getCameraPosition().target;

                mLatitude = mCoordinates.latitude;
                mLongitude = mCoordinates.longitude;

                mLocation = mLatitude + "," + mLongitude;

                // Call request with new coordinates
                mPresenter.fetchRestaurants(mLocation);
            }
        });

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(sfLatitude, sfLongitude), 15));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mWindowAdapter.setRestaurant(mHashMap.get(marker));
        return false;
    }
}
