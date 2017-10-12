package com.tapette.stock.bovespaHistoryFormater.inputs.table;

import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockName;

public interface TableDAO extends List<StockEntry> {

	public StockName getStrockNameTable(String str);

	public StockDate getStrockDateTable(String str);

	public String getProximunTimePrice(String stockName, String stockDate) throws Exception;

}
