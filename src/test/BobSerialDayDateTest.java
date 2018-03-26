package test;

import junit.framework.TestCase;
import main.DayDate;
import main.SpreadsheetDayDate;

import java.util.GregorianCalendar;

import static main.DayDate.*;

public class BobSerialDayDateTest extends TestCase {

//    public void testIsValidWeekdayCode() throws Exception {
//        for (int day = 1; day <= 7; day++)
//            assertTrue(isValidWeekdayCode(day));
//        assertFalse(isValidWeekdayCode(0));
//        assertFalse(isValidWeekdayCode(8));
//    }

    public void testStringToWeekdayCode() throws Exception {

        assertEquals(-1, stringToWeekdayCode("Hello"));

        assertEquals(Day.MONDAY.toInt(), stringToWeekdayCode("Monday"));
        assertEquals(Day.MONDAY.toInt(), stringToWeekdayCode("Mon"));
        assertEquals(Day.MONDAY.toInt(), stringToWeekdayCode("monday"));
        assertEquals(Day.MONDAY.toInt(), stringToWeekdayCode("MONDAY"));
        assertEquals(Day.MONDAY.toInt(), stringToWeekdayCode("mon"));

        assertEquals(Day.TUESDAY.toInt(), stringToWeekdayCode("Tuesday"));
        assertEquals(Day.TUESDAY.toInt(), stringToWeekdayCode("Tue"));
        assertEquals(Day.TUESDAY.toInt(), stringToWeekdayCode("tuesday"));
        assertEquals(Day.TUESDAY.toInt(), stringToWeekdayCode("TUESDAY"));
        assertEquals(Day.TUESDAY.toInt(), stringToWeekdayCode("tue"));
//        assertEquals(Day.TUESDAY.toInt(), stringToWeekdayCode("tues"));

        assertEquals(Day.WEDNESDAY.toInt(), stringToWeekdayCode("Wednesday"));
        assertEquals(Day.WEDNESDAY.toInt(), stringToWeekdayCode("Wed"));
        assertEquals(Day.WEDNESDAY.toInt(), stringToWeekdayCode("wednesday"));
        assertEquals(Day.WEDNESDAY.toInt(), stringToWeekdayCode("WEDNESDAY"));
        assertEquals(Day.WEDNESDAY.toInt(), stringToWeekdayCode("wed"));

        assertEquals(Day.THURSDAY.toInt(), stringToWeekdayCode("Thursday"));
        assertEquals(Day.THURSDAY.toInt(), stringToWeekdayCode("Thu"));
        assertEquals(Day.THURSDAY.toInt(), stringToWeekdayCode("thursday"));
        assertEquals(Day.THURSDAY.toInt(), stringToWeekdayCode("THURSDAY"));
        assertEquals(Day.THURSDAY.toInt(), stringToWeekdayCode("thu"));
//        assertEquals(Day.THURSDAY.toInt(), stringToWeekdayCode("thurs"));

        assertEquals(Day.FRIDAY.toInt(), stringToWeekdayCode("Friday"));
        assertEquals(Day.FRIDAY.toInt(), stringToWeekdayCode("Fri"));
        assertEquals(Day.FRIDAY.toInt(), stringToWeekdayCode("friday"));
        assertEquals(Day.FRIDAY.toInt(), stringToWeekdayCode("FRIDAY"));
        assertEquals(Day.FRIDAY.toInt(), stringToWeekdayCode("fri"));

        assertEquals(Day.SATURDAY.toInt(), stringToWeekdayCode("Saturday"));
        assertEquals(Day.SATURDAY.toInt(), stringToWeekdayCode("Sat"));
        assertEquals(Day.SATURDAY.toInt(), stringToWeekdayCode("saturday"));
        assertEquals(Day.SATURDAY.toInt(), stringToWeekdayCode("SATURDAY"));
        assertEquals(Day.SATURDAY.toInt(), stringToWeekdayCode("sat"));

        assertEquals(Day.SUNDAY.toInt(), stringToWeekdayCode("Sunday"));
        assertEquals(Day.SUNDAY.toInt(), stringToWeekdayCode("Sun"));
        assertEquals(Day.SUNDAY.toInt(), stringToWeekdayCode("sunday"));
        assertEquals(Day.SUNDAY.toInt(), stringToWeekdayCode("SUNDAY"));
        assertEquals(Day.SUNDAY.toInt(), stringToWeekdayCode("sun"));
    }

