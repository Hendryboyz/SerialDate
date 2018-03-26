package main;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SpreadSheetDayDateFactory extends DayDateFactory {
    @Override
    protected DayDate _makeDate(int ordinal) {
        return new SpreadsheetDayDate(ordinal);
    }

    @Override
    protected DayDate _makeDate(int day, DayDate.Month month, int year) {
        return new SpreadsheetDayDate(day, month, year);
    }

    @Override
    protected DayDate _makeDate(int day, int month, int year) {
        return new SpreadsheetDayDate(day, month, year);
    }

    @Override
    protected DayDate _makeDate(Date date) {
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return new SpreadsheetDayDate(calendar.get(Calendar.DATE),
                DayDate.Month.make(calendar.get(Calendar.MONTH + 1)),
                calendar.get(Calendar.YEAR));
    }

    @Override
    protected int _getMinimumYear() {
        return SpreadsheetDayDate.MINIMUM_YEAR_SUPPORTED;
    }

    @Override
    protected int _getMaximumYear() {
        return SpreadsheetDayDate.MAXIMUM_YEAR_SUPPORTED;
    }
}
