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
 *
 *  Use DayDateFactory.makeDate to create an instance.
 *
 * @author David Gilbert
 * @author Henry Chou
 */
public abstract class DayDate implements Comparable,
                                    Serializable {
    public abstract int getOrdinalDay();
    public abstract int getYear();
    public abstract Month getMonth();
    public abstract int getDayOfMonth();

    protected abstract Day getDayOfWeekForOrdinalZero();

    public DayDate plusDays(int days) {
        return DayDateFactory.makeDate(getOrdinalDay() + days);
    }

    public DayDate plusMonths(int months) {
        int thsMonthAsOrdinal = getMonth().toInt() - 1;
        int thisMonthAndYearAsOrdinal = 12 * getYear() + thsMonthAsOrdinal;
        int resultMonthAndYearAsOrdinal = thisMonthAndYearAsOrdinal + months;
        int resultYear = resultMonthAndYearAsOrdinal / 12;
        Month resultMonth = Month.fromInt(resultMonthAndYearAsOrdinal % 12 + 1);
        int resultDay = correctLatDayOfMonth(getDayOfMonth(), resultMonth, resultYear);
        return DayDateFactory.makeDate(resultDay, resultMonth, resultYear);

    }

    public DayDate plusYear(int years) {
        int resultYear = getYear() + years;
        int resultDay = correctLatDayOfMonth(getDayOfMonth(), getMonth(), resultYear);
        return DayDateFactory.makeDate(resultDay, getMonth(), resultYear);

    }

    private int correctLatDayOfMonth(int day, Month month, int year) {
        int lastDayOfMonth = DateUtil.lastDayOfMonth(month, year);
        if (day > lastDayOfMonth)
            day = lastDayOfMonth;
        return day;
    }

    public DayDate getPreviousDayOfWeek(Day targetWeekday) {
        int offsetToTarget = targetWeekday.toInt() - getDayOfWeek().toInt();
        if (offsetToTarget >= 0) {
            offsetToTarget -= 7;
        }
        return plusDays(offsetToTarget);
    }

    public DayDate getFollowingDayOfWeek(Day targetWeekday) {
        int offsetToTarget = targetWeekday.toInt() - getDayOfWeek().toInt();
        if (offsetToTarget <= 0) {
            offsetToTarget += 7;
        }
        return plusDays(offsetToTarget);

    }

    public DayDate getNearestDayOfWeek(Day targetDOW) {
        int offsetToThisWeeksTarget = targetDOW.toInt() - getDayOfWeek().toInt();
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
        int lastDay = DateUtil.lastDayOfMonth(month, year);
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
     * Returns a java.util.DayDate. Since java.util.DayDate has more precision than
     * DayDate, we need to define a convention for the 'time of day'.
     * @return this as <code>java.util.DayDate</code>
     */
    public java.util.Date toDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(getYear(), getMonth().toInt() - 1, getDayOfMonth(), 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * Converts the date to a string.
     * @return a string representation of the date.
     */
    public String toString() {
        return getDayOfMonth() + "-" + getMonth().toString()
                                + "-" + getYear();
    }

    public Day getDayOfWeek() {
        // todo some logic error ?
        Day startingDay = getDayOfWeekForOrdinalZero();
        int startingOffset = startingDay.toInt() - Day.SUNDAY.toInt(); //6
        return Day.fromInt((getOrdinalDay() + 6) % 7 + 1);
//        return Day.fromInt((getOrdinalDay() + startingOffset) % 7 + 1);
    }

    public int daySince(DayDate other) {
        return getOrdinalDay() - other.getOrdinalDay();
    }

    public boolean isOn(DayDate other) {
        return (getOrdinalDay() == other.getOrdinalDay());
    }

    public boolean isBefore(DayDate other) {
        return (getOrdinalDay() < other.getOrdinalDay());
    }

    public boolean isOnOrBefore(DayDate other) {
        return (getOrdinalDay() <= other.getOrdinalDay());
    }

    public boolean isAfter(DayDate other) {
        return (getOrdinalDay() > other.getOrdinalDay());
    }

    public boolean isOnOrAfter(DayDate other) {
        return (getOrdinalDay() >= other.getOrdinalDay());
    }

    public boolean isInRange(DayDate d1, DayDate d2) {
        return isInRange(d1, d2, DateInterval.OPEN);
    }

    public boolean isInRange(DayDate d1, DayDate d2, DateInterval include) {
        int left = Math.min(d1.getOrdinalDay(), d2.getOrdinalDay());
        int right = Math.max(d1.getOrdinalDay(), d2.getOrdinalDay());
        int d = getOrdinalDay();
        return include.isIn(d, left, right);
    }
}
