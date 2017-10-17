package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped;

import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;

public interface StocksEntryGrouped extends StocksEntrySimple {

	public List<StockEntry> getRelativeDateStockEntry(int date) throws Exception;
	public  List<List<StockEntry>> getRelativeDateStockEntry(int[] dates) throws Exception;
	public  List<StockEntry> getRelativeDateStockEntry(int date, TypeStockEntry type) throws Exception;
	public  List<List<StockEntry>> getRelativeDateStockEntry(int[] dates, TypeStockEntry type) throws Exception; 
	public int[] getDateArray();
	public int[] getDateArray(TypeStockEntry type);

}
