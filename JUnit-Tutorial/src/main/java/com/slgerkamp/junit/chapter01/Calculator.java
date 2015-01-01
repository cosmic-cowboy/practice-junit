package com.slgerkamp.junit.chapter01;

public class Calculator {
	/**
	 * 引数で与えられた２つの値を掛けあわせた値を返す
	 * @param x １つ目の引数
	 * @param y ２つ目の引数
	 * @return xとyを掛けあわせた値
	 */
	public int multiply(int x, int y){
		return x * y;
	}
	public float divide(int x, int y){
		if(y == 0) throw new IllegalArgumentException("divide by zero");
		return (float) x / (float) y;
	}
}
