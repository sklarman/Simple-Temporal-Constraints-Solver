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
    public void differenceBetweenBeginningAndEndOfDate() {

        String date = "2017-01-01";
        TemporalDataConverter converter = new TemporalDataConverter(3);
        long begin = converter.getDateBeginning(date);
        long end = converter.getDateEnd(date);

        int diff = (int) (end - begin);

        assertEquals(24, diff);

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