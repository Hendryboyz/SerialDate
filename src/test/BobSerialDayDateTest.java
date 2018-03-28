package test;

import junit.framework.TestCase;
import main.DayDate;
import main.Month;
import main.SpreadsheetDayDate;

import java.util.GregorianCalendar;

import static main.Day.*;
import static main.Month.*;
import static main.DayDate.*;

public class BobSerialDayDateTest extends TestCase {

    public void testStringToWeekdayCode() throws Exception {

        try {
            parseDay("Hello");
            fail("Invalid Weekday Code should throw exception.");
        } catch (IllegalArgumentException e) {

        }

        assertEquals(MONDAY, parseDay("Monday"));
        assertEquals(MONDAY, parseDay("Mon"));
        assertEquals(MONDAY, parseDay("monday"));
        assertEquals(MONDAY, parseDay("MONDAY"));
        assertEquals(MONDAY, parseDay("mon"));

        assertEquals(TUESDAY, parseDay("Tuesday"));
        assertEquals(TUESDAY, parseDay("Tue"));
        assertEquals(TUESDAY, parseDay("tuesday"));
        assertEquals(TUESDAY, parseDay("TUESDAY"));
        assertEquals(TUESDAY, parseDay("tue"));
//        assertEquals(Day.TUESDAY, parseDay("tues"));

        assertEquals(WEDNESDAY, parseDay("Wednesday"));
        assertEquals(WEDNESDAY, parseDay("Wed"));
        assertEquals(WEDNESDAY, parseDay("wednesday"));
        assertEquals(WEDNESDAY, parseDay("WEDNESDAY"));
        assertEquals(WEDNESDAY, parseDay("wed"));

        assertEquals(THURSDAY, parseDay("Thursday"));
        assertEquals(THURSDAY, parseDay("Thu"));
        assertEquals(THURSDAY, parseDay("thursday"));
        assertEquals(THURSDAY, parseDay("THURSDAY"));
        assertEquals(THURSDAY, parseDay("thu"));
//        assertEquals(Day.THURSDAY, parseDay("thurs"));

        assertEquals(FRIDAY, parseDay("Friday"));
        assertEquals(FRIDAY, parseDay("Fri"));
        assertEquals(FRIDAY, parseDay("friday"));
        assertEquals(FRIDAY, parseDay("FRIDAY"));
        assertEquals(FRIDAY, parseDay("fri"));

        assertEquals(SATURDAY, parseDay("Saturday"));
        assertEquals(SATURDAY, parseDay("Sat"));
        assertEquals(SATURDAY, parseDay("saturday"));
        assertEquals(SATURDAY, parseDay("SATURDAY"));
        assertEquals(SATURDAY, parseDay("sat"));

        assertEquals(SUNDAY, parseDay("Sunday"));
        assertEquals(SUNDAY, parseDay("Sun"));
        assertEquals(SUNDAY, parseDay("sunday"));
        assertEquals(SUNDAY, parseDay("SUNDAY"));
        assertEquals(SUNDAY, parseDay("sun"));
    }

    public void testWeekdayCodeToString() throws Exception {
        assertEquals("Sunday", SUNDAY.toString());
        assertEquals("Monday", MONDAY.toString());
        assertEquals("Tuesday", TUESDAY.toString());
        assertEquals("Wednesday", WEDNESDAY.toString());
        assertEquals("Thursday", THURSDAY.toString());
        assertEquals("Friday", FRIDAY.toString());
        assertEquals("Saturday", SATURDAY.toString());
    }

    public void testMonthToQuarter() throws Exception {
        assertEquals(1, JANUARY.quarter());
        assertEquals(1, FEBRUARY.quarter());
        assertEquals(1, MARCH.quarter());
        assertEquals(2, APRIL.quarter());
        assertEquals(2, MAY.quarter());
        assertEquals(2, JUNE.quarter());
        assertEquals(3, JULY.quarter());
        assertEquals(3, AUGUST.quarter());
        assertEquals(3, SEPTEMBER.quarter());
        assertEquals(4, OCTOBER.quarter());
        assertEquals(4, NOVEMBER.quarter());
        assertEquals(4, DECEMBER.quarter());
    }

