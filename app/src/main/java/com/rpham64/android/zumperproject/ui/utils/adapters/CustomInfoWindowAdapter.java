package com.rpham64.android.zumperproject.ui.utils.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.rpham64.android.zumperproject.R;
import com.rpham64.android.zumperproject.models.Restaurant;
import com.rpham64.android.zumperproject.models.Review;
import com.rpham64.android.zumperproject.ui.utils.RestUtils;
import com.rpham64.android.zumperproject.ui.utils.TimeUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rudolf on 4/3/2017.
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = CustomInfoWindowAdapter.class.getName();

    @BindView(R.id.content_photo) ImageView imgPhoto;
    @BindView(R.id.content_name) TextView txtName;
    @BindView(R.id.content_address) TextView txtAddress;
    @BindView(R.id.content_contact_info) TextView txtContact;
    @BindView(R.id.content_rating) RatingBar barRating;
    @BindView(R.id.review_author) TextView txtReviewAuthor;
    @BindView(R.id.review_time) TextView txtReviewTime;
    @BindView(R.id.review_rating) RatingBar barRatingReview;
    @BindView(R.id.review_text) TextView txtReviewContent;

    private Context mContext;
    private final View mContents;

    private Restaurant mRestaurant;

    private boolean isImageShowingFirstTime;

    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
        mContents = ((Activity) context).getLayoutInflater().inflate(R.layout.v_marker_contents, null);
        ButterKnife.bind(this, mContents);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        render(marker);
        return mContents;
    }

    private void render(Marker marker) {

        String photoReference = mRestaurant.photos.get(0).reference;
        String photoUrl = RestUtils.fetchPhotoUrl(photoReference);

        if (isImageShowingFirstTime) {

            // Refresh info window
            // Bug fix: Image doesn't show on first click but shows on second
            isImageShowingFirstTime = false;
            Picasso.with(mContext).load(photoUrl).into(imgPhoto, new InfoWindowRefresher(marker));

        } else {
            Picasso.with(mContext).load(photoUrl).into(imgPhoto);
        }

        txtName.setText(mRestaurant.name);
        txtAddress.setText(mRestaurant.address);
        txtContact.setText(mRestaurant.phoneNumber);
        barRating.setRating(mRestaurant.rating);

        // TODO: Create list, if time
        if (mRestaurant.reviews != null) {
            Review review = mRestaurant.reviews.get(0);

            txtReviewAuthor.setText(review.authorName);
            txtReviewTime.setText(TimeUtils.getDate(review.time));
            barRatingReview.setRating(review.rating);
            txtReviewContent.setText(review.text);
        }
    }

    public void setRestaurant(Restaurant restaurant) {
        mRestaurant = restaurant;
        isImageShowingFirstTime = true;
    }

    private class InfoWindowRefresher implements Callback {
        private Marker markerToRefresh;

        private InfoWindowRefresher(Marker markerToRefresh) {
            this.markerToRefresh = markerToRefresh;
        }

        @Override
        public void onSuccess() {
            markerToRefresh.showInfoWindow();
        }

        @Override
        public void onError() {
        }
    }
}
