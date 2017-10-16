package com.tapette.stock.bovespaHistoryFormater.inputs.table.imp;

import java.util.HashMap;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockName;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class TableDAOImp implements TableDAO {

	private HashMap<Stock, StockName> hashStockName = new HashMap<Stock, StockName>();


	@Override
	public boolean addStock(StockEntry e) {
		if(hashStockName.get(e.getStock()) == null)
			hashStockName.put(e.getStock() , new StockName());
		hashStockName.get(e.getStock()).add(e);
		return true;
	}

	@Override
	public StockName getStock(Stock str) {
		return hashStockName.get(str);
	}

	@Override
	public void getRelativeDatePrice(Stock stock, int date) throws Exception {
		hashStockName.get(stock).getFirstStrockEntryByDate(String.valueOf(date));

	}

	@Override
	public StockName sort(Stock stock) {
		//TODO falta implementar
		return null;
	}

}
