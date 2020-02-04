package com.sneaklife.ut.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String FORMAT_A = "yyyy-MM-dd HH:mm:ss";

	public static Long getSecond() {
		return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
	}

	public static Long getMilli() {
		return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
	}

	public static LocalDate strToLocalDate(String str, String format) {
		return LocalDate.from(DateTimeFormatter.ofPattern(format).parse(str));
	}

	public static LocalDateTime strToLocalDateTime(String str, String format) {
		return LocalDateTime.from(DateTimeFormatter.ofPattern(format).parse(str));
	}

	public static String localDateToStr(LocalDate date, String format) {
		return DateTimeFormatter.ofPattern(format).format(date);
	}

	public static String localDateTimeToStr(LocalDateTime localDateTime, String format) {
		return DateTimeFormatter.ofPattern(format).format(localDateTime);
	}

	public static long until(LocalDate startDate, LocalDate endDate, TemporalUnit unit) {
		return startDate.until(endDate, unit);
	}

	public static Period getPeriod(LocalDate startDate, LocalDate endDate) {
		return Period.between(startDate, endDate);
	}

	public static int differDays(LocalDate startDate, LocalDate endDate) {
		return getPeriod(startDate, endDate).getDays();
	}

	public static int differMonths(LocalDate startDate, LocalDate endDate) {
		return getPeriod(startDate, endDate).getMonths();
	}

	public static int differYears(LocalDate startDate, LocalDate endDate) {
		return getPeriod(startDate, endDate).getYears();
	}

	public static String getWeekDay(){
		String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0){
			w = 0;
		}
		return weekDays[w];
	}

	public static LocalDateTime plus(long hours){
		return LocalDateTime.now().plusHours(hours);
	}
}
