package za.co.smileyjoedev.tincar.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cody on 2016/08/10.
 */
public class DateHelper {

    public static final String FORMAT_API = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String FORMAT_SHORT = "d MMM yyy";
    public static final String FORMAT_LONG = "dd MMMM yyyy 'at' HH:mm:ss";

    public static Date format(String stringDate, String format){
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(format);

        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e){
            e.printStackTrace();
        }

        return date;
    }

    public static String format(Date date){
        return format(date, FORMAT_SHORT);
    }

    public static String format(Date date, String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

}
