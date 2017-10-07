package com.tapette.stock.bovespaHistoryFormater.file.table;

import java.util.Calendar;
import java.util.List;

public interface StockEntry {

	String getStockEntryName();

	Calendar getCalendar();

	String getDate();

	String getClosePrice();

	String getVolume();

	List<StringBuilder> getFields();

}
