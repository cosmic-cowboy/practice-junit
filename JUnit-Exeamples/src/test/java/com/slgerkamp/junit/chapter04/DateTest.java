package com.slgerkamp.junit.chapter04;

import static com.slgerkamp.junit.chapter04.IsDate.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import org.junit.Test;

public class DateTest {

	@Test
	public void 指定した日付が登録されている(){
		Date today = new Date();
		assertThat(today, is(dateOf(2015,1,1)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullとの比較(){
		assertThat(null, is(dateOf(2015,1,1)));
	}
}