    public void testWeekdayCodeToString() throws Exception {
        assertEquals("Sunday", weekdayCodeToString(Day.SUNDAY.toInt()));
        assertEquals("Monday", weekdayCodeToString(Day.MONDAY.toInt()));
        assertEquals("Tuesday", weekdayCodeToString(Day.TUESDAY.toInt()));
        assertEquals("Wednesday", weekdayCodeToString(Day.WEDNESDAY.toInt()));
        assertEquals("Thursday", weekdayCodeToString(Day.THURSDAY.toInt()));
        assertEquals("Friday", weekdayCodeToString(Day.FRIDAY.toInt()));
        assertEquals("Saturday", weekdayCodeToString(Day.SATURDAY.toInt()));
    }

    public void testMonthToQuarter() throws Exception {
        assertEquals(1, monthCodeToQuarter(Month.JANUARY.index));
        assertEquals(1, monthCodeToQuarter(Month.FEBRUARY.index));
        assertEquals(1, monthCodeToQuarter(Month.MARCH.index));
        assertEquals(2, monthCodeToQuarter(Month.APRIL.index));
        assertEquals(2, monthCodeToQuarter(Month.MAY.index));
        assertEquals(2, monthCodeToQuarter(Month.JUNE.index));
        assertEquals(3, monthCodeToQuarter(Month.JULY.index));
        assertEquals(3, monthCodeToQuarter(Month.AUGUST.index));
        assertEquals(3, monthCodeToQuarter(Month.SEPTEMBER.index));
        assertEquals(4, monthCodeToQuarter(Month.OCTOBER.index));
        assertEquals(4, monthCodeToQuarter(Month.NOVEMBER.index));
        assertEquals(4, monthCodeToQuarter(Month.DECEMBER.index));

        try {
            monthCodeToQuarter(-1);
            fail("Invalid Month Code should throw exception.");
        } catch (IllegalArgumentException e) {

        }
    }

