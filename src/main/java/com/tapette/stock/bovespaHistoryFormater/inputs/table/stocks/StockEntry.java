package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks;

import java.util.Calendar;
import java.util.List;

public interface StockEntry {
	
	public enum Type {
		PRICE, PROVENTOS
	}

	public String getStockEntryName();

	public Calendar getCalendar();

	public String getDate();

	public String getClosePrice();

	public String getVolume();

	public List<StringBuilder> getFields();
	
	public Type getType();

}
