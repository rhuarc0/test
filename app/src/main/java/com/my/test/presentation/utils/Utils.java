package com.my.test.presentation.utils;

import java.util.Date;

public class Utils {

    public static boolean isDateDifferenceMoreThan(Date date1, Date date2, long difference) {
        long diff = Math.abs(date1.getTime() - date2.getTime());
        return diff > difference;
    }

    public static String determineWindDirection(Integer windAzimuth) {
        if (windAzimuth == null)
            return "";

        if (between(windAzimuth, 45 - 22, 45 + 23))
            return "NE";
        if (between(windAzimuth, 90 - 22, 90 + 23))
            return "E";
        if (between(windAzimuth, 135 - 22, 135 + 23))
            return "SE";
        if (between(windAzimuth, 180 - 22, 180 + 23))
            return "S";
        if (between(windAzimuth, 225 - 22, 225 + 23))
            return "SW";
        if (between(windAzimuth, 270 - 22, 270 + 23))
            return "W";
        if (between(windAzimuth, 315 - 22, 315 + 23))
            return "NW";

        return "N";
    }

    public static boolean between(int x, int min, int max)
    {
        return x >= min && x < max;
    }

}
