package com.tapette.stock.bovespaHistoryFormater.inputs.table;

import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockName;

public interface TableDAO extends List<StockEntry> {

	StockName getStrockNameTable(String str);

	StockDate getStrockDateTable(String str);

	String getProximunTimePrice(String stockName, String stockDate) throws Exception;

}
