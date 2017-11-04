package com.tapette.stock.bovespaHistoryFormater.exceptions;

public class ExceptionOutOfRangeDate extends Exception{
	
	public ExceptionOutOfRangeDate(String date) {
		super("Date [" + date + "] is not in the range");
	}
	
	public ExceptionOutOfRangeDate(int date) {
		super("Date [" + date + "] is not in the range");
	}
	
	public ExceptionOutOfRangeDate() {
	}

}
