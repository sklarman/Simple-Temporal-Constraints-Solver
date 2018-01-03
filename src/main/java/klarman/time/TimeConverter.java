package klarman.time;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;

import javax.print.attribute.standard.MediaSize;

public class TimeConverter {

    /*

    precision:
    0) milliseconds
    1) seconds
    2) minutes
    3) hours (default)
    4) days

    */

    private int precisionFactor;
    private int precision;

    public TimeConverter(int precision) {
        this.precision = precision;
        this.precisionFactor = getFactor(precision);
    }

    public TimeConverter() {
        this.precision = 3;
        this.precisionFactor = getFactor(this.precision);
    }

    private int getFactor(int precision) {
        int mils = 1;
        int secs = 1000;
        int mins = 60;
        int hrs = 60;
        int days = 24;

        switch (precision) {
            case 0: {
                return mils;
            }
            case 1: {
                return secs;
            }
            case 2: {
                return secs * mins;
            }
            case 3: {
                return secs * mins * hrs;
            }
            case 4: {
                return secs * mins * hrs * days;
            }
            default: {
                return secs * mins * hrs;
            }
        }
    }
    

    // this method might return slightly different results depending on the current klarman.time

    public long getDuration(String xsdDuration) {

        //e.g. "P17Y4M"

        Period period = Period.parse(xsdDuration, ISOPeriodFormat.standard());
        Duration duration = period.toDurationFrom(new DateTime());
        return duration.getMillis() / precisionFactor;

    }

    public long getYearBeginning(String xsdYear) {

        //e.g. "2017"

        DateTime date = DateTime.parse(xsdYear + "-01-01", ISODateTimeFormat.date());

        return date.getMillis() / precisionFactor;

    }

    public long getYearEnd(String xsdYear) {

        //e.g. "2017"

        DateTime date = DateTime.parse(xsdYear + "-01-01", ISODateTimeFormat.date());

        return date.plusYears(1).getMillis() / precisionFactor;

    }

    public long getYearMonthBeginning(String xsdYearMonth) {

        //e.g. "2017-10"

        DateTime date = DateTime.parse(xsdYearMonth + "-01", ISODateTimeFormat.date());

        return date.getMillis() / precisionFactor;

    }

    public long getYearMonthEnd(String xsdYearMonth) {

        //e.g. "2017-10"

        DateTime date = DateTime.parse(xsdYearMonth + "-01", ISODateTimeFormat.date());

        return date.plusMonths(1).getMillis() / precisionFactor;

    }

    public long getDateBeginning(String xsdDate) {

        //e.g. "2017-01-01"

        DateTime date = DateTime.parse(xsdDate, ISODateTimeFormat.localDateOptionalTimeParser());

        return date.getMillis() / precisionFactor;

    }

    public long getDateEnd(String xsdDate) {

        //e.g. "2017-01-01"

        DateTime date = DateTime.parse(xsdDate, ISODateTimeFormat.localDateOptionalTimeParser());

        return date.plusDays(1).getMillis() / precisionFactor;

    }

    public long getDateTimeBeginning(String xsdDateTime) {

        //e.g. "2002-10-10T12:00:00-05:00" or "2002-10-10T12:00:00"

        DateTime dateTime = DateTime.parse(xsdDateTime, ISODateTimeFormat.dateTimeParser());

        return dateTime.getMillis() / precisionFactor;

    }

    public long getDateTimeEnd(String xsdDateTime) {

        //e.g. "2002-10-10T12:00:00-05:00" or "2002-10-10T12:00:00"

        DateTime dateTime = DateTime.parse(xsdDateTime, ISODateTimeFormat.dateTimeParser());

        return dateTime.plusSeconds(1).getMillis() / precisionFactor;

    }

    public String getXSDDateTime(long numericTime) {

        String epoch = "1970-01-01T00:00:00Z";

        long millis = numericTime * precisionFactor;

        DateTime zero = DateTime.parse(epoch, ISODateTimeFormat.dateTimeParser());

        DateTime dateTime = zero.plus(millis);

        String output = null;

        if (this.precision==0) {

            output = dateTime.toString();
        }

        if (this.precision>0&&this.precision<4) {

            output = dateTime.toString(ISODateTimeFormat.dateTimeNoMillis());
        }

        if (this.precision==4) {

            output = dateTime.toString(ISODateTimeFormat.date());
        }

        return output;

    }

}
