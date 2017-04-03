package com.rpham64.android.zumperproject.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rpham64.android.zumperproject.R;
import com.rpham64.android.zumperproject.models.Restaurant;
import com.rpham64.android.zumperproject.models.Review;
import com.rpham64.android.zumperproject.ui.utils.RestUtils;
import com.rpham64.android.zumperproject.ui.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity that displays a selected restaurant's information:
 * <p>
 * 1) Name
 * 2) Formatted address
 * 3) A way to contact the restaurant (if available)
 * 4) At least one photo (if available)
 * 5) Rating (if available)
 * 6) List of reviews (if available)
 * <p>
 * Created by Rudolf on 4/2/2017.
 */

public class RestaurantInfoActivity extends AppCompatActivity implements RestaurantInfoPresenter.View {

    private static final String TAG = RestaurantInfoActivity.class.getName();

    public interface Extras {
        String RESTAURANT = "RestaurantInfoActivity.restaurant";
    }

    @BindView(R.id.img_restaurant_info_photo) ImageView imgPhoto;
    @BindView(R.id.text_restaurant_info_name) TextView txtName;
    @BindView(R.id.text_restaurant_info_address) TextView txtAddress;
    @BindView(R.id.text_restaurant_info_contact) TextView txtContact;
    @BindView(R.id.text_restaurant_info_rating) RatingBar barRating;
    @BindView(R.id.review_author) TextView txtReviewAuthor;
    @BindView(R.id.review_time) TextView txtReviewTime;
    @BindView(R.id.review_rating) RatingBar barRatingReview;
    @BindView(R.id.review_text) TextView txtReview;

    private RestaurantInfoPresenter mPresenter;

    private Restaurant mRestaurant;

    public static void startRestaurantInfoActivity(Context context, Restaurant restaurant) {
        Intent intent = new Intent(context, RestaurantInfoActivity.class);
        intent.putExtra(Extras.RESTAURANT, restaurant);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        ButterKnife.bind(this);

        mPresenter = new RestaurantInfoPresenter();
        mPresenter.attachView(this);

        if (getIntent() != null) {
            mRestaurant = getIntent().getParcelableExtra(Extras.RESTAURANT);
        }

        mPresenter.fetchDetails(mRestaurant.placeId);
    }

    @Override
    public void showInfo(Restaurant restaurant) {
        String photoReference = restaurant.photos.get(0).reference;
        String photoUrl = RestUtils.fetchPhotoUrl(photoReference);

        Picasso.with(this)
                .load(photoUrl)
                .into(imgPhoto);

        txtName.setText(restaurant.name);
        txtAddress.setText(restaurant.address);
        txtContact.setText(restaurant.phoneNumber);
        barRating.setRating(restaurant.rating);

        // TODO: Create list of reviews, if time
        if (restaurant.reviews != null) {
            Review review = restaurant.reviews.get(0);

            txtReviewAuthor.setText(review.authorName);
            txtReviewTime.setText(TimeUtils.getDate(review.time));
            barRatingReview.setRating(review.rating);
            txtReview.setText(review.text);
        }
    }
}
