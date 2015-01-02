package com.slgerkamp.junit.chapter11;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
	
    @Test
    public void Mockitoのspyを使ったテスト() throws Exception {
        // SetUp
        SpyExample sut = new SpyExample();
        Logger spy = spy(sut.logger);
        final StringBuilder infoLog = new StringBuilder();
        doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
                infoLog.append(invocation.getArguments()[0]);
                invocation.callRealMethod();
				return null;
			}
        }).when(spy).info(anyString());
        sut.logger = spy;
        // Exercise
        sut.doSomething();
        // Verify
        assertThat(infoLog.toString(), is("doSomething"));
    }
}

