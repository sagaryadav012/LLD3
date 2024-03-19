package ParkingLot.Utils;

import java.util.Date;

public class DateTimeUtils {
    public static int calcHours(Date startDate, Date endDate){
        long diff = endDate.getTime() - startDate.getTime();
        long diffInSec = diff / 1000;
        return (int)(Math.ceil((double)diffInSec/3600));
    }
}
