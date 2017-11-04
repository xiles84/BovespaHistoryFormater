package com.tapette.stock.bovespaHistoryFormater.exceptions;

public class ExceptionEmptyStockArray extends Exception{
	
	public ExceptionEmptyStockArray() {
		super("Stock array must not be neither null nor empty");
	}

}
