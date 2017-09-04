package com.andredina.ghrepos.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * Aux to Date Format
 *
 * Created by André on 02/09/2017.
 */
public class DateUtils {

    private static final String DEFAULT_DATE_PATTERN  = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static SimpleDateFormat dateFormat;

    static{
        dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN, Locale.getDefault());
    }

    public static String format(Date date){
        if (date != null) return dateFormat.format(date);
        return null;
    }

    public static DateFormat getDefaultDateFormat(){
        return dateFormat;
    }

}
