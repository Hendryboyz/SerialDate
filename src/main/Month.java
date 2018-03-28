package main;

import java.text.DateFormatSymbols;
import java.util.Locale;

public enum Month {
    JANUARY(1), FEBRUARY(2), MARCH(3), // season 1
    APRIL(4), MAY(5), JUNE(6), // season 2
    JULY(7), AUGUST(8), SEPTEMBER(9), // season 3
    OCTOBER(10), NOVEMBER(11), DECEMBER(12); // season 4

    public final int index;
    private  static DateFormatSymbols dateFormatSymbols
            = new DateFormatSymbols(Locale.ENGLISH);

    Month(int index) {
        this.index = index;
    }

    public static Month makeMonth(int monthIndex) {
        for (Month m : Month.values()) {
            if (m.index == monthIndex)
                return m;
        }
        throw new IllegalArgumentException(
                "Invalid month index " + monthIndex);
    }

    public static Month parseMonth(String s) {
        s = s.trim();
        for (Month m : Month.values()) {
            if (m.matches(s)) {
                return m;
            }
        }

        try {
            return makeMonth(Integer.parseInt(s));
        }
        catch (NumberFormatException e) {}
        throw new IllegalArgumentException("Invalid month " + s);
    }

    private boolean matches(String s) {
        return s.equalsIgnoreCase(toString()) ||
                s.equalsIgnoreCase(toShortString());
    }

    public int quarter() {
        return 1 + (index - 1)/3;
    }

    public String toString() {
        return dateFormatSymbols.getMonths()[index - 1];
    }

    public String toShortString() {
        return dateFormatSymbols.getShortMonths()[index - 1];
    }
}