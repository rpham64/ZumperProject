package com.rpham64.android.zumperproject.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rpham64.android.zumperproject.R;
import com.rpham64.android.zumperproject.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private static final String TAG = ListAdapter.class.getName();

    private Context mContext;
    private List<Restaurant> mRestaurants;

    public ListAdapter(Context context, List<Restaurant> restaurants) {
        mContext = context;
        mRestaurants = restaurants;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.v_list_holder, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Restaurant restaurant = mRestaurants.get(position);
        holder.bindHolder(restaurant);
    }

    @Override
    public int getItemCount() {
        if (mRestaurants != null) return mRestaurants.size();
        return 0;
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final String MAX_WIDTH_PARAM = "maxwidth";
        final String REFERENCE_PARAM = "photoreference";
        final String API_KEY_PARAM = "key";

        final String API_KEY = "AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk";
        final String PHOTO_URL = "https://maps.googleapis.com/maps/api/place/";
        final String MAX_WIDTH = "80";

        @BindView(R.id.text_restaurant_name) TextView txtName;
        @BindView(R.id.img_restaurant_photo) ImageView imgRestaurant;
        @BindView(R.id.text_restaurant_rating) TextView txtRating;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void bindHolder(Restaurant restaurant) {
            txtName.setText(restaurant.name);
            txtRating.setText(String.valueOf(restaurant.rating));

            // Reference for retrieving a list of photos from Google Places Photo API
            String photoReference = restaurant.photos.get(0).reference;

            Picasso.with(mContext)
                    .load(fetchPhotoUrl(photoReference))
                    .into(imgRestaurant);
        }

        public String fetchPhotoUrl(String reference) {

            Uri photoUri = Uri.parse(PHOTO_URL)
                    .buildUpon()
                    .appendPath("photo")
                    .appendQueryParameter(MAX_WIDTH_PARAM, MAX_WIDTH)
                    .appendQueryParameter(REFERENCE_PARAM, reference)
                    .appendQueryParameter(API_KEY_PARAM, API_KEY)
                    .build();

            Log.i(TAG, "Photo Url: " + photoUri.toString());

            return photoUri.toString();
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "You touched a list view!", Toast.LENGTH_SHORT).show();
        }
    }
}
