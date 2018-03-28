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

    /**
     * Determines whether or not the specified year is a leap year.
     * @param yyyy the year (in the range of 1900 to 9999).
     * @return <code>true</code> if the specified year is a leap year
     */
    public static boolean isLeapYear(final int yyyy) {
        boolean fourth = (yyyy % 4) == 0;
        boolean hundredth = (yyyy % 100) == 0;
        boolean fourHundredth = (yyyy % 400) == 0;
        return fourth && (!hundredth || fourHundredth);
    }

    /**
     * Returns the number of the last day of the month, taking into account
     * leap year
     * @param month the month.
     * @param yyyy the year (in the range 1900 to 9999).
     * @return the number of the last day of the month.
     */
    public static int lastDayOfMonth(Month month, int yyyy) {
        if (month == Month.FEBRUARY && isLeapYear(yyyy)) {
            return month.lastDay() + 1;
        }
        else {
            return month.lastDay();
        }
    }
}
