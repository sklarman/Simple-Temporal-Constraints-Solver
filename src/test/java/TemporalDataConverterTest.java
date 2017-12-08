import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemporalDataConverterTest {

    @Test
    public void durationOfADayInHours() {

        TemporalDataConverter converter = new TemporalDataConverter(3);
        long duration = converter.getDuration("P1D");
        assertEquals(24, (int) duration);

    }

    @Test
    public void durationOfADayInDays() {

        TemporalDataConverter converter = new TemporalDataConverter(4);
        long duration = converter.getDuration("P1D");
        assertEquals(1, (int) duration);

    }

    @Test
    public void differenceBetweenBeginningAndEndOfDateInHours() {

        String date = "2017-01-01";
        TemporalDataConverter converter = new TemporalDataConverter(3);
        long begin = converter.getDateBeginning(date);
        long end = converter.getDateEnd(date);

        int diff = (int) (end - begin);

        assertEquals(24, diff);

    }

    @Test
    public void differenceBetweenBeginningAndEndOfDateInDays() {

        String date = "2017-01-01";
        TemporalDataConverter converter = new TemporalDataConverter(4);
        long begin = converter.getDateBeginning(date);
        long end = converter.getDateEnd(date);

        int diff = (int) (end - begin);

        assertEquals(1, diff);

    }

    @Test
    public void differenceBetweenBeginningAndEndOfYearInDays() {

        String year = "2017";
        TemporalDataConverter converter = new TemporalDataConverter(4);
        long begin = converter.getYearBeginning(year);
        long end = converter.getYearEnd(year);

        int diff = (int) (end - begin);

        assertEquals(365, diff);

    }

    @Test
    public void differenceBetweenBeginningAndEndOfJanuaryInDays() {

        String year = "2017-01";
        TemporalDataConverter converter = new TemporalDataConverter(4);
        long begin = converter.getYearMonthBeginning(year);
        long end = converter.getYearMonthEnd(year);

        int diff = (int) (end - begin);

        assertEquals(31, diff);

    }

    @Test
    public void differenceBetweenBeginningAndEndOfFebruary2017InDays() {

        String year = "2017-02";
        TemporalDataConverter converter = new TemporalDataConverter(4);
        long begin = converter.getYearMonthBeginning(year);
        long end = converter.getYearMonthEnd(year);

        int diff = (int) (end - begin);

        assertEquals(28, diff);

    }

    @Test
    public void differenceBetweenBeginningAndEndOfFebruary2020InDays() {

        String year = "2020-02";
        TemporalDataConverter converter = new TemporalDataConverter(4);
        long begin = converter.getYearMonthBeginning(year);
        long end = converter.getYearMonthEnd(year);

        int diff = (int) (end - begin);

        assertEquals(29, diff);

    }


    @Test
    public void differenceBetweenBeginningAndEndOfDateTime() {

        String dateTime = "2002-10-10T12:00:00-05:00";
        TemporalDataConverter converter = new TemporalDataConverter(1);
        long begin = converter.getDateTimeBeginning(dateTime);
        long end = converter.getDateTimeEnd(dateTime);

        int diff = (int) (end - begin);

        assertEquals(1, diff);

    }

    @Test
    public void differenceBetweenBeginningAndEndOfDateTimeNoTimeZone() {

        String dateTime = "2002-10-10T12:00:00";
        TemporalDataConverter converter = new TemporalDataConverter(1);
        long begin = converter.getDateTimeBeginning(dateTime);
        long end = converter.getDateTimeEnd(dateTime);

        int diff = (int) (end - begin);

        assertEquals(1, diff);

    }

}