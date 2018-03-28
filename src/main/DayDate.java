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

    public DayDate plusDays(int days) {
        return DayDateFactory.makeDate(toOrdinal() + days);
    }

    public DayDate plusMonths(final int months) {
        int thisMonthAsOrdinal = 12 * getYear() + getMonth().index - 1;
        int resultMonthAsOrdinal = thisMonthAsOrdinal + months;
        int resultYear = resultMonthAsOrdinal / 12;
        int resultMonth = Month.makeMonth(resultMonthAsOrdinal % 12 + 1).index;
        int lastDayOfResultMonth =
                DayDate.lastDayOfMonth(Month.makeMonth(resultMonth), resultYear);
        int resultDay = Math.min(getDayOfMonth(), lastDayOfResultMonth);
        return DayDateFactory.makeDate(resultDay, resultMonth, resultYear);

    }

    public DayDate plusYear(int years) {
        int resultYear = getYear() + years;
        int lastDayOfMonthInResultYear =
                lastDayOfMonth(getMonth(), resultYear);
        int resultDay =
                Math.min(getDayOfMonth(), lastDayOfMonthInResultYear);

        return DayDateFactory.makeDate(resultDay, getMonth(), resultYear);

    }

    public DayDate getPreviousDayOfWeek(final Day targetWeekday) {
        int offsetToTarget = targetWeekday.index - getDayOfWeek();
        if (offsetToTarget >= 0) {
            offsetToTarget -= 7;
        }
        return plusDays(offsetToTarget);
    }

    public DayDate getFollowingDayOfWeek(Day targetWeekday) {
        int offsetToTarget = targetWeekday.index - getDayOfWeek();
        if (offsetToTarget <= 0) {
            offsetToTarget += 7;
        }
        return plusDays(offsetToTarget);

    }

    public DayDate getNearestDayOfWeek(final Day targetDOW) {
        int offsetToThisWeeksTarget = targetDOW.index - getDayOfWeek();
        int offsetToFutureTarget = (offsetToThisWeeksTarget + 7) % 7;
        int offsetToPreviousTarget = offsetToFutureTarget - 7;
        if (offsetToFutureTarget > 3)
            return plusDays(offsetToPreviousTarget);
        else
            return plusDays(offsetToFutureTarget);
    }

    /**
     * Rolls the date forward to the last day of the month
     * @return a new serial date
     */
    public DayDate getEndOfCurrentMonth() {
        Month month = getMonth();
        int year = getYear();
        int lastDay = lastDayOfMonth(month, year);
        return DayDateFactory.makeDate(lastDay, month, year);
    }

    /**
     * Factory method that returns a instance of some concrete subclass of
     * {@link DayDate}
     * @param day the day (1-31).
     * @param month the month (1-12).
     * @param yyyy the year (in the range 1900 to 9999).
     * @return An instance of {@link DayDate}
     */
    public static DayDate makeDate(final int day, final int month,
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
    public static DayDate makeDate(final int day, final Month month,
                                   final int yyyy) {
        return DayDateFactory.makeDate(day, month, yyyy);
    }

    /**
     * Factory method that returns a instance of some concrete subclass of
     * {@link DayDate}
     * @param serial date A Java date object
     * @return An instance of {@link DayDate}
     */
    public static DayDate makeDate(final int serial) {
        return DayDateFactory.makeDate(serial);
    }

    /**
     * Factory method that returns an instance of a subclass of DayDate
     * @param date A Java date object
     * @return a instance of DayDate
     */
    public static DayDate makeDate(final java.util.Date date) {
        return DayDateFactory.makeDate(date);
    }

    /**
     * Returns the serial number for the date, where 1 January 1900 = 2 (this
     * corresponds, almost, to the numbering system used in Microsoft Excel for
     * Windows and Lotus 1-2-3).
     * @return the serial number for the date
     */
    public abstract int toOrdinal();

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
        return getDayOfMonth() + "-" + getMonth().toString()
                                + "-" + getYear();
    }

    /**
     * Returns the year (assume a valid range of 1900 to 9999).
     * @return the year.
     */
    public abstract int getYear();

    /**
     * Returns the month (January = 1, February = 2, March = 3).
     * @return the month of the year.
     */
    public abstract Month getMonth();

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
}
