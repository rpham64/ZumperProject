package com.rpham64.android.zumperproject.ui.utils.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.rpham64.android.zumperproject.R;
import com.rpham64.android.zumperproject.models.Restaurant;
import com.rpham64.android.zumperproject.ui.list.RestaurantInfoActivity;
import com.rpham64.android.zumperproject.ui.utils.RestUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class ListAdapter extends UltimateViewAdapter<ListAdapter.ListViewHolder> {

    private static final String TAG = ListAdapter.class.getName();

    private Context mContext;
    private List<Restaurant> mRestaurants;

    public ListAdapter(Context context, List<Restaurant> restaurants) {
        mContext = context;
        mRestaurants = restaurants;
    }

    @Override
    public ListViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public ListViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.v_list_holder, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Restaurant restaurant = mRestaurants.get(position);
        holder.bindHolder(restaurant);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getAdapterItemCount() {
        if (mRestaurants != null) return mRestaurants.size();
        return 0;
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.mRestaurants = restaurants;
        notifyDataSetChanged();
    }

    public void addRestaurants(List<Restaurant> restaurants) {
        mRestaurants.addAll(restaurants);
        notifyDataSetChanged();
    }

    class ListViewHolder extends UltimateRecyclerviewViewHolder implements View.OnClickListener{

        @BindView(R.id.text_restaurant_name) TextView txtName;
        @BindView(R.id.img_restaurant_photo) ImageView imgRestaurant;
        @BindView(R.id.text_restaurant_rating) RatingBar txtRating;

        private Restaurant mRestaurant;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void bindHolder(Restaurant restaurant) {

            mRestaurant = restaurant;

            txtName.setText(restaurant.name);
            txtRating.setRating(restaurant.rating);

            // Reference for retrieving a list of photos from Google Places Photo API
//            String photoReference = restaurant.photos.get(0).reference;
//            String photoUrl = RestUtils.fetchPhotoUrl(photoReference);
//
//            Picasso.with(mContext)
//                    .load(photoUrl)
//                    .into(imgRestaurant);
        }

        @Override
        public void onClick(View v) {
            RestaurantInfoActivity.startRestaurantInfoActivity(mContext, mRestaurant);
        }
    }
}
