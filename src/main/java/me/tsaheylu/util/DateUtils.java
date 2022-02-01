package me.tsaheylu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
  private static final SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  public static Date formatSendTime(String sendtimes) {
    myFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date sendtime = null;
    try {
      sendtime = myFormatter.parse(sendtimes);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return sendtime;
  }

  /*  public static Date getCurrentTime() {
    myFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

    Date now = null;
    String nows = myFormatter.format(new Date());
    try {
      now = myFormatter.parse(nows);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return now;
  }*/

  public static Date getCurrentTime() {
    Calendar cal = Calendar.getInstance();
    //    Date now = new Date(cal.getTimeInMillis());
    Date now = cal.getTime();
    return now;
  }

  public static long localToUTCTimeInMillis(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(date.getTime());
    int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
    int dstOffset = calendar.get(Calendar.DST_OFFSET);
    calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
    return calendar.getTimeInMillis();
  }
}
