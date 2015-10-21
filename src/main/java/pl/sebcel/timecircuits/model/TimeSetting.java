package pl.sebcel.timecircuits.model;

import java.util.Calendar;

public class TimeSetting {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;

	public TimeSetting(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public static TimeSetting now() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		return new TimeSetting(year, month, day, hour, minute);
	}

	public void increase(int position) {
		switch (position) {
		case 2:
			year++;
			break;
		case 0:
			if (month < 12) {
				month++;
			}
			break;
		case 1:
			if (day < 31) {
				day++;
			}
			break;
		case 3:
			if (hour < 23) {
				hour++;
			}
			break;
		case 4:
			if (minute < 59) {
				minute++;
			}
			break;
		}
	}

	public void decrease(int position) {
		switch (position) {
		case 2:
			if (year > 0)
				year--;
			break;
		case 0:
			if (month > 1) {
				month--;
			}
			break;
		case 1:
			if (day > 1) {
				day--;
			}
			break;
		case 3:
			if (hour > 0) {
				hour--;
			}
			break;
		case 4:
			if (minute > 0) {
				minute--;
			}
			break;
		}
	}
}