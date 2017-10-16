package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks;

import java.util.Calendar;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public interface StockEntry {

	public Stock getStock();

	public Calendar getCalendar();

	public int getDate();

	public String getClosePrice();

	public String getVolume();
	
	public TypeStockEntry getType();

}
