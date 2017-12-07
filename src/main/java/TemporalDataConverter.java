import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;

public class TemporalDataConverter {

    /*

    precision:
    0) milliseconds
    1) seconds
    2) minutes
    3) hours

    */

    int precisionFactor;

    public TemporalDataConverter(int precision) {

        this.precisionFactor = getFactor(precision);

    }

    private int getFactor(int precision) {
        int mils = 1;
        int secs = 1000;
        int mins = 60;
        int hrs = 60;

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
            default: {
                return secs * mins * hrs;
            }
        }
    }

    public TemporalDataConverter() {
        this.precisionFactor = getFactor(3);
    }


    // this method might return slightly different results depending on the current time

    public long getDuration(String xsdDuration) {

        //e.g. "P17Y4M"

        Period period = Period.parse(xsdDuration, ISOPeriodFormat.standard());
        Duration duration = period.toDurationFrom(new DateTime());
        return duration.getMillis() / precisionFactor;

    }

    public long getYearBeginning(String xsdYear) {

        //e.g. "2017"

        DateTime date = DateTime.parse(xsdYear + "01-01", ISODateTimeFormat.date());

        return date.getMillis() / precisionFactor;

    }

    public long getYearEnd(String xsdYear) {

        //e.g. "2017"

        DateTime date = DateTime.parse(xsdYear + "01-01", ISODateTimeFormat.date());

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

        DateTime date = DateTime.parse(xsdDate, ISODateTimeFormat.date());

        return date.getMillis() / precisionFactor;

    }

    public long getDateEnd(String xsdDate) {

        //e.g. "2017-01-01"

        DateTime date = DateTime.parse(xsdDate, ISODateTimeFormat.date());

        return date.plusDays(1).getMillis() / precisionFactor;

    }

    public long getDateTimeBeginning(String xsdDateTime) {

        //e.g. "2002-10-10T12:00:00-05:00"

        DateTime dateTime = DateTime.parse(xsdDateTime, ISODateTimeFormat.dateTimeParser());

        return dateTime.getMillis() / precisionFactor;

    }

    public long getDateTimeEnd(String xsdDateTime) {

        //e.g. "2002-10-10T12:00:00-05:00"

        DateTime dateTime = DateTime.parse(xsdDateTime, ISODateTimeFormat.dateTimeParser());

        return dateTime.plusSeconds(1).getMillis() / precisionFactor;

    }

}
