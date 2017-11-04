package com.tapette.stock.bovespaHistoryFormater.exceptions;

public class ExceptionOutOfRange extends Exception{
	
	public ExceptionOutOfRange() {
		super("Array must not be neither null nor empty");
	}
	
	public ExceptionOutOfRange(String str) {
		super(str);
	}

}