    public void testMonthCodeToString() throws Exception {
        assertEquals("January", JANUARY.toString());
        assertEquals("February", FEBRUARY.toString());
        assertEquals("March", MARCH.toString());
        assertEquals("April", APRIL.toString());
        assertEquals("May", MAY.toString());
        assertEquals("June", JUNE.toString());
        assertEquals("July", JULY.toString());
        assertEquals("August", AUGUST.toString());
        assertEquals("September", SEPTEMBER.toString());
        assertEquals("October", OCTOBER.toString());
        assertEquals("November", NOVEMBER.toString());
        assertEquals("December", DECEMBER.toString());

        assertEquals("Jan", JANUARY.toShortString());
        assertEquals("Feb", FEBRUARY.toShortString());
        assertEquals("Mar", MARCH.toShortString());
        assertEquals("Apr", APRIL.toShortString());
        assertEquals("May", MAY.toShortString());
        assertEquals("Jun", JUNE.toShortString());
        assertEquals("Jul", JULY.toShortString());
        assertEquals("Aug", AUGUST.toShortString());
        assertEquals("Sep", SEPTEMBER.toShortString());
        assertEquals("Oct", OCTOBER.toShortString());
        assertEquals("Nov", NOVEMBER.toShortString());
        assertEquals("Dec", DECEMBER.toShortString());
    }

    public void testStringToMonthCode() throws Exception {
        assertEquals(1, parseMonth("1").index);
        assertEquals(2, parseMonth("2").index);
        assertEquals(3, parseMonth("3").index);
        assertEquals(4, parseMonth("4").index);
        assertEquals(5, parseMonth("5").index);
        assertEquals(6, parseMonth("6").index);
        assertEquals(7, parseMonth("7").index);
        assertEquals(8, parseMonth("8").index);
        assertEquals(9, parseMonth("9").index);
        assertEquals(10, parseMonth("10").index);
        assertEquals(11, parseMonth("11").index);
        assertEquals(12, parseMonth("12").index);

        try {
            parseMonth("Hello");
            fail("Invalid month string should throw exception.");
        } catch (IllegalArgumentException e) {
        }

        for (int m = 1; m <= 12; m++) {
            assertEquals(m, Month.makeMonth(m).index);
        }

        assertEquals(1, parseMonth("jan").index);
        assertEquals(2, parseMonth("feb").index);
        assertEquals(3, parseMonth("mar").index);
        assertEquals(4, parseMonth("apr").index);
        assertEquals(5, parseMonth("may").index);
        assertEquals(6, parseMonth("jun").index);
        assertEquals(7, parseMonth("jul").index);
        assertEquals(8, parseMonth("aug").index);
        assertEquals(9, parseMonth("sep").index);
        assertEquals(10, parseMonth("oct").index);
        assertEquals(11, parseMonth("nov").index);
        assertEquals(12, parseMonth("dec").index);

        assertEquals(1, parseMonth("JAN").index);
        assertEquals(2, parseMonth("FEB").index);
        assertEquals(3, parseMonth("MAR").index);
        assertEquals(4, parseMonth("APR").index);
        assertEquals(5, parseMonth("MAY").index);
        assertEquals(6, parseMonth("JUN").index);
        assertEquals(7, parseMonth("JUL").index);
        assertEquals(8, parseMonth("AUG").index);
        assertEquals(9, parseMonth("SEP").index);
        assertEquals(10, parseMonth("OCT").index);
        assertEquals(11, parseMonth("NOV").index);
        assertEquals(12, parseMonth("DEC").index);

        assertEquals(1, parseMonth("january").index);
        assertEquals(2, parseMonth("february").index);
        assertEquals(3, parseMonth("march").index);
        assertEquals(4, parseMonth("april").index);
        assertEquals(5, parseMonth("may").index);
        assertEquals(6, parseMonth("june").index);
        assertEquals(7, parseMonth("july").index);
        assertEquals(8, parseMonth("august").index);
        assertEquals(9, parseMonth("september").index);
        assertEquals(10, parseMonth("october").index);
        assertEquals(11, parseMonth("november").index);
        assertEquals(12, parseMonth("december").index);

        assertEquals(1, parseMonth("JANUARY").index);
        assertEquals(2, parseMonth("FEBRUARY").index);
        assertEquals(3, parseMonth("MARCH").index);
        assertEquals(4, parseMonth("APRIL").index);
        assertEquals(5, parseMonth("MAY").index);
        assertEquals(6, parseMonth("JUNE").index);
        assertEquals(7, parseMonth("JULY").index);
        assertEquals(8, parseMonth("AUGUST").index);
        assertEquals(9, parseMonth("SEPTEMBER").index);
        assertEquals(10, parseMonth("OCTOBER").index);
        assertEquals(11, parseMonth("NOVEMBER").index);
        assertEquals(12, parseMonth("DECEMBER").index);
    }

