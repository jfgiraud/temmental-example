package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static final String DDMMYYYYhhmmss = "dd/MM/yyyy HH:mm:ss";
    public static final String YYYYMMDDhhmmss = "yyyy-MM-dd HH:mm:ss";

    public static Calendar parse(String date, String format) throws ParseException {
        Date tmp = new SimpleDateFormat(format).parse(date);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(tmp);
        return gc;
    }

    public static String format(Calendar cal, String format) {
        return format(cal.getTime(), format);
    }

    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static boolean isToday(String date, String format) throws ParseException {
        return isToday(parse(date, format));
    }

    public static boolean isToday(Calendar calendar) {
        GregorianCalendar gc = new GregorianCalendar();
        return calendar.get(Calendar.YEAR) == gc.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == gc.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == gc.get(Calendar.DAY_OF_MONTH);
    }

}
