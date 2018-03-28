package main;

public class SpreadsheetDayDate extends DayDate {

    /** For serialization */
    private static final long serialVersionUID = -2039586703574454461L;

    public static final int EARLIEST_DATE_ORDINAL = 2; // 1/1/1900

    public static final int LATEST_DATE_ORDINAL = 2958465; // 12/31/9999

    public static final int MINIMUM_YEAR_SUPPORTED = 1900;

    public static final int MAXIMUM_YEAR_SUPPORTED = 9999;

    private static final int[] AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH =
            {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    private static final int[] LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH =
            {0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366};

    /**
     * The day number (1-Jan-1900 = 2, 2-Jan-1900 = 3, ..., 31-Dec-9999 = 2958465)
     */
    private int serial;

    /** The day of the month (1 to 28, 29, 30 or 31 depending on the month). */
    private int day;

    /** The month of the year (1 to 12) */
    private Month month;

    /** The year (1900 to 9999). */
    private int year;

    /** An optional description for the date. */
    private String description;

    /**
     * Creates a new date instance
     * @param day the day (in the range 1 to 28/29/30/31).
     * @param month the month (in the range 1 to 12).
     * @param year the year (in the range 1900 to 9999).
     */
    public SpreadsheetDayDate(final int day, final int month, final int year) {

        if ((year >= 1900) && (year <= 9999)) {
            this.year = year;
        }
        else {
            throw new IllegalArgumentException(
                    "The 'year' argument must be in range 1900 to 9999."
            );
        }

        if ((month >= Month.JANUARY.toInt())
                && (month <= Month.DECEMBER.toInt())) {
            this.month = Month.fromInt(month);
        }
        else {
            throw new IllegalArgumentException(
                    "The 'month' argument must be in range 1 to 12."
            );
        }

        if ((day >= 1) && (day <= DateUtil.lastDayOfMonth(Month.fromInt(month), year))) {
            this.day = day;
        }
        else {
            throw new IllegalArgumentException(
                    "Invalid 'day' argument."
            );
        }

        // the serial number needs to be synchronised with the day-month-year....
        this.serial = calcSerial(day, month, year);

        this.description = null;
    }

    public SpreadsheetDayDate(final int day, final Month month, final int year) {

        if ((year >= 1900) && (year <= 9999)) {
            this.year = year;
        }
        else {
            throw new IllegalArgumentException(
                    "The 'year' argument must be in range 1900 to 9999."
            );
        }

        this.month = month;

        if ((day >= 1) && (day <= DateUtil.lastDayOfMonth(month, year))) {
            this.day = day;
        }
        else {
            throw new IllegalArgumentException(
                    "Invalid 'day' argument."
            );
        }

        // the serial number needs to be synchronised with the day-month-year....
        this.serial = calcSerial(day, month.toInt(), year);

        this.description = null;
    }

    /**
     * Calculate the serial number from the day, month and year.
     * <P>
     *     1-Jan-1900 = 2
     * </P>
     * @param d the day
     * @param m the month
     * @param y the year
     * @return the serial number from the day, month and year
     */
    private int calcSerial(final int d, final int m, final int y) {
        final int yy = ((y - 1900) * 365) + leapYearCount(y - 1);
        int mm = AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH[m];
        if (m > Month.FEBRUARY.toInt()) {
            if (DateUtil.isLeapYear(y)) {
                mm = mm + 1;
            }
        }
        final int dd = d;
        return yy + mm + dd + 1;
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
     * Standard constructor - Creates a new date object representing the
     * specified day number (which should be in the range 2 to 2958465).
     * @param serial the serial number for the day (range: 2 to 2958465).
     */
    public SpreadsheetDayDate(int serial) {
        if ((serial >= EARLIEST_DATE_ORDINAL) && (serial <= LATEST_DATE_ORDINAL)) {
            this.serial = serial;
        }
        else {
            throw new IllegalArgumentException(
                    "SpreadsheetDayDate: Serial must be in range 2 to 2958465."
            );
        }
        // the day-month-year needs to be synchronised with the serial number...
        calcDayMonthYear();
    }

    /**
     * Calculate the day, month and year from the serial number
     */
    private void calcDayMonthYear() {
        // get the year from the serial date
        final int days = this.serial - EARLIEST_DATE_ORDINAL;

        // overestimated because we ignored leap days
        final int overestimatedYYYY = 1900 + (days / 365);
        final int leaps = leapYearCount(overestimatedYYYY);
        final int nonleapdays = days - leaps;
        // underestimated because we overestimated year
        int underestimatedYYYY = 1900 + (nonleapdays / 365);

        if (underestimatedYYYY == overestimatedYYYY) {
            this.year = underestimatedYYYY;
        }
        else {
            int ss1 = calcSerial(1, 1, underestimatedYYYY);
            while (ss1 <= this.serial) {
                underestimatedYYYY = underestimatedYYYY + 1;
                ss1 = calcSerial(1, 1, underestimatedYYYY);
            }
            this.year = underestimatedYYYY - 1;
        }

        final int ss2 = calcSerial(1, 1, this.year);

        int[] daysToEndOfPrecedingMonth
                = AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH;

        if (DateUtil.isLeapYear(this.year)) {
            daysToEndOfPrecedingMonth = LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH;
        }

        // get the month from the serial date
        int mm = 1;
        int sss = ss2 + daysToEndOfPrecedingMonth[mm] - 1;
        while (sss < this.serial) {
            mm = mm + 1;
            sss = ss2 + daysToEndOfPrecedingMonth[mm] - 1;
        }
        this.month = Month.fromInt(mm - 1);

        // what's left is d(+1);
        this.day = this.serial - ss2
                - daysToEndOfPrecedingMonth[this.month.toInt()] + 1;
    }

    /**
     * Returns the description that is attached to the date. It is not
     * required that a date have a description, but for some applications it
     * is useful
     * @return The description that is attached to the date.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description for the date
     * @param description the description for this date (<code>null</code>
     *                    permitted).
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns the serial number for the date, where 1 January 1900 = 2
     * (this corresponds, almost, to the numbering system used in Microsoft
     * Excel for Windows and Lotus 1-2-3).
     * @return The serial number of this date.
     */
    @Override
    public int getOrdinalDay() {
        return this.serial;
    }

    /**
     * Returns the year (assume a valid range of 1900 and 9999).
     * @return The year
     */
    @Override
    public int getYear() {
        return this.year;
    }

    /**
     * Returns the month (January = 1, February = 2, March = 3)
     * @return The month of the year
     */
    @Override
    public Month getMonth() {
        return this.month;
    }

    /**
     * Returns the day of the month.
     * @return The day of the month.
     */
    @Override
    public int getDayOfMonth() {
        return this.day;
    }

    @Override
    public Day getDayOfWeekForOrdinalZero() {
        return Day.SUNDAY;
    }

    /**
     * Tests the equality of this date with an arbitrary object
     * <P>
     *     This method will return true ONLY if the object is an instance of the
     *     {@link DayDate} base class, and it represents the same day as this
     *     {@link SpreadsheetDayDate}
     * </P>
     * @param object the object to daySince (<code>null</code> permitted)
     * @return A boolean
     */
    public boolean equals(final Object object) {
        if (object instanceof DayDate) {
            final DayDate s = (DayDate) object;
            return (s.getOrdinalDay() == this.getOrdinalDay());
        }
        else {
            return false;
        }
    }

    /**
     * Returns a hash code for this object instance.
     * @return A hash code.
     */
    public int hashCode() {
        return getOrdinalDay();
    }

    /**
     * Implements the method required the Comparable interface.
     * @param other the other object (usually another DayDate).
     * @return A negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(final Object other) {
        return daySince((DayDate) other);
    }
}