    public void testIsLeapYear() throws Exception {
        assertFalse(isLeapYear(1900));
        assertFalse(isLeapYear(1901));
        assertFalse(isLeapYear(1902));
        assertFalse(isLeapYear(1903));
        assertTrue(isLeapYear(1904));
        assertTrue(isLeapYear(1908));
        assertFalse(isLeapYear(1955));
        assertTrue(isLeapYear(1964));
        assertTrue(isLeapYear(1980));
        assertTrue(isLeapYear(2000));
        assertFalse(isLeapYear(2001));
        assertFalse(isLeapYear(2100));
    }

    public void testLeapYearCount() throws Exception {
        assertEquals(0, SpreadsheetDayDate.leapYearCount(1900));
        assertEquals(0, SpreadsheetDayDate.leapYearCount(1901));
        assertEquals(0, SpreadsheetDayDate.leapYearCount(1902));
        assertEquals(0, SpreadsheetDayDate.leapYearCount(1903));
        assertEquals(1, SpreadsheetDayDate.leapYearCount(1904));
        assertEquals(1, SpreadsheetDayDate.leapYearCount(1905));
        assertEquals(1, SpreadsheetDayDate.leapYearCount(1906));
        assertEquals(1, SpreadsheetDayDate.leapYearCount(1907));
        assertEquals(2, SpreadsheetDayDate.leapYearCount(1908));
        assertEquals(24, SpreadsheetDayDate.leapYearCount(1999));
        assertEquals(25, SpreadsheetDayDate.leapYearCount(2001));
        assertEquals(49, SpreadsheetDayDate.leapYearCount(2101));
        assertEquals(73, SpreadsheetDayDate.leapYearCount(2201));
        assertEquals(97, SpreadsheetDayDate.leapYearCount(2301));
        assertEquals(122, SpreadsheetDayDate.leapYearCount(2401));
    }

    public void testLastDayOfMonth() throws Exception {
        assertEquals(31, lastDayOfMonth(JANUARY, 1901));
        assertEquals(28, lastDayOfMonth(FEBRUARY, 1901));
        assertEquals(31, lastDayOfMonth(MARCH, 1901));
        assertEquals(30, lastDayOfMonth(APRIL, 1901));
        assertEquals(31, lastDayOfMonth(MAY, 1901));
        assertEquals(30, lastDayOfMonth(JUNE, 1901));
        assertEquals(31, lastDayOfMonth(JULY, 1901));
        assertEquals(31, lastDayOfMonth(AUGUST, 1901));
        assertEquals(30, lastDayOfMonth(SEPTEMBER, 1901));
        assertEquals(31, lastDayOfMonth(OCTOBER, 1901));
        assertEquals(30, lastDayOfMonth(NOVEMBER, 1901));
        assertEquals(31, lastDayOfMonth(DECEMBER, 1901));
        assertEquals(29, lastDayOfMonth(FEBRUARY, 1904));
    }

    public void testAddDays() throws Exception {
        DayDate newYears = d(1, JANUARY.index, 1900);
        assertEquals(d(2, JANUARY.index, 1900), addDays(1, newYears));
        assertEquals(d(1, FEBRUARY.index, 1900), addDays(31, newYears));
        assertEquals(d(1, JANUARY.index, 1901), addDays(365, newYears));
        assertEquals(d(31, DECEMBER.index, 1904), addDays(5 * 365, newYears));
    }

    private SpreadsheetDayDate d(int day, int month, int year) {
        return new SpreadsheetDayDate(day, month, year);
    }

    public void testAddMonths() throws Exception {
        assertEquals(d(1, FEBRUARY.index, 1900), addMonths(1, d(1, JANUARY.index, 1900)));
        assertEquals(d(28, FEBRUARY.index, 1900), addMonths(1, d(31, JANUARY.index, 1900)));
        assertEquals(d(28, FEBRUARY.index, 1900), addMonths(1, d(30, JANUARY.index, 1900)));
        assertEquals(d(28, FEBRUARY.index, 1900), addMonths(1, d(29, JANUARY.index, 1900)));
        assertEquals(d(28, FEBRUARY.index, 1900), addMonths(1, d(28, JANUARY.index, 1900)));
        assertEquals(d(27, FEBRUARY.index, 1900), addMonths(1, d(27, JANUARY.index, 1900)));

        assertEquals(d(30, JUNE.index, 1900), addMonths(5, d(31, JANUARY.index, 1900)));
        assertEquals(d(30, JUNE.index, 1901), addMonths(17, d(31, JANUARY.index, 1900)));
        assertEquals(d(29, FEBRUARY.index, 1904), addMonths(49, d(31, JANUARY.index, 1900)));
    }

