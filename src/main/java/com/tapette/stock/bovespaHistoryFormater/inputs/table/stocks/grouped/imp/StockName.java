package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp;

import java.util.ArrayList;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;

public class StockName extends StocksAbstractImp {

	@Override
	public List<StockEntry> getRelativeDateStockEntry(int date) throws Exception {
		return getRelativeDateStockEntry(date , null);
	}

	@Override
	public List<StockEntry> getRelativeDateStockEntry(int date, TypeStockEntry stockEntry) throws Exception {
		int localDate = rotateDate(date);
		ArrayList<StockEntry> localArray = new ArrayList<StockEntry>();
		for (int i = 0; i < stockEntrys.size(); i++)
			if(stockEntrys.get(i).getDate() == localDate && (stockEntry == null || stockEntrys.get(i).getType().getIntType() == stockEntry.getIntType()))
				localArray.add(stockEntrys.get(i));
		return localArray;
	}

	@Override
	public List<List<StockEntry>> getRelativeDateStockEntry(int[] date) throws Exception {
		return getRelativeDateStockEntry(date, null);
	}

	@Override
	public List<List<StockEntry>> getRelativeDateStockEntry(int[] date, TypeStockEntry stockEntry) throws Exception {
		ArrayList<List<StockEntry>> localArrayOfArrays = new ArrayList<List<StockEntry>>();
		for (int i = 0; i < date.length; i++) {
			localArrayOfArrays.add(getRelativeDateStockEntry(date[i], stockEntry));
		}
		return localArrayOfArrays;
	}

}
