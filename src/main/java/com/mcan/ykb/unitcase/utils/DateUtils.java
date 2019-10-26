package com.mcan.ykb.unitcase.utils;

import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    public static long getInterval(Date date1, Date date2, TimeUnit timeUnit ){
        long diff = date1.getTime() - date2.getTime();
        return timeUnit.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static long getIntervalInYears(Date date1, Date date2){
        long interval = getInterval(date1, date2, TimeUnit.DAYS);
        return interval/Constants.Date.DaysInYear;
    }
}
