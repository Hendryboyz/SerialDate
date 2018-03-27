package test;

import junit.framework.TestCase;
import main.DayDate;
import main.SpreadsheetDayDate;

import java.util.GregorianCalendar;

import static main.Day.*;
import static main.Month.*;
import static main.DayDate.*;

public class BobSerialDayDateTest extends TestCase {
    
    public void testStringToWeekdayCode() throws Exception {

        try {
            parse("Hello");
            fail("Invalid Weekday Code should throw exception.");
        } catch (IllegalArgumentException e) {

        }

        assertEquals(MONDAY, parse("Monday"));
        assertEquals(MONDAY, parse("Mon"));
        assertEquals(MONDAY, parse("monday"));
        assertEquals(MONDAY, parse("MONDAY"));
        assertEquals(MONDAY, parse("mon"));

        assertEquals(TUESDAY, parse("Tuesday"));
        assertEquals(TUESDAY, parse("Tue"));
        assertEquals(TUESDAY, parse("tuesday"));
        assertEquals(TUESDAY, parse("TUESDAY"));
        assertEquals(TUESDAY, parse("tue"));
//        assertEquals(Day.TUESDAY, parse("tues"));

        assertEquals(WEDNESDAY, parse("Wednesday"));
        assertEquals(WEDNESDAY, parse("Wed"));
        assertEquals(WEDNESDAY, parse("wednesday"));
        assertEquals(WEDNESDAY, parse("WEDNESDAY"));
        assertEquals(WEDNESDAY, parse("wed"));

        assertEquals(THURSDAY, parse("Thursday"));
        assertEquals(THURSDAY, parse("Thu"));
        assertEquals(THURSDAY, parse("thursday"));
        assertEquals(THURSDAY, parse("THURSDAY"));
        assertEquals(THURSDAY, parse("thu"));
//        assertEquals(Day.THURSDAY, parse("thurs"));

        assertEquals(FRIDAY, parse("Friday"));
        assertEquals(FRIDAY, parse("Fri"));
        assertEquals(FRIDAY, parse("friday"));
        assertEquals(FRIDAY, parse("FRIDAY"));
        assertEquals(FRIDAY, parse("fri"));

        assertEquals(SATURDAY, parse("Saturday"));
        assertEquals(SATURDAY, parse("Sat"));
        assertEquals(SATURDAY, parse("saturday"));
        assertEquals(SATURDAY, parse("SATURDAY"));
        assertEquals(SATURDAY, parse("sat"));

        assertEquals(SUNDAY, parse("Sunday"));
        assertEquals(SUNDAY, parse("Sun"));
        assertEquals(SUNDAY, parse("sunday"));
        assertEquals(SUNDAY, parse("SUNDAY"));
        assertEquals(SUNDAY, parse("sun"));
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
        assertEquals(1, monthCodeToQuarter(JANUARY.index));
        assertEquals(1, monthCodeToQuarter(FEBRUARY.index));
        assertEquals(1, monthCodeToQuarter(MARCH.index));
        assertEquals(2, monthCodeToQuarter(APRIL.index));
        assertEquals(2, monthCodeToQuarter(MAY.index));
        assertEquals(2, monthCodeToQuarter(JUNE.index));
        assertEquals(3, monthCodeToQuarter(JULY.index));
        assertEquals(3, monthCodeToQuarter(AUGUST.index));
        assertEquals(3, monthCodeToQuarter(SEPTEMBER.index));
        assertEquals(4, monthCodeToQuarter(OCTOBER.index));
        assertEquals(4, monthCodeToQuarter(NOVEMBER.index));
        assertEquals(4, monthCodeToQuarter(DECEMBER.index));

        try {
            monthCodeToQuarter(-1);
            fail("Invalid Month Code should throw exception.");
        } catch (IllegalArgumentException e) {

        }
    }