    public void testAddYears() throws Exception {
        assertEquals(d(1, JANUARY.index, 1901), addYears(1, d(1, JANUARY.index, 1900)));
        assertEquals(d(28, FEBRUARY.index, 1905), addYears(1, d(29, FEBRUARY.index, 1904)));
        assertEquals(d(28, FEBRUARY.index, 1905), addYears(1, d(28, FEBRUARY.index, 1904)));
        assertEquals(d(28, FEBRUARY.index, 1904), addYears(1, d(28, FEBRUARY.index, 1903)));
    }

    public void testGetPreviousDayOfWeek() throws Exception {
        assertEquals(d(24, FEBRUARY.index, 2006), getPreviousDayOfWeek(FRIDAY, d(1, MARCH.index, 2006)));
        assertEquals(d(22, FEBRUARY.index, 2006), getPreviousDayOfWeek(WEDNESDAY, d(1, MARCH.index, 2006)));
        assertEquals(d(29, FEBRUARY.index, 2004), getPreviousDayOfWeek(SUNDAY, d(3, MARCH.index, 2004)));
        assertEquals(d(29, DECEMBER.index, 2004), getPreviousDayOfWeek(WEDNESDAY, d(5, JANUARY.index, 2005)));

//        try {
//            getPreviousDayOfWeek(-1, d(1, Month.JANUARY.index, 2006));
//            fail("Invalid day of week code should throw exception");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testGetFollowingDayOfWeek() throws Exception {
        assertEquals(d(1, JANUARY.index, 2005), getFollowingDayOfWeek(SATURDAY, d(25, DECEMBER.index, 2004)));
        assertEquals(d(1, JANUARY.index, 2005), getFollowingDayOfWeek(SATURDAY, d(26, DECEMBER.index, 2004)));
        // test case error in clean code book
        assertEquals(d(6, MARCH.index, 2004), getFollowingDayOfWeek(SATURDAY, d(28, FEBRUARY.index, 2004)));

//        try {
//            getFollowingDayOfWeek(-1, d(1, Month.JANUARY.index, 2006));
//            fail("Invalid day of week code should throw exception.");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testGetNearestDayOfWeek() throws Exception {
        assertEquals(d(16, APRIL.index, 2006), getNearestDayOfWeek(SUNDAY, d(16, APRIL.index, 2006)));
        assertEquals(d(16, APRIL.index, 2006), getNearestDayOfWeek(SUNDAY, d(17, APRIL.index, 2006)));
        assertEquals(d(16, APRIL.index, 2006), getNearestDayOfWeek(SUNDAY, d(18, APRIL.index, 2006)));
        assertEquals(d(16, APRIL.index, 2006), getNearestDayOfWeek(SUNDAY, d(19, APRIL.index, 2006)));
        assertEquals(d(23, APRIL.index, 2006), getNearestDayOfWeek(SUNDAY, d(20, APRIL.index, 2006)));
        assertEquals(d(23, APRIL.index, 2006), getNearestDayOfWeek(SUNDAY, d(21, APRIL.index, 2006)));
        assertEquals(d(23, APRIL.index, 2006), getNearestDayOfWeek(SUNDAY, d(22, APRIL.index, 2006)));

        assertEquals(d(17, APRIL.index, 2006), getNearestDayOfWeek(MONDAY, d(16, APRIL.index, 2006)));
        assertEquals(d(17, APRIL.index, 2006), getNearestDayOfWeek(MONDAY, d(17, APRIL.index, 2006)));
        assertEquals(d(17, APRIL.index, 2006), getNearestDayOfWeek(MONDAY, d(18, APRIL.index, 2006)));
        assertEquals(d(17, APRIL.index, 2006), getNearestDayOfWeek(MONDAY, d(19, APRIL.index, 2006)));
        assertEquals(d(17, APRIL.index, 2006), getNearestDayOfWeek(MONDAY, d(20, APRIL.index, 2006)));
        assertEquals(d(24, APRIL.index, 2006), getNearestDayOfWeek(MONDAY, d(21, APRIL.index, 2006)));
        assertEquals(d(24, APRIL.index, 2006), getNearestDayOfWeek(MONDAY, d(22, APRIL.index, 2006)));

        assertEquals(d(18, APRIL.index, 2006), getNearestDayOfWeek(TUESDAY, d(16, APRIL.index, 2006)));
        assertEquals(d(18, APRIL.index, 2006), getNearestDayOfWeek(TUESDAY, d(17, APRIL.index, 2006)));
        assertEquals(d(18, APRIL.index, 2006), getNearestDayOfWeek(TUESDAY, d(18, APRIL.index, 2006)));
        assertEquals(d(18, APRIL.index, 2006), getNearestDayOfWeek(TUESDAY, d(19, APRIL.index, 2006)));
        assertEquals(d(18, APRIL.index, 2006), getNearestDayOfWeek(TUESDAY, d(20, APRIL.index, 2006)));
        assertEquals(d(18, APRIL.index, 2006), getNearestDayOfWeek(TUESDAY, d(21, APRIL.index, 2006)));
        assertEquals(d(25, APRIL.index, 2006), getNearestDayOfWeek(TUESDAY, d(22, APRIL.index, 2006)));

        assertEquals(d(19, APRIL.index, 2006), getNearestDayOfWeek(WEDNESDAY, d(16, APRIL.index, 2006)));
        assertEquals(d(19, APRIL.index, 2006), getNearestDayOfWeek(WEDNESDAY, d(17, APRIL.index, 2006)));
        assertEquals(d(19, APRIL.index, 2006), getNearestDayOfWeek(WEDNESDAY, d(18, APRIL.index, 2006)));
        assertEquals(d(19, APRIL.index, 2006), getNearestDayOfWeek(WEDNESDAY, d(19, APRIL.index, 2006)));
        assertEquals(d(19, APRIL.index, 2006), getNearestDayOfWeek(WEDNESDAY, d(20, APRIL.index, 2006)));
        assertEquals(d(19, APRIL.index, 2006), getNearestDayOfWeek(WEDNESDAY, d(21, APRIL.index, 2006)));
        assertEquals(d(19, APRIL.index, 2006), getNearestDayOfWeek(WEDNESDAY, d(22, APRIL.index, 2006)));

        assertEquals(d(13, APRIL.index, 2006), getNearestDayOfWeek(THURSDAY, d(16, APRIL.index, 2006)));
        assertEquals(d(20, APRIL.index, 2006), getNearestDayOfWeek(THURSDAY, d(17, APRIL.index, 2006)));
        assertEquals(d(20, APRIL.index, 2006), getNearestDayOfWeek(THURSDAY, d(18, APRIL.index, 2006)));
        assertEquals(d(20, APRIL.index, 2006), getNearestDayOfWeek(THURSDAY, d(19, APRIL.index, 2006)));
        assertEquals(d(20, APRIL.index, 2006), getNearestDayOfWeek(THURSDAY, d(20, APRIL.index, 2006)));
        assertEquals(d(20, APRIL.index, 2006), getNearestDayOfWeek(THURSDAY, d(21, APRIL.index, 2006)));
        assertEquals(d(20, APRIL.index, 2006), getNearestDayOfWeek(THURSDAY, d(22, APRIL.index, 2006)));

        assertEquals(d(14, APRIL.index, 2006), getNearestDayOfWeek(FRIDAY, d(16, APRIL.index, 2006)));
        assertEquals(d(14, APRIL.index, 2006), getNearestDayOfWeek(FRIDAY, d(17, APRIL.index, 2006)));
        assertEquals(d(21, APRIL.index, 2006), getNearestDayOfWeek(FRIDAY, d(18, APRIL.index, 2006)));
        assertEquals(d(21, APRIL.index, 2006), getNearestDayOfWeek(FRIDAY, d(19, APRIL.index, 2006)));
        assertEquals(d(21, APRIL.index, 2006), getNearestDayOfWeek(FRIDAY, d(20, APRIL.index, 2006)));
        assertEquals(d(21, APRIL.index, 2006), getNearestDayOfWeek(FRIDAY, d(21, APRIL.index, 2006)));
        assertEquals(d(21, APRIL.index, 2006), getNearestDayOfWeek(FRIDAY, d(22, APRIL.index, 2006)));

        assertEquals(d(15, APRIL.index, 2006), getNearestDayOfWeek(SATURDAY, d(16, APRIL.index, 2006)));
        assertEquals(d(15, APRIL.index, 2006), getNearestDayOfWeek(SATURDAY, d(17, APRIL.index, 2006)));
        assertEquals(d(15, APRIL.index, 2006), getNearestDayOfWeek(SATURDAY, d(18, APRIL.index, 2006)));
        assertEquals(d(22, APRIL.index, 2006), getNearestDayOfWeek(SATURDAY, d(19, APRIL.index, 2006)));
        assertEquals(d(22, APRIL.index, 2006), getNearestDayOfWeek(SATURDAY, d(20, APRIL.index, 2006)));
        assertEquals(d(22, APRIL.index, 2006), getNearestDayOfWeek(SATURDAY, d(21, APRIL.index, 2006)));
        assertEquals(d(22, APRIL.index, 2006), getNearestDayOfWeek(SATURDAY, d(22, APRIL.index, 2006)));

//        try {
//            getNearestDayOfWeek(-1, d(1, Month.JANUARY.index, 2006));
//            fail("Invalid day of week code should throw exception.");
//        } catch (IllegalArgumentException e) {}
    }

