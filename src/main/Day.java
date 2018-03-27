package main;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public enum Day {
    MONDAY(Calendar.MONDAY),
    TUESDAY(Calendar.TUESDAY),
    WEDNESDAY(Calendar.WEDNESDAY),
    THURSDAY(Calendar.THURSDAY),
    FRIDAY(Calendar.FRIDAY),
    SATURDAY(Calendar.SATURDAY),
    SUNDAY(Calendar.SUNDAY);

    private final int index;
    private  static DateFormatSymbols dateFormatSymbols
            = new DateFormatSymbols(Locale.ENGLISH);
    Day(int day) {
        index = day;
    }

    public static Day fromInt(int index) throws IllegalArgumentException {
        for (Day d : Day.values()) {
            if (d.index == index)
                return d;
        }
        throw new IllegalArgumentException(
                String.format("Illegal day index: %d", index));
    }

    public int toInt() {
        return index;
    }
}
