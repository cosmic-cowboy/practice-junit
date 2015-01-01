package com.slgerkamp.junit.chapter04;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsDate extends BaseMatcher<Date>{

	private final int year;
	private final int month;
	private final int day;
	Object actual;
	
	private IsDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	
	/**
	 * ファクトリメソッド
	 * @param yaer
	 * @param month
	 * @param day
	 * @return
	 */
	public static Matcher<Date> dateOf(int yaer, int month, int day){
		return new IsDate(yaer, month, day);
	}
	
	public boolean matches(Object actual) {
		this.actual = actual;
		if(!(actual instanceof Date)) throw new IllegalArgumentException();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) actual );

		if(year == cal.get(Calendar.YEAR) &&
				month == cal.get(Calendar.MONTH) + 1 &&
				day == cal.get(Calendar.DAY_OF_MONTH) ){
			return true;
		} else {
			return false;
		}
	}

	public void describeTo(Description desc) {
		
		desc.appendValue(String.format("%d/%02d/%02d", year, month, day));
		if (actual != null){
			desc.appendText("but actual is " );
			desc.appendValue(new SimpleDateFormat("yyyy/MM/dd").format((Date)actual));
		}
	}


}