    public void testMonthCodeToString() throws Exception {
        assertEquals("January", monthCodeToString(JANUARY));
        assertEquals("February", monthCodeToString(FEBRUARY));
        assertEquals("March", monthCodeToString(MARCH));
        assertEquals("April", monthCodeToString(APRIL));
        assertEquals("May", monthCodeToString(MAY));
        assertEquals("June", monthCodeToString(JUNE));
        assertEquals("July", monthCodeToString(JULY));
        assertEquals("August", monthCodeToString(AUGUST));
        assertEquals("September", monthCodeToString(SEPTEMBER));
        assertEquals("October", monthCodeToString(OCTOBER));
        assertEquals("November", monthCodeToString(NOVEMBER));
        assertEquals("December", monthCodeToString(DECEMBER));

        assertEquals("Jan", monthCodeToString(JANUARY, true));
        assertEquals("Feb", monthCodeToString(FEBRUARY, true));
        assertEquals("Mar", monthCodeToString(MARCH, true));
        assertEquals("Apr", monthCodeToString(APRIL, true));
        assertEquals("May", monthCodeToString(MAY, true));
        assertEquals("Jun", monthCodeToString(JUNE, true));
        assertEquals("Jul", monthCodeToString(JULY, true));
        assertEquals("Aug", monthCodeToString(AUGUST, true));
        assertEquals("Sep", monthCodeToString(SEPTEMBER, true));
        assertEquals("Oct", monthCodeToString(OCTOBER, true));
        assertEquals("Nov", monthCodeToString(NOVEMBER, true));
        assertEquals("Dec", monthCodeToString(DECEMBER, true));

//        try {
//            monthCodeToString(-1);
//            fail("Invalid month code should throw exception.");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testStringToMonthCode() throws Exception {
        assertEquals(1, stringToMonthCode("1"));
        assertEquals(2, stringToMonthCode("2"));
        assertEquals(3, stringToMonthCode("3"));
        assertEquals(4, stringToMonthCode("4"));
        assertEquals(5, stringToMonthCode("5"));
        assertEquals(6, stringToMonthCode("6"));
        assertEquals(7, stringToMonthCode("7"));
        assertEquals(8, stringToMonthCode("8"));
        assertEquals(9, stringToMonthCode("9"));
        assertEquals(10, stringToMonthCode("10"));
        assertEquals(11, stringToMonthCode("11"));
        assertEquals(12, stringToMonthCode("12"));

//todo        assertEquals(-1, stringToMonthCode("0"));
//        assertEquals(-1, stringToMonthCode("13"));

        assertEquals(-1, stringToMonthCode("Hello"));

        for (int m = 1; m <= 12; m++) {
            assertEquals(m, stringToMonthCode(monthCodeToString(makeMonth(m), false)));
            assertEquals(m, stringToMonthCode(monthCodeToString(makeMonth(m), true)));
        }

//        assertEquals(1, stringToMonthCode("jan"));
//        assertEquals(2, stringToMonthCode("feb"));
//        assertEquals(3, stringToMonthCode("mar"));
//        assertEquals(4, stringToMonthCode("apr"));
//        assertEquals(5, stringToMonthCode("may"));
//        assertEquals(6, stringToMonthCode("jun"));
//        assertEquals(7, stringToMonthCode("jul"));
//        assertEquals(8, stringToMonthCode("aug"));
//        assertEquals(9, stringToMonthCode("sep"));
//        assertEquals(10, stringToMonthCode("oct"));
//        assertEquals(11, stringToMonthCode("nov"));
//        assertEquals(12, stringToMonthCode("dec"));

//        assertEquals(1, stringToMonthCode("JAN"));
//        assertEquals(2, stringToMonthCode("FEB"));
//        assertEquals(3, stringToMonthCode("MAR"));
//        assertEquals(4, stringToMonthCode("APR"));
//        assertEquals(5, stringToMonthCode("MAY"));
//        assertEquals(6, stringToMonthCode("JUN"));
//        assertEquals(7, stringToMonthCode("JUL"));
//        assertEquals(8, stringToMonthCode("AUG"));
//        assertEquals(9, stringToMonthCode("SEP"));
//        assertEquals(10, stringToMonthCode("OCT"));
//        assertEquals(11, stringToMonthCode("NOV"));
//        assertEquals(12, stringToMonthCode("DEC"));

//        assertEquals(1, stringToMonthCode("january"));
//        assertEquals(2, stringToMonthCode("february"));
//        assertEquals(3, stringToMonthCode("march"));
//        assertEquals(4, stringToMonthCode("Month.APRIL.index"));
//        assertEquals(5, stringToMonthCode("may"));
//        assertEquals(6, stringToMonthCode("june"));
//        assertEquals(7, stringToMonthCode("july"));
//        assertEquals(8, stringToMonthCode("august"));
//        assertEquals(9, stringToMonthCode("september"));
//        assertEquals(10, stringToMonthCode("october"));
//        assertEquals(11, stringToMonthCode("november"));
//        assertEquals(12, stringToMonthCode("december"));

//        assertEquals(1, stringToMonthCode("JANUARY"));
//        assertEquals(2, stringToMonthCode("FEBRUARY"));
//        assertEquals(3, stringToMonthCode("MARCH"));
//        assertEquals(4, stringToMonthCode("Month.APRIL.index"));
//        assertEquals(5, stringToMonthCode("MAY"));
//        assertEquals(6, stringToMonthCode("JUNE"));
//        assertEquals(7, stringToMonthCode("JULY"));
//        assertEquals(8, stringToMonthCode("AUGUST"));
//        assertEquals(9, stringToMonthCode("SEPTEMBER"));
//        assertEquals(10, stringToMonthCode("OCTOBER"));
//        assertEquals(11, stringToMonthCode("NOVEMBER"));
//        assertEquals(12, stringToMonthCode("DECEMBER"));
    }

//    public void testIsValidWeekInMonthCode() throws Exception {
//        // test case error, week code 0 is invalid
//        for (int w = 1; w <= 4; w++) {
//            assertTrue(isValidWeekInMonthCode(w));
//        }
//        assertFalse(isValidWeekInMonthCode(5));
//    }

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
        assertEquals(0, leapYearCount(1900));
        assertEquals(0, leapYearCount(1901));
        assertEquals(0, leapYearCount(1902));
        assertEquals(0, leapYearCount(1903));
        assertEquals(1, leapYearCount(1904));
        assertEquals(1, leapYearCount(1905));
        assertEquals(1, leapYearCount(1906));
        assertEquals(1, leapYearCount(1907));
        assertEquals(2, leapYearCount(1908));
        assertEquals(24, leapYearCount(1999));
        assertEquals(25, leapYearCount(2001));
        assertEquals(49, leapYearCount(2101));
        assertEquals(73, leapYearCount(2201));
        assertEquals(97, leapYearCount(2301));
        assertEquals(122, leapYearCount(2401));
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
