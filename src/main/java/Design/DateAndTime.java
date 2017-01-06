package Design;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateAndTime {

    public static LocalTime getLocalTimeByTimeZone(TimeZone timeZone, Date date) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        LocalTime localTime = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        return localTime;
    }

    public static LocalDate getLocalDateByTimeZone(TimeZone timeZone, Date date) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH));
        return localDate;
    }

    public static boolean isOpen(LocalTime time, LocalTime start, LocalTime end) {
        return time.equals(start) || (time.isAfter(start) && time.isBefore(end));
    }

    public static void main(String[] args) {
        Date now = new Date();

        TimeZone laTimeZone = TimeZone.getTimeZone("America/Los_Angeles");

        //get LA's local time
        System.err.println(getLocalTimeByTimeZone(laTimeZone, now));
        //LA's local date
        System.err.println(getLocalDateByTimeZone(laTimeZone, now));

        System.err.println(isOpen(getLocalTimeByTimeZone(laTimeZone, now), LocalTime.of(9, 30), LocalTime.of(6, 00)));

        System.err.println(laTimeZone.getID() + " date " + getLocalDateByTimeZone(laTimeZone, now) +
            " time " + getLocalTimeByTimeZone(laTimeZone, now));

        TimeZone nyTimeZone = TimeZone.getTimeZone("America/New_York");

        System.err.println(nyTimeZone.getID() + " date " + getLocalDateByTimeZone(nyTimeZone, now) +
            " time " + getLocalTimeByTimeZone(nyTimeZone, now));

        TimeZone beijingTimeZone = TimeZone.getTimeZone("Asia/Harbin");

        System.err.println(beijingTimeZone.getID() + " date " + getLocalDateByTimeZone(beijingTimeZone, now) +
            " time " + getLocalTimeByTimeZone(beijingTimeZone, now));

    }
}
