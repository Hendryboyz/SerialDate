package main;

public class SpreadsheetDayDate extends DayDate {
    public static final int EARLIEST_DATE_ORDINAL = 2; // 1/1/1900
    public static final int LATEST_DATE_ORDINAL = 2958465; // 12/31/9999
    public static final int MINIMUM_YEAR_SUPPORTED = 1900;
    public static final int MAXIMUM_YEAR_SUPPORTED = 9999;
    private static final int[] AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH =
            {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    private static final int[] LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH =
            {0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366};

    private int ordinalDay;
    private int day;
    private Month month;
    private int year;

    public SpreadsheetDayDate(int day, int month, int year) {
        this(day, Month.fromInt(month), year);
    }

    public SpreadsheetDayDate(int day, Month month, int year) {

        if ((year < MINIMUM_YEAR_SUPPORTED) || (year > MAXIMUM_YEAR_SUPPORTED)) {
            throw new IllegalArgumentException(
                    "The 'year' argument must be in range " +
                            MINIMUM_YEAR_SUPPORTED + "to" + MAXIMUM_YEAR_SUPPORTED + "."
            );
        }
        if ((day < 1) || (day > DateUtil.lastDayOfMonth(month, year))) {
            throw new IllegalArgumentException(
                    "Invalid 'day' argument.");
        }

        this.year = year;
        this.month = month;
        this.day = day;
        this.ordinalDay = calcOrdinal(day, month.toInt(), year);
    }

    public SpreadsheetDayDate(int ordinalDay) {
        if ((ordinalDay >= EARLIEST_DATE_ORDINAL) && (ordinalDay <= LATEST_DATE_ORDINAL)) {
            this.ordinalDay = ordinalDay;
        }
        else {
            throw new IllegalArgumentException(
                    "SpreadsheetDayDate: Serial must be in range 2 to 2958465."
            );
        }
        // the day-month-year needs to be synchronised with the ordinalDay number...
        calcDayMonthYear();
    }

    @Override
    public int getOrdinalDay() {
        return this.ordinalDay;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public Month getMonth() {
        return this.month;
    }

    @Override
    public int getDayOfMonth() {
        return this.day;
    }

    @Override
    protected Day getDayOfWeekForOrdinalZero() {
        return Day.SUNDAY;
    }

    public boolean equals(final Object object) {
        if (object instanceof DayDate) {
            final DayDate s = (DayDate) object;
            return (s.getOrdinalDay() == this.getOrdinalDay());
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        return getOrdinalDay();
    }

    @Override
    public int compareTo(final Object other) {
        return daySince((DayDate) other);
    }

    private int calcOrdinal(int day, int month, int year) {
        int leapDaysForYear = DateUtil.leapYearCount(year - 1);
        int dayUpToYear = ((year - MINIMUM_YEAR_SUPPORTED) * 365) + leapDaysForYear;
        int daysUpToMonth = AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH[month];
        if (DateUtil.isLeapYear(year) && month > Month.FEBRUARY.toInt()) {
            daysUpToMonth = daysUpToMonth + 1;
        }
        int daysInMonth = day - 1;
        return dayUpToYear + daysUpToMonth + daysInMonth + EARLIEST_DATE_ORDINAL;
    }

    private void calcDayMonthYear() {
        int days = ordinalDay - EARLIEST_DATE_ORDINAL;
        int overestimatedYear = MINIMUM_YEAR_SUPPORTED + days / 365;
        int nonleapdays = days - DateUtil.leapYearCount(overestimatedYear);
        int underestimatedYear = MINIMUM_YEAR_SUPPORTED + nonleapdays / 365;

        year = huntForYearContaining(ordinalDay, underestimatedYear);
        int firstOrdinalOfYear = firstOrdinalOfYear(year);
        month = huntForMonthContaining(ordinalDay, firstOrdinalOfYear);
        day = ordinalDay - firstOrdinalOfYear
                - daysBeforeThisMonth(month.toInt());// + 1;
    }

    private Month huntForMonthContaining(int anOrdinal, int firstOrdinalOfYear) {
        int dayIntoThisYear = anOrdinal  - firstOrdinalOfYear;
        int aMonth = 1;
        while (daysBeforeThisMonth(aMonth) < dayIntoThisYear) {
            aMonth = aMonth + 1;
        }

        return Month.fromInt(aMonth - 1);
    }

    private int daysBeforeThisMonth(int aMonth) {
       if (DateUtil.isLeapYear(year)) {
           return LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH[aMonth] - 1;
       }
       else {
           return AGGREGATE_DAYS_TO_END_OF_PRECEDIND_MONTH[aMonth] - 1;
       }
    }

    private int huntForYearContaining(int anOrdinalDay, int startingYear) {
        int aYear = startingYear;
        while (firstOrdinalOfYear(aYear) <= anOrdinalDay) {
            aYear = aYear + 1;
        }

        return aYear - 1;
    }

    private int firstOrdinalOfYear(int year) {
        return calcOrdinal(1, Month.JANUARY.toInt(), year);
    }
}