    public void testEndOfCurrentMonth() throws Exception {
        DayDate d = DayDate.createInstance(2);
        assertEquals(d(31, JANUARY.index, 2006), d.getEndOfCurrentMonth(d(1, JANUARY.index, 2006)));
        assertEquals(d(28, FEBRUARY.index, 2006), d.getEndOfCurrentMonth(d(1, FEBRUARY.index, 2006)));
        assertEquals(d(31, MARCH.index, 2006), d.getEndOfCurrentMonth(d(1, MARCH.index, 2006)));
        assertEquals(d(30, APRIL.index, 2006), d.getEndOfCurrentMonth(d(1, APRIL.index, 2006)));
        assertEquals(d(31, MAY.index, 2006), d.getEndOfCurrentMonth(d(1, MAY.index, 2006)));
        assertEquals(d(30, JUNE.index, 2006), d.getEndOfCurrentMonth(d(1, JUNE.index, 2006)));
        assertEquals(d(31, JULY.index, 2006), d.getEndOfCurrentMonth(d(1, JULY.index, 2006)));
        assertEquals(d(31, AUGUST.index, 2006), d.getEndOfCurrentMonth(d(1, AUGUST.index, 2006)));
        assertEquals(d(30, SEPTEMBER.index, 2006), d.getEndOfCurrentMonth(d(1, SEPTEMBER.index, 2006)));
        assertEquals(d(31, OCTOBER.index, 2006), d.getEndOfCurrentMonth(d(1, OCTOBER.index, 2006)));
        assertEquals(d(30, NOVEMBER.index, 2006), d.getEndOfCurrentMonth(d(1, NOVEMBER.index, 2006)));
        assertEquals(d(31, DECEMBER.index, 2006), d.getEndOfCurrentMonth(d(1, DECEMBER.index, 2006)));
        assertEquals(d(29, FEBRUARY.index, 2008), d.getEndOfCurrentMonth(d(1, FEBRUARY.index, 2008)));
    }

