package com.sneaklife.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DateUtil {

	public static void main(String[] args) {
		TempPlan tempPlan = new TempPlan();
		List<Map<String, Object>> list = buildPlan(tempPlan);
		System.out.println(list);
	}

	public static List<Map<String, Object>> buildPlan(TempPlan tempPlan) {
		List<Map<String, Object>> list = new LinkedList<>();
		LocalDate startLocalDate = strToLocalDate(tempPlan.getStartDate(), "yyyy-MM-dd");
		LocalDate endLocalDate = strToLocalDate(tempPlan.getEndDate(), "yyyy-MM-dd");
		long differDays = until(startLocalDate, endLocalDate, ChronoUnit.DAYS);
		for (int i = -1; i < differDays; i++) {
			Map<String, Object> specific = new HashMap<>();
			specific.put("date", localDateToStr(startLocalDate, "yyyy-MM-dd"));
			specific.put("dayOfWeek", startLocalDate.getDayOfWeek().getValue());
			specific.put("initiate", 0);
			specific.put("startTime", tempPlan.getStartTime());
			specific.put("endTime", tempPlan.getEndTime());
			specific.put("amCount", tempPlan.getAmCount());
			specific.put("pmCount", tempPlan.getPmCount());
			startLocalDate = startLocalDate.plusDays(1);
			list.add(specific);
		}
		return list;
	}

	public static void buildPlant(String startTime, String endTime) {

	}

	public static Long getSecond() {
		return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
	}

	public static LocalDate strToLocalDate(String str, String format) {
		return LocalDate.from(DateTimeFormatter.ofPattern(format).parse(str));
	}

	public static String localDateToStr(LocalDate date, String format) {
		return DateTimeFormatter.ofPattern(format).format(date);
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

	static class TempPlan {
		private String startDate = "2018-10-01";
		private String endDate = "2019-11-01";
		private String startTime = "09:00";
		private String endTime = "17:00";
		private int amCount = 20;
		private int pmCount = 20;

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public int getAmCount() {
			return amCount;
		}

		public void setAmCount(int amCount) {
			this.amCount = amCount;
		}

		public int getPmCount() {
			return pmCount;
		}

		public void setPmCount(int pmCount) {
			this.pmCount = pmCount;
		}
	}
}
