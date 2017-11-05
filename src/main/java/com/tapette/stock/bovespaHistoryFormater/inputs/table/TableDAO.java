package com.tapette.stock.bovespaHistoryFormater.inputs.table;

import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Extracters;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockEntriesGrupped;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public interface TableDAO {

	public boolean addExtracter(Extracters extracter);
	
	public boolean addExtracter(List<Extracters> extracter);
	
	public StockEntriesGrupped getStockEntriesGrupped(Stock str);
	
	public List<StockEntry> getRelativeDateEntries(Stock stock, int date) throws ExceptionOutOfRangeDate;
	
	public boolean addStockEntry(StockEntry stockEntry);

	int size();

}