    public void testRelativeToString() throws Exception {
//        assertEquals("Preceding", relativeToString(PRECEDING));
//        assertEquals("Nearest", relativeToString(NEAREST));
//        assertEquals("Following", relativeToString(FOLLOWING));

//todo        try {
//            weekInMonthToString(-1000);
//            fail("Invalid relative code should throw exception.");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testCreateInstanceFromDDMMYYYY() throws Exception {
        DayDate dayDate = createInstance(1, JANUARY.index, 1900);
        assertEquals(1, dayDate.getDayOfMonth());
        assertEquals(JANUARY.index, dayDate.getMonth());
        assertEquals(1900, dayDate.getYYYY());
        assertEquals(2, dayDate.toSerial());
    }

    public void testCreateInstanceFromSerial() throws Exception {
        assertEquals(d(1, JANUARY.index, 1900), createInstance(2));
        assertEquals(d(1, JANUARY.index, 1901), createInstance(367));
    }

    public void testCreateInstanceFromJavaDate() throws Exception {
        assertEquals(d(1, JANUARY.index, 1900),
                createInstance(new GregorianCalendar(1900, 0, 1).getTime()));

        assertEquals(d(1, JANUARY.index, 2006),
                createInstance(new GregorianCalendar(2006, 0, 1).getTime()));
    }
}
