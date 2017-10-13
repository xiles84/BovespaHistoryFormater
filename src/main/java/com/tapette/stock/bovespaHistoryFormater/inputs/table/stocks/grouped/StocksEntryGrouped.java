package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped;

import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;

public interface StocksEntryGrouped extends StocksEntrySimple {

	public enum Type {
		NAME, DATE
	}

	public StockEntry getFirstStrockEntryByName(String str) throws Exception;

	public StockEntry getFirstStrockEntryByDate(String str) throws Exception;

	public List<StockEntry> getFirstStrockEntryByName(List<String> strList) throws Exception;

	public List<StockEntry> getFirstStrockEntryByDate(List<String> strList) throws Exception;

	public List<String> getDateList();

	public List<StockEntry> getFirstStockEntryByNameJumpDays(List<String> strList, String startDate, String endDate,
			int interval) throws Exception;

	public Type getType();

}