    public void testMonthCodeToString() throws Exception {
        assertEquals("January", monthCodeToString(Month.JANUARY));
        assertEquals("February", monthCodeToString(Month.FEBRUARY));
        assertEquals("March", monthCodeToString(Month.MARCH));
        assertEquals("April", monthCodeToString(Month.APRIL));
        assertEquals("May", monthCodeToString(Month.MAY));
        assertEquals("June", monthCodeToString(Month.JUNE));
        assertEquals("July", monthCodeToString(Month.JULY));
        assertEquals("August", monthCodeToString(Month.AUGUST));
        assertEquals("September", monthCodeToString(Month.SEPTEMBER));
        assertEquals("October", monthCodeToString(Month.OCTOBER));
        assertEquals("November", monthCodeToString(Month.NOVEMBER));
        assertEquals("December", monthCodeToString(Month.DECEMBER));

        assertEquals("Jan", monthCodeToString(Month.JANUARY, true));
        assertEquals("Feb", monthCodeToString(Month.FEBRUARY, true));
        assertEquals("Mar", monthCodeToString(Month.MARCH, true));
        assertEquals("Apr", monthCodeToString(Month.APRIL, true));
        assertEquals("May", monthCodeToString(Month.MAY, true));
        assertEquals("Jun", monthCodeToString(Month.JUNE, true));
        assertEquals("Jul", monthCodeToString(Month.JULY, true));
        assertEquals("Aug", monthCodeToString(Month.AUGUST, true));
        assertEquals("Sep", monthCodeToString(Month.SEPTEMBER, true));
        assertEquals("Oct", monthCodeToString(Month.OCTOBER, true));
        assertEquals("Nov", monthCodeToString(Month.NOVEMBER, true));
        assertEquals("Dec", monthCodeToString(Month.DECEMBER, true));

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
            assertEquals(m, stringToMonthCode(monthCodeToString(Month.make(m), false)));
            assertEquals(m, stringToMonthCode(monthCodeToString(Month.make(m), true)));
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

    public void testIsValidWeekInMonthCode() throws Exception {
        // test case error, week code 0 is invalid
        for (int w = 1; w <= 4; w++) {
            assertTrue(isValidWeekInMonthCode(w));
        }
        assertFalse(isValidWeekInMonthCode(5));
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
        assertEquals(31, lastDayOfMonth(Month.JANUARY, 1901));
        assertEquals(28, lastDayOfMonth(Month.FEBRUARY, 1901));
        assertEquals(31, lastDayOfMonth(Month.MARCH, 1901));
        assertEquals(30, lastDayOfMonth(Month.APRIL, 1901));
        assertEquals(31, lastDayOfMonth(Month.MAY, 1901));
        assertEquals(30, lastDayOfMonth(Month.JUNE, 1901));
        assertEquals(31, lastDayOfMonth(Month.JULY, 1901));
        assertEquals(31, lastDayOfMonth(Month.AUGUST, 1901));
        assertEquals(30, lastDayOfMonth(Month.SEPTEMBER, 1901));
        assertEquals(31, lastDayOfMonth(Month.OCTOBER, 1901));
        assertEquals(30, lastDayOfMonth(Month.NOVEMBER, 1901));
        assertEquals(31, lastDayOfMonth(Month.DECEMBER, 1901));
        assertEquals(29, lastDayOfMonth(Month.FEBRUARY, 1904));
    }

    public void testAddDays() throws Exception {
        DayDate newYears = d(1, Month.JANUARY.index, 1900);
        assertEquals(d(2, Month.JANUARY.index, 1900), addDays(1, newYears));
        assertEquals(d(1, Month.FEBRUARY.index, 1900), addDays(31, newYears));
        assertEquals(d(1, Month.JANUARY.index, 1901), addDays(365, newYears));
        assertEquals(d(31, Month.DECEMBER.index, 1904), addDays(5 * 365, newYears));
    }

    private SpreadsheetDayDate d(int day, int month, int year) {
        return new SpreadsheetDayDate(day, month, year);
    }

    public void testAddMonths() throws Exception {
        assertEquals(d(1, Month.FEBRUARY.index, 1900), addMonths(1, d(1, Month.JANUARY.index, 1900)));
        assertEquals(d(28, Month.FEBRUARY.index, 1900), addMonths(1, d(31, Month.JANUARY.index, 1900)));
        assertEquals(d(28, Month.FEBRUARY.index, 1900), addMonths(1, d(30, Month.JANUARY.index, 1900)));
        assertEquals(d(28, Month.FEBRUARY.index, 1900), addMonths(1, d(29, Month.JANUARY.index, 1900)));
        assertEquals(d(28, Month.FEBRUARY.index, 1900), addMonths(1, d(28, Month.JANUARY.index, 1900)));
        assertEquals(d(27, Month.FEBRUARY.index, 1900), addMonths(1, d(27, Month.JANUARY.index, 1900)));

        assertEquals(d(30, Month.JUNE.index, 1900), addMonths(5, d(31, Month.JANUARY.index, 1900)));
        assertEquals(d(30, Month.JUNE.index, 1901), addMonths(17, d(31, Month.JANUARY.index, 1900)));
        assertEquals(d(29, Month.FEBRUARY.index, 1904), addMonths(49, d(31, Month.JANUARY.index, 1900)));
    }

    public void testAddYears() throws Exception {
        assertEquals(d(1, Month.JANUARY.index, 1901), addYears(1, d(1, Month.JANUARY.index, 1900)));
        assertEquals(d(28, Month.FEBRUARY.index, 1905), addYears(1, d(29, Month.FEBRUARY.index, 1904)));
        assertEquals(d(28, Month.FEBRUARY.index, 1905), addYears(1, d(28, Month.FEBRUARY.index, 1904)));
        assertEquals(d(28, Month.FEBRUARY.index, 1904), addYears(1, d(28, Month.FEBRUARY.index, 1903)));
    }

    public void testGetPreviousDayOfWeek() throws Exception {
        assertEquals(d(24, Month.FEBRUARY.index, 2006), getPreviousDayOfWeek(Day.FRIDAY, d(1, Month.MARCH.index, 2006)));
        assertEquals(d(22, Month.FEBRUARY.index, 2006), getPreviousDayOfWeek(Day.WEDNESDAY, d(1, Month.MARCH.index, 2006)));
        assertEquals(d(29, Month.FEBRUARY.index, 2004), getPreviousDayOfWeek(Day.SUNDAY, d(3, Month.MARCH.index, 2004)));
        assertEquals(d(29, Month.DECEMBER.index, 2004), getPreviousDayOfWeek(Day.WEDNESDAY, d(5, Month.JANUARY.index, 2005)));

//        try {
//            getPreviousDayOfWeek(-1, d(1, Month.JANUARY.index, 2006));
//            fail("Invalid day of week code should throw exception");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testGetFollowingDayOfWeek() throws Exception {
        assertEquals(d(1, Month.JANUARY.index, 2005), getFollowingDayOfWeek(Day.SATURDAY, d(25, Month.DECEMBER.index, 2004)));
        assertEquals(d(1, Month.JANUARY.index, 2005), getFollowingDayOfWeek(Day.SATURDAY, d(26, Month.DECEMBER.index, 2004)));
        // test case error in clean code book
        assertEquals(d(6, Month.MARCH.index, 2004), getFollowingDayOfWeek(Day.SATURDAY, d(28, Month.FEBRUARY.index, 2004)));

//        try {
//            getFollowingDayOfWeek(-1, d(1, Month.JANUARY.index, 2006));
//            fail("Invalid day of week code should throw exception.");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testGetNearestDayOfWeek() throws Exception {
        assertEquals(d(16, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SUNDAY, d(16, Month.APRIL.index, 2006)));
        assertEquals(d(16, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SUNDAY, d(17, Month.APRIL.index, 2006)));
        assertEquals(d(16, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SUNDAY, d(18, Month.APRIL.index, 2006)));
        assertEquals(d(16, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SUNDAY, d(19, Month.APRIL.index, 2006)));
        assertEquals(d(23, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SUNDAY, d(20, Month.APRIL.index, 2006)));
        assertEquals(d(23, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SUNDAY, d(21, Month.APRIL.index, 2006)));
        assertEquals(d(23, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SUNDAY, d(22, Month.APRIL.index, 2006)));

        assertEquals(d(17, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.MONDAY, d(16, Month.APRIL.index, 2006)));
        assertEquals(d(17, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.MONDAY, d(17, Month.APRIL.index, 2006)));
        assertEquals(d(17, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.MONDAY, d(18, Month.APRIL.index, 2006)));
        assertEquals(d(17, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.MONDAY, d(19, Month.APRIL.index, 2006)));
        assertEquals(d(17, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.MONDAY, d(20, Month.APRIL.index, 2006)));
        assertEquals(d(24, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.MONDAY, d(21, Month.APRIL.index, 2006)));
        assertEquals(d(24, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.MONDAY, d(22, Month.APRIL.index, 2006)));

        assertEquals(d(18, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.TUESDAY, d(16, Month.APRIL.index, 2006)));
        assertEquals(d(18, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.TUESDAY, d(17, Month.APRIL.index, 2006)));
        assertEquals(d(18, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.TUESDAY, d(18, Month.APRIL.index, 2006)));
        assertEquals(d(18, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.TUESDAY, d(19, Month.APRIL.index, 2006)));
        assertEquals(d(18, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.TUESDAY, d(20, Month.APRIL.index, 2006)));
        assertEquals(d(18, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.TUESDAY, d(21, Month.APRIL.index, 2006)));
        assertEquals(d(25, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.TUESDAY, d(22, Month.APRIL.index, 2006)));

        assertEquals(d(19, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(16, Month.APRIL.index, 2006)));
        assertEquals(d(19, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(17, Month.APRIL.index, 2006)));
        assertEquals(d(19, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(18, Month.APRIL.index, 2006)));
        assertEquals(d(19, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(19, Month.APRIL.index, 2006)));
        assertEquals(d(19, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(20, Month.APRIL.index, 2006)));
        assertEquals(d(19, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(21, Month.APRIL.index, 2006)));
        assertEquals(d(19, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(22, Month.APRIL.index, 2006)));

        assertEquals(d(13, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.THURSDAY, d(16, Month.APRIL.index, 2006)));
        assertEquals(d(20, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.THURSDAY, d(17, Month.APRIL.index, 2006)));
        assertEquals(d(20, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.THURSDAY, d(18, Month.APRIL.index, 2006)));
        assertEquals(d(20, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.THURSDAY, d(19, Month.APRIL.index, 2006)));
        assertEquals(d(20, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.THURSDAY, d(20, Month.APRIL.index, 2006)));
        assertEquals(d(20, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.THURSDAY, d(21, Month.APRIL.index, 2006)));
        assertEquals(d(20, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.THURSDAY, d(22, Month.APRIL.index, 2006)));

        assertEquals(d(14, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.FRIDAY, d(16, Month.APRIL.index, 2006)));
        assertEquals(d(14, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.FRIDAY, d(17, Month.APRIL.index, 2006)));
        assertEquals(d(21, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.FRIDAY, d(18, Month.APRIL.index, 2006)));
        assertEquals(d(21, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.FRIDAY, d(19, Month.APRIL.index, 2006)));
        assertEquals(d(21, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.FRIDAY, d(20, Month.APRIL.index, 2006)));
        assertEquals(d(21, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.FRIDAY, d(21, Month.APRIL.index, 2006)));
        assertEquals(d(21, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.FRIDAY, d(22, Month.APRIL.index, 2006)));

        assertEquals(d(15, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SATURDAY, d(16, Month.APRIL.index, 2006)));
        assertEquals(d(15, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SATURDAY, d(17, Month.APRIL.index, 2006)));
        assertEquals(d(15, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SATURDAY, d(18, Month.APRIL.index, 2006)));
        assertEquals(d(22, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SATURDAY, d(19, Month.APRIL.index, 2006)));
        assertEquals(d(22, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SATURDAY, d(20, Month.APRIL.index, 2006)));
        assertEquals(d(22, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SATURDAY, d(21, Month.APRIL.index, 2006)));
        assertEquals(d(22, Month.APRIL.index, 2006), getNearestDayOfWeek(Day.SATURDAY, d(22, Month.APRIL.index, 2006)));

//        try {
//            getNearestDayOfWeek(-1, d(1, Month.JANUARY.index, 2006));
//            fail("Invalid day of week code should throw exception.");
//        } catch (IllegalArgumentException e) {}
    }

    public void testEndOfCurrentMonth() throws Exception {
        DayDate d = DayDate.createInstance(2);
        assertEquals(d(31, Month.JANUARY.index, 2006), d.getEndOfCurrentMonth(d(1, Month.JANUARY.index, 2006)));
        assertEquals(d(28, Month.FEBRUARY.index, 2006), d.getEndOfCurrentMonth(d(1, Month.FEBRUARY.index, 2006)));
        assertEquals(d(31, Month.MARCH.index, 2006), d.getEndOfCurrentMonth(d(1, Month.MARCH.index, 2006)));
        assertEquals(d(30, Month.APRIL.index, 2006), d.getEndOfCurrentMonth(d(1, Month.APRIL.index, 2006)));
        assertEquals(d(31, Month.MAY.index, 2006), d.getEndOfCurrentMonth(d(1, Month.MAY.index, 2006)));
        assertEquals(d(30, Month.JUNE.index, 2006), d.getEndOfCurrentMonth(d(1, Month.JUNE.index, 2006)));
        assertEquals(d(31, Month.JULY.index, 2006), d.getEndOfCurrentMonth(d(1, Month.JULY.index, 2006)));
        assertEquals(d(31, Month.AUGUST.index, 2006), d.getEndOfCurrentMonth(d(1, Month.AUGUST.index, 2006)));
        assertEquals(d(30, Month.SEPTEMBER.index, 2006), d.getEndOfCurrentMonth(d(1, Month.SEPTEMBER.index, 2006)));
        assertEquals(d(31, Month.OCTOBER.index, 2006), d.getEndOfCurrentMonth(d(1, Month.OCTOBER.index, 2006)));
        assertEquals(d(30, Month.NOVEMBER.index, 2006), d.getEndOfCurrentMonth(d(1, Month.NOVEMBER.index, 2006)));
        assertEquals(d(31, Month.DECEMBER.index, 2006), d.getEndOfCurrentMonth(d(1, Month.DECEMBER.index, 2006)));
        assertEquals(d(29, Month.FEBRUARY.index, 2008), d.getEndOfCurrentMonth(d(1, Month.FEBRUARY.index, 2008)));
    }

    public void testWeekInMonthToString() throws Exception {
        assertEquals("First", weekInMonthToString(FIRST_WEEK_IN_MONTH));
        assertEquals("Second", weekInMonthToString(SECOND_WEEK_IN_MONTH));
        assertEquals("Third", weekInMonthToString(THIRD_WEEK_IN_MONTH));
        assertEquals("Fourth", weekInMonthToString(FOURTH_WEEK_IN_MONTH));
        assertEquals("Last", weekInMonthToString(LAST_WEEK_IN_MONTH));

//todo        try {
//            weekInMonthToString(-1);
//            fail("Invalid week code should throw exception.");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testRelativeToString() throws Exception {
        assertEquals("Preceding", relativeToString(PRECEDING));
        assertEquals("Nearest", relativeToString(NEAREST));
        assertEquals("Following", relativeToString(FOLLOWING));

//todo        try {
//            weekInMonthToString(-1000);
//            fail("Invalid relative code should throw exception.");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testCreateInstanceFromDDMMYYYY() throws Exception {
        DayDate dayDate = createInstance(1, Month.JANUARY.index, 1900);
        assertEquals(1, dayDate.getDayOfMonth());
        assertEquals(Month.JANUARY.index, dayDate.getMonth());
        assertEquals(1900, dayDate.getYYYY());
        assertEquals(2, dayDate.toSerial());
    }

    public void testCreateInstanceFromSerial() throws Exception {
        assertEquals(d(1, Month.JANUARY.index, 1900), createInstance(2));
        assertEquals(d(1, Month.JANUARY.index, 1901), createInstance(367));
    }

    public void testCreateInstanceFromJavaDate() throws Exception {
        assertEquals(d(1, Month.JANUARY.index, 1900),
                createInstance(new GregorianCalendar(1900, 0, 1).getTime()));

        assertEquals(d(1, Month.JANUARY.index, 2006),
                createInstance(new GregorianCalendar(2006, 0, 1).getTime()));
    }
}
