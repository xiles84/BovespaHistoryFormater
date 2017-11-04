package com.tapette.stock.bovespaHistoryFormater.inputs.table.imp;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockEntriesGrupped;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class TableDAOImp implements TableDAO {

	private static Logger logger = LoggerFactory.getLogger( TableDAOImp.class );

	private HashMap<Stock, StockEntriesGrupped> hashStockName = new HashMap<Stock, StockEntriesGrupped>();


	@Override
	public boolean addStockEntry(StockEntry e) {
		if(logger.isDebugEnabled())
			logger.debug(String.format("addStock has been called [%s:%s]" , this, e));
		if(hashStockName.get(e.getStock()) == null)
			hashStockName.put(e.getStock() , new StockEntriesGrupped());
		hashStockName.get(e.getStock()).add(e);
		return true;
	}

	@Override
	public StockEntriesGrupped getStockEntriesGrupped(Stock str) {
		if(logger.isDebugEnabled())
			logger.debug(String.format("getStock has been called [%s:%s:%s]" , this, str, hashStockName.get(str) != null ?  hashStockName.get(str) : null));
		return hashStockName.get(str);
	}

	@Override
	public List<StockEntry> getRelativeDateEntries(Stock stock, int date) throws ExceptionOutOfRangeDate {
		return (hashStockName != null && hashStockName.get(stock) != null ) ? hashStockName.get(stock).getRelativeDateStockEntry(date) : null;
	}
	
	@Override
	public int size() {
		return this.hashStockName.size();
	}

}
