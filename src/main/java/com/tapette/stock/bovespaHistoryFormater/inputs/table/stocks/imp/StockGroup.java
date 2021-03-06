package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockEntriesGrupped;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class StockGroup {

	private static Logger logger = LoggerFactory.getLogger( StockGroup.class );

	private TableDAO table = null;
	private DateGroup dates = null;
	private double[][] resultIntArray = null;
	private List<Stock> stockList = null;

	public StockGroup(List<Stock> stockList, TableDAO table, DateGroup dates) {
		this.table = table;
		this.dates = dates;
		this.stockList = stockList;
	}

	private boolean processResultIntArray() {
		if(logger.isDebugEnabled())
			logger.debug(String.format("processResultIntArray has been called [%s:%s]" , stockList, Arrays.toString(dates.getDates())));
		this.resultIntArray = new double[dates.getDates().length][stockList.size()];
		double tempVal = -1;
		for (int j = 0; j < stockList.size(); j++) {
			try {
				tempVal = getProximunTimesPremium(table.getStockEntriesGrupped(stockList.get(j)), dates.getDates()[0], TypeStockEntry.PROVENTOS);
			} catch (ExceptionOutOfRangeDate e) {
				tempVal = 0;
			}
			try {
				resultIntArray[0][j] = getProximunTimesPrice(table.getStockEntriesGrupped(stockList.get(j)),  dates.getDates()[0]);
			} catch (ExceptionOutOfRangeDate e) {
				resultIntArray[0][j] = -1;
			}
			if(logger.isDebugEnabled())
				logger.debug(String.format("processResultIntArray got initial proventos and price [%s:%s]" , tempVal, resultIntArray[0][j]));
			for (int i = 1; i < dates.getDates().length; i++) {
				try {
					resultIntArray[i][j] = getProximunTimesPrice(table.getStockEntriesGrupped(stockList.get(j)),  dates.getDates()[i]) +
							getProximunTimesPremium(table.getStockEntriesGrupped(stockList.get(j)), dates.getDates()[i], TypeStockEntry.PROVENTOS) -
							tempVal;
				} catch (ExceptionOutOfRangeDate e) {
					if(logger.isErrorEnabled())
						logger.error(e.getMessage(), e);
					resultIntArray[i][j] = -1;
				}
			}
		}
		if(logger.isDebugEnabled())
			for (int j = 0; j < resultIntArray.length; j++)
				logger.debug(String.format("processResultIntArray has ended [%s:%s:%s]", j, dates.getDates()[j], Arrays.toString(resultIntArray[j])));
		return true;
	}

	public double[][] getResultIntArray() throws ExceptionOutOfRangeDate {
		if(logger.isDebugEnabled())
			logger.debug(String.format("getResultIntArray has been called [%s]", resultIntArray == null ? "true" : "false"));
		if(resultIntArray == null)
			processResultIntArray();
		return resultIntArray;
	}

	private double getProximunTimesPrice(StockEntriesGrupped stockEntriesGrupped, int date) throws ExceptionOutOfRangeDate {
		if(stockEntriesGrupped == null) {
			if(logger.isDebugEnabled())
				logger.debug("getProximunTimesPrice has been called with null stock");
			return -1;
		}
		List<StockEntry> stockEntryList = stockEntriesGrupped.getRelativeDateStockEntry(date, TypeStockEntry.PRICE);
		if(logger.isDebugEnabled())
			logger.debug((stockEntriesGrupped != null &&
			stockEntryList != null &&
			!(stockEntryList.size() < 1)) ?
					String.format("getProximunTimesPrice has been called [%s:%s:%s:%s]",
							stockEntriesGrupped.getIdent(),
							date,
							TypeStockEntry.PRICE,
							getPrice(stockEntryList)) :
								String.format("getProximunTimesPrice has been called with null stock [%s:%s:%s]",
										stockEntriesGrupped.getIdent(),
										date,
										TypeStockEntry.PRICE));
		return (stockEntriesGrupped != null) ?
				getPrice(stockEntryList) :
					-1d;
	}

	private double getProximunTimesPremium(StockEntriesGrupped stockName, int date, TypeStockEntry typeStockEntry) throws ExceptionOutOfRangeDate {
		if(logger.isDebugEnabled())
			logger.debug(String.format("getProximunTimesPremium has been called [%s:%s:%s]" , stockName, date, typeStockEntry));
		//TODO I am thinking about raising exceptions instead of returnng -1
		if(stockName == null)
			return -1;
		List<StockEntry> ret = stockName.
				getRelativeDateStockEntry(
						date, typeStockEntry);
		if(ret == null || ret.isEmpty() || ret.size() < 1)
			return -1;
		return stockName.
				getRelativeDateStockEntry(
						date, typeStockEntry).
				get(0).getClosePrice();
	}

	private double getPrice(List<StockEntry> stockEntry) {
		for (int i = 0; i < stockEntry.size(); i++)
			if( !(stockEntry.get(i).getClosePrice() < 0) )
				return stockEntry.get(i).getClosePrice();
		return -1d;
	}
	
	
	private double getProximunTimesAccumulatedPremiums(StockEntriesGrupped stockName, int date, TypeStockEntry typeStockEntry) throws ExceptionOutOfRangeDate {
		if(logger.isDebugEnabled())
			logger.debug(String.format("getProximunTimesPremium has been called [%s:%s:%s]" , stockName, date, typeStockEntry));
		//TODO I am thinking about raising exceptions instead of returning -1
		if(stockName == null)
			return -1;
		List<StockEntry> ret = stockName.
				getRelativeDateStockEntry(
						date, typeStockEntry);
		if(ret == null || ret.isEmpty() || ret.size() < 1)
			return -1;
		return stockName.
				getRelativeDateStockEntry(
						date, typeStockEntry).
				get(0).getClosePrice();
	}


}
