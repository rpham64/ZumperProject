package com.rpham64.android.zumperproject.ui.utils;

import android.net.Uri;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class RestUtils {

    public static final String API_KEY = "AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk";
    public static final String PHOTO_URL = "https://maps.googleapis.com/maps/api/place/";
    public static final String MAX_WIDTH = "200";

    public static final String PHOTO_PATH = "photo";

    public static final String MAX_WIDTH_PARAM = "maxwidth";
    public static final String REFERENCE_PARAM = "photoreference";
    public static final String API_KEY_PARAM = "key";

    /**
     * Fetches a photo of a restaurant using Google's Place Details API
     *
     * @param reference
     * @return
     */
    public static String fetchPhotoUrl(String reference) {

        // TODO: Create database and store url, if time

        Uri photoUri = Uri.parse(PHOTO_URL)
                .buildUpon()
                .appendPath(PHOTO_PATH)
                .appendQueryParameter(MAX_WIDTH_PARAM, MAX_WIDTH)
                .appendQueryParameter(REFERENCE_PARAM, reference)
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();

        return photoUri.toString();
    }


}
