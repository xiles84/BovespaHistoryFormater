package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;

public class StockEntriesGrupped extends StocksAbstractImp {

	private static Logger logger = LoggerFactory.getLogger( StockEntriesGrupped.class );

	@Override
	public List<StockEntry> getRelativeDateStockEntry(int date) throws ExceptionOutOfRangeDate {
		return getRelativeDateStockEntry(date , null);
	}

	@Override
	public List<StockEntry> getRelativeDateStockEntry(int date, TypeStockEntry stockEntryTupe) throws ExceptionOutOfRangeDate {
		int localDate = rotateDate(date, stockEntryTupe);
		if(logger.isDebugEnabled())
			logger.debug(String.format("getRelativeDateStockEntry had to transform the date [%s:%s]", date, localDate));
		ArrayList<StockEntry> localArray = new ArrayList<StockEntry>();
		for (int i = 0; i < stockEntrys.size(); i++) {
			if(logger.isDebugEnabled())
				logger.debug(String.format("getRelativeDateStockEntry was called, the verification resulted in %s", stockEntrys.get(i).getDate() == localDate && (stockEntryTupe == null || stockEntrys.get(i).getType().getIntType() == stockEntryTupe.getIntType())));
			if(stockEntrys.get(i).getDate() == localDate && (stockEntryTupe == null || stockEntrys.get(i).getType().getIntType() == stockEntryTupe.getIntType()))
				localArray.add(stockEntrys.get(i));
		}
		return localArray;
	}

	@Override
	public List<List<StockEntry>> getRelativeDateStockEntry(int[] date) throws ExceptionOutOfRangeDate {
		return getRelativeDateStockEntry(date, null);
	}

	@Override
	public List<List<StockEntry>> getRelativeDateStockEntry(int[] date, TypeStockEntry type) throws ExceptionOutOfRangeDate {
		ArrayList<List<StockEntry>> localArrayOfArrays = new ArrayList<List<StockEntry>>();
		for (int i = 0; i < date.length; i++)
			localArrayOfArrays.add(
					getRelativeDateStockEntry(date[i], type));
		return localArrayOfArrays;
	}

}
