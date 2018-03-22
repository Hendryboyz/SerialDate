package test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;

import java.io.*;

public class SerialDateTest extends TestCase {

    /** Date representing November 9. */
    private SerialDate nov9Y2001;

    /**
     * Creates a new test case.
     * @param name the name
     */
    public SerialDateTest(final String name) {
        super(name);
    }

    /**
     * Returns a test suit for the JUnit test runner.
     * @return The test suit.
     */
    public static Test suit() {
        return new TestSuite(SerialDateTest.class);
    }

    /**
     * Problem set up.
     */
    protected void setUp() {
        this.nov9Y2001 = SerialDate.createInstance(9, MonthConstants.NOVEMBER, 2001);
    }

    /**
     * 9 Nov 2001 plus two months should be 9 Jun 2002.
     */
    public void testAddMonthsTo9Nov2001() {
        final SerialDate jan9Y2002 = SerialDate.addMonths(2, this.nov9Y2001);
        final SerialDate answer = SerialDate.createInstance(9, 1, 2002);
        assertEquals(answer, jan9Y2002);
    }

    /**
     * A test case for a reported bug, now fixed
     */
    public void testAddMonthsTo5Oct2003() {
        final SerialDate d1 = SerialDate.createInstance(5, MonthConstants.OCTOBER, 2003);
        final SerialDate d2 = SerialDate.addMonths(2, d1);
        assertEquals(d2, SerialDate.createInstance(5, MonthConstants.DECEMBER, 2003));
    }

    /**
     * A test case for a reported bug, now fixed
     */
    public void testAddMonthsTo1Jan2003() {
        final SerialDate d1 = SerialDate.createInstance(1, MonthConstants.JANUARY, 2003);
        final SerialDate d2 = SerialDate.addMonths(0, d1);
        assertEquals(d2, d1);
    }

    /**
     * Monday preceding 9 November 2001 should be 5 November.
     */
    public void testMondayPrecedingFriday9Nov2001() {
        SerialDate mondayBefore = SerialDate.getPreviousDayOfWeek(
                SerialDate.MONDAY, this.nov9Y2001
        );
        assertEquals(5, mondayBefore.getDayOfMonth());
    }

    /**
     * Monday following Friday 9 November 2001 should be 12 November
     */
    public void testMondayFollowingFriday9Nov2001() {
       SerialDate mondayAfter = SerialDate.getFollowingDayOfWeek(
               SerialDate.MONDAY, this.nov9Y2001
       );
       assertEquals(12, mondayAfter.getDayOfMonth());
    }

    /**
     * Monday nearest Friday 9 November 2001 should be 12 November.
     */
    public void testMondayNearestFridayNov2001() {
        SerialDate mondayNearest = SerialDate.getNearestDayOfWeek(
                SerialDate.MONDAY, this.nov9Y2001
        );
        assertEquals(12, mondayNearest.getDayOfMonth());
    }

    /**
     * The Monday nearest to 22 nd January 1940 falls on the 19th.
     */
    public void testMondayNearest22Jan1970() {
        SerialDate jan22Y1970 = SerialDate.createInstance(22, MonthConstants.JANUARY, 1970);
        SerialDate mondayNearest = SerialDate.getNearestDayOfWeek(SerialDate.MONDAY, jan22Y1970);
        assertEquals(19, mondayNearest.getDayOfMonth());
    }

    /**
     * Problem that the conversion of days to strings returns the right result. Actually, this
     * result depends on the Locale so this test needs to be modified.
     */
    public void testWeekdayCodeToString() {
        final String test = SerialDate.weekdayCodeToString(SerialDate.SATURDAY);
        assertEquals("Saturday", test);
    }

    /**
     * Test the conversion of a string to a weekday. Note that this test will fail if the
     * default locale doesn't use English weekday names... devise a better test!
     */
    public void testStringToWeekday() {
        int weekday = SerialDate.stringToWeekdayCode("Wednesday");
        assertEquals(SerialDate.WEDNESDAY, weekday);

        weekday = SerialDate.stringToWeekdayCode(" Wednesday ");
        assertEquals(SerialDate.WEDNESDAY, weekday);

        weekday = SerialDate.stringToWeekdayCode("Wed");
        assertEquals(SerialDate.WEDNESDAY, weekday);
    }

    /**
     * Test the conversion of a string to a month. Note that this test will fail if the
     * default locale doesn't use English weekday names... devise a better test!
     */
    public void testStringToMonthCode() {
        int m = SerialDate.stringToMonthCode("January");
        assertEquals(SerialDate.JANUARY, m);

        m = SerialDate.stringToMonthCode(" January ");
        assertEquals(SerialDate.JANUARY, m);

        m = SerialDate.stringToMonthCode("Jan");
        assertEquals(SerialDate.JANUARY, m);
    }

    /**
     * Tests the conversion of a month code to a string
     */
    public void testMonthCodeToStringCode() {
        final String test = SerialDate.monthCodeToString(MonthConstants.DECEMBER);
        assertEquals("December", test);
    }

    /**
     * 1900 is not a leap year
     */
    public void testIsNotLeapYear1900() {
        assertTrue(!SerialDate.isLeapYear(1900));
    }

    /**
     * 2000 is a leap year
     */
    public void testIsLeapYear2000() {
        assertTrue(SerialDate.isLeapYear(2000));
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1899 is 0
     */
    public void testIsLeapYearCount1899() {
        assertEquals(SerialDate.leapYearCount(1899), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1903 is 0
     */
    public void testIsLeapYearCount1903() {
        assertEquals(SerialDate.leapYearCount(1903), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1904 is 1
     */
    public void testIsLeapYearCount1904() {
        assertEquals(SerialDate.leapYearCount(1904), 1);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1999 is 24
     */
    public void testIsLeapYearCount1999() {
        assertEquals(SerialDate.leapYearCount(1999), 24);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 2000 is 25
     */
    public void testIsLeapYearCount2000() {
        assertEquals(SerialDate.leapYearCount(2000), 25);
    }

    /**
     * Serial an instance, store it, and heck for equality
     */
    public void testSerialization() {

        SerialDate d1 = SerialDate.createInstance(15, 4, 2000);
        SerialDate d2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));

            d2 = (SerialDate) in.readObject();
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
        SerialDate d = SerialDate.createInstance(29, 2, 2004);
        d = SerialDate.addYears(1, d);
        SerialDate expected = SerialDate.createInstance(28, 2, 2005);
        assertTrue(d.isOn(expected));
    }

    /**
     * Miscellaneous tests for add Months() method
     */
    public void testAddMonths() {
        SerialDate d1 = SerialDate.createInstance(31, 5, 2004);

        SerialDate d2 = SerialDate.addMonths(1, d1);
        assertEquals(30, d2.getDayOfMonth());
        assertEquals(6, d2.getMonth());
        assertEquals(2004, d2.getYYYY());

        SerialDate d3 = SerialDate.addMonths(2, d1);
        assertEquals(31, d3.getDayOfMonth());
        assertEquals(7, d3.getMonth());
        assertEquals(2004, d3.getYYYY());

        SerialDate d4 = SerialDate.addMonths(1, SerialDate.addMonths(1, d1));
        assertEquals(30, d4.getDayOfMonth());
        assertEquals(7, d4.getMonth());
        assertEquals(2004, d4.getYYYY());
    }
}
