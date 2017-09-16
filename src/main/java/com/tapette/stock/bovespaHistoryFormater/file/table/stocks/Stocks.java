package com.tapette.stock.bovespaHistoryFormater.file.table.stocks;

public interface Stocks {
	
	public enum Type {
		NAME, DATE

	}

	String getProximunTimesPrice(String date) throws Exception;

}
