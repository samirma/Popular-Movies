package com.samir.popularmovies.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by samir on 7/5/16.
 */
public class DateUtil {

    final static DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Locale.US);

    final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    public static String userFriendlyDate(String release_date) {
        String result = null;
        try {
            final Date parse = SIMPLE_DATE_FORMAT.parse(release_date);
            result = df.format(parse);
        } catch (ParseException e) {
            Log.e(DateUtil.class.getSimpleName(), e.getMessage(), e);
        }
        return result;
    }
}
