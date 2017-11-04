package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyFile;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Extracters;
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

	public StockGroup(List<Stock> stockList, Extracters extracters, DateGroup dates) throws IOException, ExceptionEmptyFile, ExceptionInvalidFormat, InterruptedException {
		this(stockList, extracters.getList(), dates);
	}

	public boolean processResultIntArray() {
		if(logger.isDebugEnabled())
			logger.debug(String.format("processResultIntArray has been called [%s]" , stockList));
		this.resultIntArray = new double[dates.getDates().length][stockList.size()];
		double tempVal = -1;
		for (int j = 0; j < stockList.size(); j++) {
			try {
				tempVal = getProximunTimesPremium(table.getStockEntriesGrupped(stockList.get(j)), dates.getDates()[0]);
				resultIntArray[0][j] = getProximunTimesPrice(table.getStockEntriesGrupped(stockList.get(j)),  dates.getDates()[0]);
			} catch (ExceptionOutOfRangeDate e) {
				tempVal = -1;
				resultIntArray[0][j] = -1;
			}
			for (int i = 1; i < dates.getDates().length; i++) {
				try {
					resultIntArray[i][j] = getProximunTimesPrice(table.getStockEntriesGrupped(stockList.get(j)),  dates.getDates()[i]) +
							getProximunTimesPremium(table.getStockEntriesGrupped(stockList.get(j)), dates.getDates()[i]) -
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

	private double getProximunTimesPrice(StockEntriesGrupped stockName, int date) throws ExceptionOutOfRangeDate {
		if(logger.isDebugEnabled())
			logger.debug((stockName != null &&
			stockName.getRelativeDateStockEntry(date, TypeStockEntry.PRICE) != null &&
			!(stockName.getRelativeDateStockEntry(date, TypeStockEntry.PRICE).size() < 1)) ?
					String.format("getProximunTimesPrice has been called [%s:%s:%s]",
							date,
							TypeStockEntry.PRICE,
							stockName.
							getRelativeDateStockEntry(date, TypeStockEntry.PRICE).
							get(0).
							getClosePrice()) :
					"getProximunTimesPrice has been called with null stock");
		if(stockName != null &&
				stockName.getRelativeDateStockEntry(date, TypeStockEntry.PRICE) != null &&
				!(stockName.getRelativeDateStockEntry(date, TypeStockEntry.PRICE).size() < 1)) return stockName.
						getRelativeDateStockEntry(
								date, TypeStockEntry.PRICE).
						get(0).getClosePrice();
		return -1;
	}

	private double getProximunTimesPremium(StockEntriesGrupped stockName, int date) throws ExceptionOutOfRangeDate {
		if(stockName == null)
			return -1;
		List<StockEntry> ret = stockName.
				getRelativeDateStockEntry(
						date, TypeStockEntry.PROVENTOS);
		if(ret == null || ret.isEmpty() || ret.size() < 1)
			return -1;
		return stockName.
				getRelativeDateStockEntry(
						date, TypeStockEntry.PROVENTOS).
				get(0).getClosePrice();
	}


}
