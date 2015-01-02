package com.slgerkamp.junit.chapter11;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;

public class SpyExampleTest {

	@Test
	public void スパイオブジェクト作成() throws Exception {
		List<String> list = new java.util.LinkedList<String>();
		List<String> spy = spy(list);
		doReturn("Mockito").when(spy).get(1);
		spy.add("Hello");
		spy.add("World");
		verify(spy).add("Hello");
		verify(spy).add("World");
		assertThat(spy.get(0), is("Hello"));
		assertThat(spy.get(1), is("Mockito"));
	}
}

