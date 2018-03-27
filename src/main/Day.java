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

    public final int index;
    private  static DateFormatSymbols dateFormatSymbols
            = new DateFormatSymbols(Locale.ENGLISH);
    Day(int day) {
        index = day;
    }

    public static Day make(int index) throws IllegalArgumentException {
        for (Day d : Day.values()) {
            if (d.index == index)
                return d;
        }
        throw new IllegalArgumentException(
                String.format("Illegal day index: %d", index));
    }

    public static Day parse(String s) {
        final String[] shortWeekdayNames =
                dateFormatSymbols.getShortWeekdays();
        final String[] weekDayNames =
                dateFormatSymbols.getWeekdays();

        s = s.trim();
        for (Day day : Day.values()) {
            if (s.equalsIgnoreCase(shortWeekdayNames[day.index]) ||
                    s.equalsIgnoreCase(weekDayNames[day.index])) {
                return day;
            }
        }

        throw new IllegalArgumentException(
                String.format("%s is not a valid weekday string", s));
    }

    public String toString() {
        return dateFormatSymbols.getWeekdays()[index];
    }
}
