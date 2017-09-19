package com.tapette.stock.bovespaHistoryFormater.file.table;

import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.imp.StockDate;
import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.imp.StockName;

public interface TableDAO extends List<StockEntry> {

	StockName getStrockNameTable(String str);

	StockDate getStrockDateTable(String str);

	String getProximunTimePrice(String stockName, String stockDate) throws Exception;

}
