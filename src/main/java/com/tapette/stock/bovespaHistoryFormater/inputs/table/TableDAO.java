package com.tapette.stock.bovespaHistoryFormater.inputs.table;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockName;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public interface TableDAO {

	public StockName getStock(Stock str);
	
	public void getRelativeDatePrice(Stock stock, int date) throws Exception;
	
	public boolean addStock(StockEntry stockEntry);
	
	public StockName sort(Stock stock);

}
