package org.red5.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimestampConvertor
{

	public static void main(String[] args)
	{
		long input = 1265110426299L;
		TimestampConvertor tsc = new TimestampConvertor();
		// Get output in system timezone
		System.out.println("usingDateAndCalendar in local timezone: "
				+ tsc.usingDateAndCalendar(input));
		// Get output in GMT
		System.out.println("usingDateAndCalendarWithTimeZone: "
				+ tsc.usingDateAndCalendarWithTimeZone(input));
		// Get output in system timezone
		System.out.println("usingDateFormatter in local timezone: "
				+ tsc.usingDateFormatter(input));
		// Get output in GMT
		System.out.println("usingDateFormatterWithTimeZone: "
				+ tsc.usingDateFormatterWithTimeZone(input));
	}

	private String usingDateAndCalendar(long input)
	{
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return (cal.get(Calendar.YEAR) + "/" + cal.get(Calendar.MONTH) + "/"
				+ cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + (cal
				.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));

	}

	private String usingDateAndCalendarWithTimeZone(long input)
	{
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		return (cal.get(Calendar.YEAR) + "/" + cal.get(Calendar.MONTH) + "/"
				+ cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + (cal
				.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));

	}

	private String usingDateFormatter(long input)
	{
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd hh:mm:ss z");
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);

	}

	private String usingDateFormatterWithTimeZone(long input)
	{
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd hh:mm:ss z");
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);

	}

}
