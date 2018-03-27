/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2006, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jcommon/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * ---------------
 * SerialDate.java
 * ---------------
 * (C) Copyright 2001-2006, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: SerialDate.java,v 1.9 2011/10/17 20:08:22 mungady Exp $
 *
 * Changes (from 11-Oct-2001)
 */
package main;

import java.io.Serializable;
import java.text.*;
import java.util.*;
/**
 *  An abstract class that defines our requirements for manipulating dates,
 *  without tying down a particular implementation.
 *  <pre>
 *      Requirement 1 : match at least what Excel does for dates;
 *      Requirement 2 : the date represented by the class is immutable;
 *  </pre>
 *  <pre>
 *      Why not just use java.util.DayDate?  We will, when it makes sense.  At times,
 *      java.util.DayDate can be *too* precise - it represents an instant in time,
 *      accurate to 1/1000th of a second (with the date itself depending on the
 *      time-zone).  Sometimes we just want to represent a particular day (e.g. 21
 *      January 2015) without concerning ourselves about the time of day, or the
 *      time-zone, or anything else.  That's what we've defined DayDate for.
 *  </pre>
 *  You can call getInstance() to get a concrete subclass of DayDate,
 *  without worrying about the exact implementation.
 *
 * @author David Gilbert
 */
public abstract class DayDate implements Comparable,
                                    Serializable {


    public static final DateFormatSymbols
        DATE_FORMAT_SYMBOLS = new SimpleDateFormat("", Locale.ENGLISH)
            .getDateFormatSymbols();

    private static final int[] LAST_DAY_OF_MONTH =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static enum WeekInMonth {
        FIRST(1), SECOND(2), THIRD(3), FOURTH(4), LAST(0);
        public final int index;

        WeekInMonth(int index){
            this.index = index;
        }

        public int toInt() {
            return index;
        }
    }

    public static enum DateInterval {
        CLOSED(1), CLOSED_LEFT(2), CLOSED_RIGHT(2), OPEN(3);
        public final int index;

        DateInterval(int index){
            this.index = index;
        }
    }

    public static enum WeekdayRange {
        LAST(-1), NEAREST(0), NEXT(1);
        public final int index;

        WeekdayRange(int index){
            this.index = index;
        }
    }

    public static String[] getMonthNames() {
        return DATE_FORMAT_SYMBOLS.getMonths();
    }

    /**
     * Returns the quarter for the specified month
     * @param code the month code (1-12).
     * @return the quarter that the month belongs to
     * @throws java.lang.IllegalArgumentException
     */
    public static int monthCodeToQuarter(final int code) {

        switch (Month.makeMonth(code)) {
            case JANUARY:
            case FEBRUARY:
            case MARCH: return 1;
            case APRIL:
            case MAY:
            case JUNE: return 2;
            case JULY:
            case AUGUST:
            case SEPTEMBER: return 3;
            case OCTOBER:
            case NOVEMBER:
            case DECEMBER: return 4;
            default: return -1;
        }

    }

    /**
     * Returns a string representing the supplied month.
     *
     * The string returned is the long form of the month name taken from the
     * default locale
     * @param month the month
     * @return a string representing the supplied month
     */
    public static String monthCodeToString(final Month month) {
        return monthCodeToString(month, false);
    }

    /**
     * Returns a string representing the supplied month.
     *
     * The string returned is the long or short form of the month name taken
     * from the default locale
     * @param month the month
     * @param shortened shorten if <code>true</code> return the abbreviation of the month.
     * @return a string representing the supplied month.
     * @throws java.lang.IllegalArgumentException
     */
    public static String monthCodeToString(final Month month,
                                            final boolean shortened) {
        final String[] months;

        if (shortened) {
            months = DATE_FORMAT_SYMBOLS.getShortMonths();
        }
        else {
            months = DATE_FORMAT_SYMBOLS.getMonths();
        }

        return months[month.index - 1];

    }

    /**
     * Converts a string to a month code.
     * <P>
     *     This method will return one of the constants JANUARY, FEBRUARY, ...,
     *     DECEMBER that corresponds to the string. If the string is not recognised
     *     , this method returns -1
     * </P>
     *
     * @param s the string tor parse.
     * @return <code>-1</code> if the string is not parseable, the month of the
     *          year otherwise.
     */
    public static int stringToMonthCode(String s) {

        final String[] shortMonthNames = DATE_FORMAT_SYMBOLS.getShortMonths();
        final String[] monthNames = DATE_FORMAT_SYMBOLS.getMonths();

        int result = -1;
        s = s.trim();

        // first try parsing the string as an integer (1-12)...
        try {
            result = Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            // suppress
        }

        // now search through the names...
        if ((result < 1) || (result > 12)) {
            result = -1;
            for (int i = 0; i < monthNames.length; i++) {
                if (s.equalsIgnoreCase(shortMonthNames[i])) {
                    result = i + 1;
                }
                if (s.equalsIgnoreCase(monthNames[i])) {
                    result = i + 1;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Returns true if the supplied integer code represents a valid
     * week-in-the-month, and false otherwise.
     *
     * @param code the code being checked for validity
     * @return <code>true</code> if the supplied integer code represents a
     *          valid week-in-the-month.
     */
//    public static boolean isValidWeekInMonthCode(final int code) {
//
//        switch (code) {
//            case FIRST_WEEK_IN_MONTH:
//            case SECOND_WEEK_IN_MONTH:
//            case THIRD_WEEK_IN_MONTH:
//            case FOURTH_WEEK_IN_MONTH: return true;
//            default : return false;
//        }
//
//    }

    /**
     * Determines whether or not the specified year is a leap year.
     * @param yyyy the year (in the range of 1900 to 9999).
     * @return <code>true</code> if the specified year is a leap year
     */
    public static boolean isLeapYear(final int yyyy) {

        if ((yyyy % 4) != 0) {
            return false;
        }
        else if ((yyyy % 400) == 0) {
            return true;
        }
        else if ((yyyy % 100) == 0) {
            return false;
        }
        else {
            return true;
        }

    }

    /**
     * Returns the number of leap years from 1900 to the specified year INCLUSIVE
     * <P>
     *     Note that 1900 is not a leap year
     * </P>
     * @param yyyy
     * @return
     */
    public static int leapYearCount(final int yyyy) {

        final int leap4 = (yyyy - 1896) / 4;
        final int leap100 = (yyyy - 1800) / 100;
        final int leap400 = (yyyy - 1600) / 400;
        return leap4 - leap100 + leap400;

    }

    /**
     * Returns the number of the last day of the month, taking into account
     * leap year
     * @param month the month.
     * @param yyyy the year (in the range 1900 to 9999).
     * @return the number of the last day of the month.
     */
    public static int lastDayOfMonth(final Month month, final int yyyy) {

        final int result = LAST_DAY_OF_MONTH[month.index];
        if (month != Month.FEBRUARY) {
            return result;
        }
        else if (isLeapYear(yyyy)) {
            return result + 1;
        }
        else {
           return result;
        }
    }

    /**
     * Creates a new date by adding the specified number of days to the base date
     * @param days the number of days to add (can be negative)
     * @param base the base date
     * @return a new date
     */
    public static DayDate addDays(final int days, final DayDate base) {

        final int serialDayNumber = base.toSerial() + days;
        return DayDate.createInstance(serialDayNumber);
    }

    /**
     * Creates a new date by adding the specified number of months to the base
     * date.
     * <P>
     *     If the base date is close to the  end of the month, the day on the result
     *     may be adjusted slightly: 31 May + 1 month = 30 June.
     * </P>
     * @param months the number of months to add (can be negative).
     * @param base the base date
     * @return a new date
     */
    public static DayDate addMonths(final int months,
                                    final DayDate base) {

        final int yy = (12 * base.getYYYY() + base.getMonth() + months - 1) / 12;

        final int mm = (12 * base.getYYYY() + base.getMonth() + months - 1) % 12 + 1;

        final int dd = Math.min(
                base.getDayOfMonth(), DayDate.lastDayOfMonth(Month.makeMonth(mm), yy)
        );
        return DayDate.createInstance(dd, mm, yy);

    }

    /**
     * Creates a new date by adding the specified number of years to the base
     * date.
     * @param years years the number of years to add (can be negative).
     * @param base the base date
     * @return a new date
     */
    public static DayDate addYears(final int years, final DayDate base) {

        final int baseY = base.getYYYY();
        final int baseM = base.getMonth();
        final int baseD = base.getDayOfMonth();

        final int targetY = baseY + years;
        final int targetD = Math.min(
                baseD, DayDate.lastDayOfMonth(Month.makeMonth(baseM), targetY)
        );

        return DayDate.createInstance(targetD, baseM, targetY);

    }

    /**
     * Returns the latest date that falls on the specified day-of-the-week and
     * is BEFORE the base date
     * @param targetWeekday a code for the target day-of-the-week
     * @param base the base date.
     * @return the latest date that falls on the specified day-of-the-week and
     *          is BEFORE the base date
     */
    public static DayDate getPreviousDayOfWeek(final Day targetWeekday,
                                               final DayDate base) {
        // find the date...
        final int adjust;
        final int baseDOW = base.getDayOfWeek();
        if (baseDOW > targetWeekday.index) {
            adjust = Math.min(0, targetWeekday.index - baseDOW);
        }
        else {
            adjust = -7 + Math.max(0, targetWeekday.index - baseDOW);
        }

        return DayDate.addDays(adjust, base);
    }

    /**
     * Returns the earliest date that falls on the specified day-of-the-week
     * and is AFTER the base date.
     * @param targetWeekday a code for the target day-of-the-week
     * @param base the base date
     * @return the earliest date that falls on the specified day-of-the-week
     *          and is AFTER the base date.
     */
    public static DayDate getFollowingDayOfWeek(final Day targetWeekday,
                                                final DayDate base) {

        // find the date...
        final int adjust;
        final int baseDOW = base.getDayOfWeek();

        if (baseDOW >= targetWeekday.index) {
            adjust = 7 + Math.min(0, targetWeekday.index - baseDOW);
        }
        else {
            adjust = Math.max(0, targetWeekday.index - baseDOW);
        }

        return DayDate.addDays(adjust, base);

    }

    /**
     * Returns the date that falls on the specified day-of-the-week and is
     * CLOSET to the base date.
     * @param targetDOW a code for the target day-of-the-week
     * @param base the base date.
     * @return the date that falls on the specified day-of-the-week and is
     *          CLOSET to the base date.
     */
    public static DayDate getNearestDayOfWeek(final Day targetDOW,
                                              final DayDate base) {
        // find the date...
        int delta = targetDOW.index - base.getDayOfWeek();
        int positiveDelta = delta + 7;
        int adjust = positiveDelta % 7;
        if (adjust > 3)
            adjust -= 7;

        return DayDate.addDays(adjust, base);

    }

    /**
     * Rolls the date forward to the last day of the month
     * @param base the base date
     * @return a new serial date
     */
    public DayDate getEndOfCurrentMonth(final DayDate base) {
        final int last = DayDate.lastDayOfMonth(
                Month.makeMonth(base.getMonth()), base.getYYYY()
        );
        return DayDate.createInstance(last, base.getMonth(), base.getYYYY());
    }

//    /**
//     * Returns a string representing the supplied 'relative'
//     * <P>
//     *     Need to find ad better approach
//     * </P>
//     * @param relative a constant representing the 'relative'
//     * @return a string representing the supplied 'relative'
//     */
//    public static String relativeToString(final int relative) {
//
//        switch (relative) {
//            case DayDate.PRECEDING : return "Preceding";
//            case DayDate.NEAREST: return "Nearest";
//            case DayDate.FOLLOWING : return "Following";
//            default: return "ERROR: Relative To String";
//        }
//
//    }

    /**
     * Factory method that returns a instance of some concrete subclass of
     * {@link DayDate}
     * @param day the day (1-31).
     * @param month the month (1-12).
     * @param yyyy the year (in the range 1900 to 9999).
     * @return An instance of {@link DayDate}
     */
    public static DayDate createInstance(final int day, final int month,
                                         final int yyyy) {
        return DayDateFactory.makeDate(day, month, yyyy);
    }

    /**
     * Factory method that returns a instance of some concrete subclass of
     * {@link DayDate}
     * @param day the day (1-31).
     * @param month An instance of {@link Month} (JANUARY-DECEMBER).
     * @param yyyy the year (in the range 1900 to 9999).
     * @return An instance of {@link DayDate}
     */
    public static DayDate createInstance(final int day, final Month month,
                                         final int yyyy) {
        return DayDateFactory.makeDate(day, month, yyyy);
    }

    /**
     * Factory method that returns a instance of some concrete subclass of
     * {@link DayDate}
     * @param serial date A Java date object
     * @return An instance of {@link DayDate}
     */
    public static DayDate createInstance(final int serial) {
        return DayDateFactory.makeDate(serial);
    }

    /**
     * Factory method that returns an instance of a subclass of DayDate
     * @param date A Java date object
     * @return a instance of DayDate
     */
    public static DayDate createInstance(final java.util.Date date) {
        return DayDateFactory.makeDate(date);
    }

    /**
     * Returns the serial number for the date, where 1 January 1900 = 2 (this
     * corresponds, almost, to the numbering system used in Microsoft Excel for
     * Windows and Lotus 1-2-3).
     * @return the serial number for the date
     */
    public abstract int toSerial();

    /**
     * Returns a java.util.DayDate. Since java.util.DayDate has more precision than
     * DayDate, we need to define a convention for the 'time of day'.
     * @return this as <code>java.util.DayDate</code>
     */
    public abstract java.util.Date toDate();

    /**
     * Converts the date to a string.
     * @return a string representation of the date.
     */
    public String toString() {
        return getDayOfMonth() + "-" + DayDate.monthCodeToString(Month.makeMonth(getMonth()))
                                + "-" + getYYYY();
    }

    /**
     * Returns the year (assume a valid range of 1900 to 9999).
     * @return the year.
     */
    public abstract int getYYYY();

    /**
     * Returns the month (January = 1, February = 2, March = 3).
     * @return the month of the year.
     */
    public abstract int getMonth();

    /**
     * Returns the day of the month.
     * @return the day of the month.
     */
    public abstract int getDayOfMonth();

    /**
     * Returns the day of the week.
     * @return the day of the week.
     */
    public abstract int getDayOfWeek();

    /**
     * Returns the difference (in days) between this date and the specified
     * 'other' date
     * <P>
     *     The result is positive if this date is after the 'other' date and
     *     negative if it is before the 'other' date.
     * </P>
     * @param other the date being compared to.
     * @return the difference between this and the other date.
     */
    public abstract int compare(DayDate other);

    /**
     * Returns true if this Serial represents the same date as the specified DayDate
     * @param other the date being compared to
     * @return <code>true</code> if this DayDate represents the same date as the specified
     *      DayDate.
     */
    public abstract boolean isOn(DayDate other);

    /**
     * Returns true if this DayDate represents an earlier date compared to
     * the specified DayDate.
     * @param other other The dat being compared to.
     * @return <code>true</code> if this DayDate represents an earlier adate
     *          compared to the specified DayDate.
     */
    public abstract boolean isBefore(DayDate other);

    /**
     * Returns true if this DayDate represents the same date as the
     * specified DayDate.
     * @param other the date being compared to.
     * @return <code>true</code> if this DayDate represents the same date
     *          as the specified DayDate.
     */
    public abstract boolean isOnOrBefore(DayDate other);

    /**
     * Returns true if this DayDate represents the same date as the specified
     * DayDate
     * @param other the date being compared to
     * @return <code>true</code> if this DayDate represents the same date
     *          as the specified DayDate
     */
    public abstract boolean isAfter(DayDate other);

    /**
     * Returns true if this DayDate represents the same date as the
     * specified DayDate
     * @param other the date being compared to
     * @return <code>true</code> if this DayDate represents the same date
     *          as the specified DayDate
     */
    public abstract boolean isOnOrAfter(DayDate other);

    /**
     * Returns <code>true</code> if this {@link DayDate} represents the same date as the
     * specified range (INCLUSIVE). The date order of d1 and d2 is not important.
     * @param d1 a boundary date for the range.
     * @param d2 the other boundary date for the range
     * @return A boolean
     */
    public abstract boolean isInRange(DayDate d1, DayDate d2);

    /**
     * Returns <code>true</code> if this {@link DayDate} represents the same date as the
     * specified range (caller specifies whether or not the end-points are included). The
     * date order of d1 and d2 is not important.
     * @param d1 a boundary date for the range.
     * @param d2 the other boundary date for the range.
     * @param include a code that controls whether or not the start and end
     *                dates are included in the range.
     * @return A boolean
     */
    public abstract boolean isInRange(DayDate d1, DayDate d2,
                                      DateInterval include);

    /**
     * Returns the latest date that falls on the specified day-of-the-week and
     * is BEFORE this date
     * @param targetDOW a code for the target day-of-the-week
     * @return the latest date that falls on the specified day-of-the-week and
     *          is BEFORE this date
     */
    public DayDate getPreviousDayOfWeek(final int targetDOW) {
        return getPreviousDayOfWeek(Day.make(targetDOW), this);
    }

    /**
     * Returns the earliest date that falls on the specified day-of-the-week
     * and is AFTER this date.
     * @param targetDOW a code for the target day-of-the-week.
     * @return the earliest date that falls on the specified day-of-the-week
     *          and is AFTER this date.
     */
    public DayDate getFollowingDayOfWeek(final int targetDOW) {
        return getFollowingDayOfWeek(Day.make(targetDOW), this);
    }

    /**
     * Returns the nearest date that falls on the specified day-of-the-week.
     *
     * @param targetDOW a code for the target day-of-the-week.
     * @return the nearest date that falls on the specified day-of-the-week
     */
    public DayDate getNearestDayOfWeek(final int targetDOW) {
        return getNearestDayOfWeek(Day.make(targetDOW), this);
    }
}