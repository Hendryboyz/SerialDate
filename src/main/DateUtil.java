package main;

import java.text.*;
import java.util.Locale;

public class DateUtil {
    public static final DateFormatSymbols
        DATE_FORMAT_SYMBOLS = new SimpleDateFormat("",Locale.ENGLISH)
            .getDateFormatSymbols();

    public static String[] getMonthNames() {
        return DATE_FORMAT_SYMBOLS.getMonths();
    }

    public static boolean isLeapYear(final int yyyy) {
        boolean fourth = (yyyy % 4) == 0;
        boolean hundredth = (yyyy % 100) == 0;
        boolean fourHundredth = (yyyy % 400) == 0;
        return fourth && (!hundredth || fourHundredth);
    }

    public static int lastDayOfMonth(Month month, int yyyy) {
        if (month == Month.FEBRUARY && isLeapYear(yyyy)) {
            return month.lastDay() + 1;
        }
        else {
            return month.lastDay();
        }
    }

    public static int leapYearCount(int year) {
        final int leap4 = (year - 1896) / 4;
        final int leap100 = (year - 1800) / 100;
        final int leap400 = (year - 1600) / 400;
        return leap4 - leap100 + leap400;
    }
}
