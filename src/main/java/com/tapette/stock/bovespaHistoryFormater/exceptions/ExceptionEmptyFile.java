package com.tapette.stock.bovespaHistoryFormater.exceptions;

public class ExceptionEmptyFile extends Exception{
	
	public ExceptionEmptyFile() {
		super("Stock array must not be neither null nor empty");
	}

}
