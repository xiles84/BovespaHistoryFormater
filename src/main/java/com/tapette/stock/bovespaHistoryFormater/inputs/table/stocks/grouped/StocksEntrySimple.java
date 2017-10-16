package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped;

import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;

public interface StocksEntrySimple {

	public void add(StockEntry stockEntry);
	public StockEntry get(int index);
	public StockEntry get(int index, TypeStockEntry type);
	public ArrayList<StockEntry> sort();
	public ArrayList<StockEntry> sort(TypeStockEntry type);

}
