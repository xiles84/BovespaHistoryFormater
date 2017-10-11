package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks;

import java.util.Calendar;
import java.util.List;

public interface StockEntry {
	
	public enum Type {
		PRICE, PROVENTOS
	}

	String getStockEntryName();

	Calendar getCalendar();

	String getDate();

	String getClosePrice();

	String getVolume();

	List<StringBuilder> getFields();
	
	Type getType();

}