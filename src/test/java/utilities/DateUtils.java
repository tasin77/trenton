package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    public static String returnNextMonth() {

        //Create Date object
        Date dNow = new Date();

        //Create Calendar object
        Calendar calendar = new GregorianCalendar();

        //Set calendar to Current Date
        calendar.setTime(dNow);

        //Create object of SimpleDateFormat
        //Define Date format to (Month-Year)
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");

        //Increment Current Month
        calendar.add(Calendar.MONTH, 1);

        //Generate Date based on specific format
        return sdf.format(calendar.getTime());
    }

}
