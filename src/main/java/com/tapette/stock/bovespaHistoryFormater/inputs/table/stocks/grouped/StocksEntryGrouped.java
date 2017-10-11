package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped;

import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;

public interface StocksEntryGrouped extends StocksEntrySimple {

	public enum Type {
		NAME, DATE
	}

	StockEntry getFirstStrockEntryByName(String str) throws Exception;

	StockEntry getFirstStrockEntryByDate(String str) throws Exception;

	List<StockEntry> getFirstStrockEntryByName(List<String> strList) throws Exception;

	List<StockEntry> getFirstStrockEntryByDate(List<String> strList) throws Exception;

	List<String> getDateList();

	List<StockEntry> getFirstStockEntryByNameJumpDays(List<String> strList, String startDate, String endDate,
			int interval) throws Exception;

	Type getType();

}
