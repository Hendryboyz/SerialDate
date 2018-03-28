package test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;

import static main.Day.*;
import static main.Month.*;
import java.io.*;

public class SerialDayDateTest extends TestCase {

    /** DayDate representing November 9. */
    private DayDate nov9Y2001;

    /**
     * Creates a new test case.
     * @param name the name
     */
    public SerialDayDateTest(final String name) {
        super(name);
    }

    /**
     * Returns a test suit for the JUnit test runner.
     * @return The test suit.
     */
    public static Test suit() {
        return new TestSuite(SerialDayDateTest.class);
    }

    /**
     * Problem set up.
     */
    protected void setUp() {
        this.nov9Y2001 = DayDate.makeDate(9, Month.NOVEMBER.index, 2001);
    }

    /**
     * 9 Nov 2001 plus two months should be 9 Jun 2002.
     */
    public void testAddMonthsTo9Nov2001() {
        final DayDate jan9Y2002 = this.nov9Y2001.plusMonths(2);
        final DayDate answer = DayDate.makeDate(9, 1, 2002);
        assertEquals(answer, jan9Y2002);
    }

    /**
     * A test case for a reported bug, now fixed
     */
    public void testAddMonthsTo5Oct2003() {
        final DayDate d1 = DayDate.makeDate(5, Month.OCTOBER.index, 2003);
        final DayDate d2 = d1.plusMonths(2);
        assertEquals(d2, DayDate.makeDate(5, Month.DECEMBER.index, 2003));
    }

    /**
     * A test case for a reported bug, now fixed
     */
    public void testAddMonthsTo1Jan2003() {
        final DayDate d1 = DayDate.makeDate(1, Month.JANUARY.index, 2003);
        final DayDate d2 = d1.plusMonths(0);
        assertEquals(d2, d1);
    }

    /**
     * Monday preceding 9 November 2001 should be 5 November.
     */
    public void testMondayPrecedingFriday9Nov2001() {
        DayDate mondayBefore =
                this.nov9Y2001.getPreviousDayOfWeek(MONDAY);
        assertEquals(5, mondayBefore.getDayOfMonth());
    }

    /**
     * Monday following Friday 9 November 2001 should be 12 November
     */
    public void testMondayFollowingFriday9Nov2001() {
       DayDate mondayAfter =
               this.nov9Y2001.getFollowingDayOfWeek(MONDAY);
       assertEquals(12, mondayAfter.getDayOfMonth());
    }

    /**
     * Monday nearest Friday 9 November 2001 should be 12 November.
     */
    public void testMondayNearestFridayNov2001() {
        DayDate mondayNearest =
                this.nov9Y2001.getNearestDayOfWeek(MONDAY);
        assertEquals(12, mondayNearest.getDayOfMonth());
    }

    /**
     * The Monday nearest to 22 nd January 1940 falls on the 19th.
     */
    public void testMondayNearest22Jan1970() {
        DayDate jan22Y1970 = DayDate.makeDate(22, Month.JANUARY.index, 1970);
        DayDate mondayNearest = jan22Y1970.getNearestDayOfWeek(MONDAY);
        assertEquals(19, mondayNearest.getDayOfMonth());
    }

    /**
     * Problem that the conversion of days to strings returns the right result. Actually, this
     * result depends on the Locale so this test needs to be modified.
     */
    public void testWeekdayCodeToString() {
        assertEquals("Saturday", SATURDAY.toString());
    }

    /**
     * Test the conversion of a string to a weekday. Note that this test will fail if the
     * default locale doesn't use English weekday names... devise a better test!
     */
    public void testStringToWeekday() {
        assertEquals(WEDNESDAY, Day.parseDay("Wednesday"));

        assertEquals(WEDNESDAY, Day.parseDay(" Wednesday "));

        assertEquals(WEDNESDAY, Day.parseDay("Wed"));
    }

    /**
     * Test the conversion of a string to a month. Note that this test will fail if the
     * default locale doesn't use English weekday names... devise a better test!
     */
    public void testStringToMonthCode() {
        assertEquals(Month.JANUARY, parseMonth("January"));

        assertEquals(Month.JANUARY, parseMonth(" January "));

        assertEquals(Month.JANUARY, parseMonth("Jan"));
    }

    /**
     * Tests the conversion of a month code to a string
     */
    public void testMonthCodeToStringCode() {
        assertEquals("December", Month.DECEMBER.toString());
    }

    /**
     * 1900 is not a leap year
     */
    public void testIsNotLeapYear1900() {
        assertTrue(!DayDate.isLeapYear(1900));
    }

    /**
     * 2000 is a leap year
     */
    public void testIsLeapYear2000() {
        assertTrue(DayDate.isLeapYear(2000));
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1899 is 0
     */
    public void testIsLeapYearCount1899() {
        assertEquals(SpreadsheetDayDate.leapYearCount(1899), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1903 is 0
     */
    public void testIsLeapYearCount1903() {
        assertEquals(SpreadsheetDayDate.leapYearCount(1903), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1904 is 1
     */
    public void testIsLeapYearCount1904() {
        assertEquals(SpreadsheetDayDate.leapYearCount(1904), 1);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1999 is 24
     */
    public void testIsLeapYearCount1999() {
        assertEquals(SpreadsheetDayDate.leapYearCount(1999), 24);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 2000 is 25
     */
    public void testIsLeapYearCount2000() {
        assertEquals(SpreadsheetDayDate.leapYearCount(2000), 25);
    }

    /**
     * Serial an instance, store it, and heck for equality
     */
    public void testSerialization() {

        DayDate d1 = DayDate.makeDate(15, 4, 2000);
        DayDate d2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));

            d2 = (DayDate) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

        assertEquals(d1, d2);
    }

    /**
     * A test for bug report 1096282 (now fixed).
     */
    public void test1096282() {
        DayDate d = DayDate.makeDate(29, 2, 2004);
        d = d.plusYear(1);
        DayDate expected = DayDate.makeDate(28, 2, 2005);
        assertTrue(d.isOn(expected));
    }

    /**
     * Miscellaneous tests for add Months() method
     */
    public void testAddMonths() {
        DayDate d1 = DayDate.makeDate(31, 5, 2004);

        DayDate d2 = d1.plusMonths(1);
        assertEquals(30, d2.getDayOfMonth());
        assertEquals(JUNE, d2.getMonth());
        assertEquals(2004, d2.getYear());

        DayDate d3 = d1.plusMonths(2);
        assertEquals(31, d3.getDayOfMonth());
        assertEquals(JULY, d3.getMonth());
        assertEquals(2004, d3.getYear());

        DayDate d4 = d1.plusMonths(1).plusMonths(1);
        assertEquals(30, d4.getDayOfMonth());
        assertEquals(JULY, d4.getMonth());
        assertEquals(2004, d4.getYear());
    }
}
