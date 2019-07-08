package utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtils8 {
    //long转LocalDateTime
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    //将LocalDateTime转为long类型的timestamp
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }
    //将某时间字符串转为自定义时间格式的LocalDateTime
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }
    //next month
    public static long getMonthEnd(long timeStamp) {
        LocalDateTime month = getDateTimeOfTimestamp(timeStamp);
        LocalDateTime nextmonth = month.plusMonths(1);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = nextmonth.atZone(zone).toInstant();
        return instant.toEpochMilli()-1;
    }

    public static void main(String[] args) {
        System.out.println(new Date(getMonthEnd(DateUtils.getBeginDayOfMonth().getTime())));
    }

}
