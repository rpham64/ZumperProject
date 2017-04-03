package com.rpham64.android.zumperproject.ui.utils;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Rudolf on 4/3/2017.
 */

public class TimeUtils {

    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("MM-dd-yyyy", cal).toString();
        return date;
    }
}
