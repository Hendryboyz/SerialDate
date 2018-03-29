package test;

import junit.framework.TestCase;
import main.*;

import java.util.GregorianCalendar;

import static main.Day.*;
import static main.Month.*;

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
        assertEquals(1, parse("1").toInt());
        assertEquals(2, parse("2").toInt());
        assertEquals(3, parse("3").toInt());
        assertEquals(4, parse("4").toInt());
        assertEquals(5, parse("5").toInt());
        assertEquals(6, parse("6").toInt());
        assertEquals(7, parse("7").toInt());
        assertEquals(8, parse("8").toInt());
        assertEquals(9, parse("9").toInt());
        assertEquals(10, parse("10").toInt());
        assertEquals(11, parse("11").toInt());
        assertEquals(12, parse("12").toInt());

        try {
            parse("Hello");
            fail("Invalid month string should throw exception.");
        } catch (IllegalArgumentException e) {
        }

        for (int m = 1; m <= 12; m++) {
            assertEquals(m, Month.fromInt(m).toInt());
        }

        assertEquals(1, parse("jan").toInt());
        assertEquals(2, parse("feb").toInt());
        assertEquals(3, parse("mar").toInt());
        assertEquals(4, parse("apr").toInt());
        assertEquals(5, parse("may").toInt());
        assertEquals(6, parse("jun").toInt());
        assertEquals(7, parse("jul").toInt());
        assertEquals(8, parse("aug").toInt());
        assertEquals(9, parse("sep").toInt());
        assertEquals(10, parse("oct").toInt());
        assertEquals(11, parse("nov").toInt());
        assertEquals(12, parse("dec").toInt());

        assertEquals(1, parse("JAN").toInt());
        assertEquals(2, parse("FEB").toInt());
        assertEquals(3, parse("MAR").toInt());
        assertEquals(4, parse("APR").toInt());
        assertEquals(5, parse("MAY").toInt());
        assertEquals(6, parse("JUN").toInt());
        assertEquals(7, parse("JUL").toInt());
        assertEquals(8, parse("AUG").toInt());
        assertEquals(9, parse("SEP").toInt());
        assertEquals(10, parse("OCT").toInt());
        assertEquals(11, parse("NOV").toInt());
        assertEquals(12, parse("DEC").toInt());

        assertEquals(1, parse("january").toInt());
        assertEquals(2, parse("february").toInt());
        assertEquals(3, parse("march").toInt());
        assertEquals(4, parse("april").toInt());
        assertEquals(5, parse("may").toInt());
        assertEquals(6, parse("june").toInt());
        assertEquals(7, parse("july").toInt());
        assertEquals(8, parse("august").toInt());
        assertEquals(9, parse("september").toInt());
        assertEquals(10, parse("october").toInt());
        assertEquals(11, parse("november").toInt());
        assertEquals(12, parse("december").toInt());

        assertEquals(1, parse("JANUARY").toInt());
        assertEquals(2, parse("FEBRUARY").toInt());
        assertEquals(3, parse("MARCH").toInt());
        assertEquals(4, parse("APRIL").toInt());
        assertEquals(5, parse("MAY").toInt());
        assertEquals(6, parse("JUNE").toInt());
        assertEquals(7, parse("JULY").toInt());
        assertEquals(8, parse("AUGUST").toInt());
        assertEquals(9, parse("SEPTEMBER").toInt());
        assertEquals(10, parse("OCTOBER").toInt());
        assertEquals(11, parse("NOVEMBER").toInt());
        assertEquals(12, parse("DECEMBER").toInt());
    }

    public void testIsLeapYear() throws Exception {
        assertFalse(DateUtil.isLeapYear(1900));
        assertFalse(DateUtil.isLeapYear(1901));
        assertFalse(DateUtil.isLeapYear(1902));
        assertFalse(DateUtil.isLeapYear(1903));
        assertTrue(DateUtil.isLeapYear(1904));
        assertTrue(DateUtil.isLeapYear(1908));
        assertFalse(DateUtil.isLeapYear(1955));
        assertTrue(DateUtil.isLeapYear(1964));
        assertTrue(DateUtil.isLeapYear(1980));
        assertTrue(DateUtil.isLeapYear(2000));
        assertFalse(DateUtil.isLeapYear(2001));
        assertFalse(DateUtil.isLeapYear(2100));
    }

    public void testLeapYearCount() throws Exception {
        assertEquals(0, DateUtil.leapYearCount(1900));
        assertEquals(0, DateUtil.leapYearCount(1901));
        assertEquals(0, DateUtil.leapYearCount(1902));
        assertEquals(0, DateUtil.leapYearCount(1903));
        assertEquals(1, DateUtil.leapYearCount(1904));
        assertEquals(1, DateUtil.leapYearCount(1905));
        assertEquals(1, DateUtil.leapYearCount(1906));
        assertEquals(1, DateUtil.leapYearCount(1907));
        assertEquals(2, DateUtil.leapYearCount(1908));
        assertEquals(24, DateUtil.leapYearCount(1999));
        assertEquals(25, DateUtil.leapYearCount(2001));
        assertEquals(49, DateUtil.leapYearCount(2101));
        assertEquals(73, DateUtil.leapYearCount(2201));
        assertEquals(97, DateUtil.leapYearCount(2301));
        assertEquals(122, DateUtil.leapYearCount(2401));
    }

    public void testLastDayOfMonth() throws Exception {
        assertEquals(31, DateUtil.lastDayOfMonth(JANUARY, 1901));
        assertEquals(28, DateUtil.lastDayOfMonth(FEBRUARY, 1901));
        assertEquals(31, DateUtil.lastDayOfMonth(MARCH, 1901));
        assertEquals(30, DateUtil.lastDayOfMonth(APRIL, 1901));
        assertEquals(31, DateUtil.lastDayOfMonth(MAY, 1901));
        assertEquals(30, DateUtil.lastDayOfMonth(JUNE, 1901));
        assertEquals(31, DateUtil.lastDayOfMonth(JULY, 1901));
        assertEquals(31, DateUtil.lastDayOfMonth(AUGUST, 1901));
        assertEquals(30, DateUtil.lastDayOfMonth(SEPTEMBER, 1901));
        assertEquals(31, DateUtil.lastDayOfMonth(OCTOBER, 1901));
        assertEquals(30, DateUtil.lastDayOfMonth(NOVEMBER, 1901));
        assertEquals(31, DateUtil.lastDayOfMonth(DECEMBER, 1901));
        assertEquals(29, DateUtil.lastDayOfMonth(FEBRUARY, 1904));
    }

    public void testAddDays() throws Exception {
        DayDate newYears = d(1, JANUARY.toInt(), 1900);
        assertEquals(d(2, JANUARY.toInt(), 1900), newYears.plusDays(1));
        assertEquals(d(1, FEBRUARY.toInt(), 1900), newYears.plusDays(31));
        assertEquals(d(1, JANUARY.toInt(), 1901), newYears.plusDays(365));
        assertEquals(d(31, DECEMBER.toInt(), 1904), newYears.plusDays(5 * 365));
    }

    private SpreadsheetDayDate d(int day, int month, int year) {
        return new SpreadsheetDayDate(day, month, year);
    }

    public void testAddMonths() throws Exception {
        assertEquals(d(1, FEBRUARY.toInt(), 1900), d(1, JANUARY.toInt(), 1900).plusMonths(1));
        assertEquals(d(28, FEBRUARY.toInt(), 1900), d(31, JANUARY.toInt(), 1900).plusMonths(1));
        assertEquals(d(28, FEBRUARY.toInt(), 1900), d(30, JANUARY.toInt(), 1900).plusMonths(1));
        assertEquals(d(28, FEBRUARY.toInt(), 1900), d(29, JANUARY.toInt(), 1900).plusMonths(1));
        assertEquals(d(28, FEBRUARY.toInt(), 1900), d(28, JANUARY.toInt(), 1900).plusMonths(1));
        assertEquals(d(27, FEBRUARY.toInt(), 1900), d(27, JANUARY.toInt(), 1900).plusMonths(1));

        assertEquals(d(30, JUNE.toInt(), 1900), d(31, JANUARY.toInt(), 1900).plusMonths(5));
        assertEquals(d(30, JUNE.toInt(), 1901), d(31, JANUARY.toInt(), 1900).plusMonths(17));
        assertEquals(d(29, FEBRUARY.toInt(), 1904), d(31, JANUARY.toInt(), 1900).plusMonths(49));
    }

    public void testAddYears() throws Exception {
        assertEquals(d(1, JANUARY.toInt(), 1901), d(1, JANUARY.toInt(), 1900).plusYear(1));
        assertEquals(d(28, FEBRUARY.toInt(), 1905), d(29, FEBRUARY.toInt(), 1904).plusYear(1));
        assertEquals(d(28, FEBRUARY.toInt(), 1905), d(28, FEBRUARY.toInt(), 1904).plusYear(1));
        assertEquals(d(28, FEBRUARY.toInt(), 1904), d(28, FEBRUARY.toInt(), 1903).plusYear(1));
    }

    public void testGetPreviousDayOfWeek() throws Exception {
        assertEquals(d(24, FEBRUARY.toInt(), 2006), d(1, MARCH.toInt(), 2006).getPreviousDayOfWeek(FRIDAY));
        assertEquals(d(22, FEBRUARY.toInt(), 2006),  d(1, MARCH.toInt(), 2006).getPreviousDayOfWeek(WEDNESDAY));
        assertEquals(d(29, FEBRUARY.toInt(), 2004), d(3, MARCH.toInt(), 2004).getPreviousDayOfWeek(SUNDAY));
        assertEquals(d(29, DECEMBER.toInt(), 2004), d(5, JANUARY.toInt(), 2005).getPreviousDayOfWeek(WEDNESDAY));

//        try {
//            getPreviousDayOfWeek(-1, d(1, Month.JANUARY.toInt(), 2006));
//            fail("Invalid day of week code should throw exception");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testGetFollowingDayOfWeek() throws Exception {
        assertEquals(d(1, JANUARY.toInt(), 2005), d(25, DECEMBER.toInt(), 2004).getFollowingDayOfWeek(SATURDAY));
        assertEquals(d(1, JANUARY.toInt(), 2005), d(26, DECEMBER.toInt(), 2004).getFollowingDayOfWeek(SATURDAY));
        // test case error in clean code book
        assertEquals(d(6, MARCH.toInt(), 2004), d(28, FEBRUARY.toInt(), 2004).getFollowingDayOfWeek(SATURDAY));

//        try {
//            getFollowingDayOfWeek(-1, d(1, Month.JANUARY.toInt(), 2006));
//            fail("Invalid day of week code should throw exception.");
//        } catch (IllegalArgumentException e) {
//        }
    }

    public void testGetNearestDayOfWeek() throws Exception {
        assertEquals(d(16, APRIL.toInt(), 2006), d(16, APRIL.toInt(), 2006).getNearestDayOfWeek(SUNDAY));
        assertEquals(d(16, APRIL.toInt(), 2006), d(17, APRIL.toInt(), 2006).getNearestDayOfWeek(SUNDAY));
        assertEquals(d(16, APRIL.toInt(), 2006), d(18, APRIL.toInt(), 2006).getNearestDayOfWeek(SUNDAY));
        assertEquals(d(16, APRIL.toInt(), 2006), d(19, APRIL.toInt(), 2006).getNearestDayOfWeek(SUNDAY));
        assertEquals(d(23, APRIL.toInt(), 2006), d(20, APRIL.toInt(), 2006).getNearestDayOfWeek(SUNDAY));
        assertEquals(d(23, APRIL.toInt(), 2006), d(21, APRIL.toInt(), 2006).getNearestDayOfWeek(SUNDAY));
        assertEquals(d(23, APRIL.toInt(), 2006), d(22, APRIL.toInt(), 2006).getNearestDayOfWeek(SUNDAY));

        assertEquals(d(17, APRIL.toInt(), 2006), d(16, APRIL.toInt(), 2006).getNearestDayOfWeek(MONDAY));
        assertEquals(d(17, APRIL.toInt(), 2006), d(17, APRIL.toInt(), 2006).getNearestDayOfWeek(MONDAY));
        assertEquals(d(17, APRIL.toInt(), 2006), d(18, APRIL.toInt(), 2006).getNearestDayOfWeek(MONDAY));
        assertEquals(d(17, APRIL.toInt(), 2006), d(19, APRIL.toInt(), 2006).getNearestDayOfWeek(MONDAY));
        assertEquals(d(17, APRIL.toInt(), 2006), d(20, APRIL.toInt(), 2006).getNearestDayOfWeek(MONDAY));
        assertEquals(d(24, APRIL.toInt(), 2006), d(21, APRIL.toInt(), 2006).getNearestDayOfWeek(MONDAY));
        assertEquals(d(24, APRIL.toInt(), 2006), d(22, APRIL.toInt(), 2006).getNearestDayOfWeek(MONDAY));

        assertEquals(d(18, APRIL.toInt(), 2006), d(16, APRIL.toInt(), 2006).getNearestDayOfWeek(TUESDAY));
        assertEquals(d(18, APRIL.toInt(), 2006), d(17, APRIL.toInt(), 2006).getNearestDayOfWeek(TUESDAY));
        assertEquals(d(18, APRIL.toInt(), 2006), d(18, APRIL.toInt(), 2006).getNearestDayOfWeek(TUESDAY));
        assertEquals(d(18, APRIL.toInt(), 2006), d(19, APRIL.toInt(), 2006).getNearestDayOfWeek(TUESDAY));
        assertEquals(d(18, APRIL.toInt(), 2006), d(20, APRIL.toInt(), 2006).getNearestDayOfWeek(TUESDAY));
        assertEquals(d(18, APRIL.toInt(), 2006), d(21, APRIL.toInt(), 2006).getNearestDayOfWeek(TUESDAY));
        assertEquals(d(25, APRIL.toInt(), 2006), d(22, APRIL.toInt(), 2006).getNearestDayOfWeek(TUESDAY));

        assertEquals(d(19, APRIL.toInt(), 2006), d(16, APRIL.toInt(), 2006).getNearestDayOfWeek(WEDNESDAY));
        assertEquals(d(19, APRIL.toInt(), 2006), d(17, APRIL.toInt(), 2006).getNearestDayOfWeek(WEDNESDAY));
        assertEquals(d(19, APRIL.toInt(), 2006), d(18, APRIL.toInt(), 2006).getNearestDayOfWeek(WEDNESDAY));
        assertEquals(d(19, APRIL.toInt(), 2006), d(19, APRIL.toInt(), 2006).getNearestDayOfWeek(WEDNESDAY));
        assertEquals(d(19, APRIL.toInt(), 2006), d(20, APRIL.toInt(), 2006).getNearestDayOfWeek(WEDNESDAY));
        assertEquals(d(19, APRIL.toInt(), 2006), d(21, APRIL.toInt(), 2006).getNearestDayOfWeek(WEDNESDAY));
        assertEquals(d(19, APRIL.toInt(), 2006), d(22, APRIL.toInt(), 2006).getNearestDayOfWeek(WEDNESDAY));

        assertEquals(d(13, APRIL.toInt(), 2006), d(16, APRIL.toInt(), 2006).getNearestDayOfWeek(THURSDAY));
        assertEquals(d(20, APRIL.toInt(), 2006), d(17, APRIL.toInt(), 2006).getNearestDayOfWeek(THURSDAY));
        assertEquals(d(20, APRIL.toInt(), 2006), d(18, APRIL.toInt(), 2006).getNearestDayOfWeek(THURSDAY));
        assertEquals(d(20, APRIL.toInt(), 2006), d(19, APRIL.toInt(), 2006).getNearestDayOfWeek(THURSDAY));
        assertEquals(d(20, APRIL.toInt(), 2006), d(20, APRIL.toInt(), 2006).getNearestDayOfWeek(THURSDAY));
        assertEquals(d(20, APRIL.toInt(), 2006), d(21, APRIL.toInt(), 2006).getNearestDayOfWeek(THURSDAY));
        assertEquals(d(20, APRIL.toInt(), 2006), d(22, APRIL.toInt(), 2006).getNearestDayOfWeek(THURSDAY));

        assertEquals(d(14, APRIL.toInt(), 2006), d(16, APRIL.toInt(), 2006).getNearestDayOfWeek(FRIDAY));
        assertEquals(d(14, APRIL.toInt(), 2006), d(17, APRIL.toInt(), 2006).getNearestDayOfWeek(FRIDAY));
        assertEquals(d(21, APRIL.toInt(), 2006), d(18, APRIL.toInt(), 2006).getNearestDayOfWeek(FRIDAY));
        assertEquals(d(21, APRIL.toInt(), 2006), d(19, APRIL.toInt(), 2006).getNearestDayOfWeek(FRIDAY));
        assertEquals(d(21, APRIL.toInt(), 2006), d(20, APRIL.toInt(), 2006).getNearestDayOfWeek(FRIDAY));
        assertEquals(d(21, APRIL.toInt(), 2006), d(21, APRIL.toInt(), 2006).getNearestDayOfWeek(FRIDAY));
        assertEquals(d(21, APRIL.toInt(), 2006), d(22, APRIL.toInt(), 2006).getNearestDayOfWeek(FRIDAY));

        assertEquals(d(15, APRIL.toInt(), 2006), d(16, APRIL.toInt(), 2006).getNearestDayOfWeek(SATURDAY));
        assertEquals(d(15, APRIL.toInt(), 2006), d(17, APRIL.toInt(), 2006).getNearestDayOfWeek(SATURDAY));
        assertEquals(d(15, APRIL.toInt(), 2006), d(18, APRIL.toInt(), 2006).getNearestDayOfWeek(SATURDAY));
        assertEquals(d(22, APRIL.toInt(), 2006), d(19, APRIL.toInt(), 2006).getNearestDayOfWeek(SATURDAY));
        assertEquals(d(22, APRIL.toInt(), 2006), d(20, APRIL.toInt(), 2006).getNearestDayOfWeek(SATURDAY));
        assertEquals(d(22, APRIL.toInt(), 2006), d(21, APRIL.toInt(), 2006).getNearestDayOfWeek(SATURDAY));
        assertEquals(d(22, APRIL.toInt(), 2006), d(22, APRIL.toInt(), 2006).getNearestDayOfWeek(SATURDAY));

//        try {
//            getNearestDayOfWeek(-1, d(1, Month.JANUARY.toInt(), 2006));
//            fail("Invalid day of week code should throw exception.");
//        } catch (IllegalArgumentException e) {}
    }

    public void testEndOfCurrentMonth() throws Exception {
        DayDate d = DayDateFactory.makeDate(2);
        assertEquals(d(31, JANUARY.toInt(), 2006), d(1, JANUARY.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(28, FEBRUARY.toInt(), 2006), d(1, FEBRUARY.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(31, MARCH.toInt(), 2006), d(1, MARCH.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(30, APRIL.toInt(), 2006), d(1, APRIL.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(31, MAY.toInt(), 2006), d(1, MAY.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(30, JUNE.toInt(), 2006), d(1, JUNE.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(31, JULY.toInt(), 2006), d(1, JULY.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(31, AUGUST.toInt(), 2006), d(1, AUGUST.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(30, SEPTEMBER.toInt(), 2006), d(1, SEPTEMBER.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(31, OCTOBER.toInt(), 2006), d(1, OCTOBER.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(30, NOVEMBER.toInt(), 2006), d(1, NOVEMBER.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(31, DECEMBER.toInt(), 2006), d(1, DECEMBER.toInt(), 2006).getEndOfCurrentMonth());
        assertEquals(d(29, FEBRUARY.toInt(), 2008), d(1, FEBRUARY.toInt(), 2008).getEndOfCurrentMonth());
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
        DayDate dayDate = DayDateFactory.makeDate(1, JANUARY.toInt(), 1900);
        assertEquals(1, dayDate.getDayOfMonth());
        assertEquals(JANUARY, dayDate.getMonth());
        assertEquals(1900, dayDate.getYear());
        assertEquals(2, dayDate.getOrdinalDay());
    }

    public void testCreateInstanceFromSerial() throws Exception {
        assertEquals(d(1, JANUARY.toInt(), 1900), DayDateFactory.makeDate(2));
        assertEquals(d(1, JANUARY.toInt(), 1901), DayDateFactory.makeDate(367));
    }

    public void testCreateInstanceFromJavaDate() throws Exception {
        assertEquals(d(1, JANUARY.toInt(), 1900),
                DayDateFactory.makeDate(new GregorianCalendar(1900, 0, 1).getTime()));

        assertEquals(d(1, JANUARY.toInt(), 2006),
                DayDateFactory.makeDate(new GregorianCalendar(2006, 0, 1).getTime()));
    }
}
