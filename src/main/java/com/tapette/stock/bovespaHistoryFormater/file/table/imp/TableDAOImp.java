package com.tapette.stock.bovespaHistoryFormater.file.table.imp;

import java.util.ArrayList;
import java.util.HashMap;

import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.imp.StockDate;
import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.imp.StockName;

public class TableDAOImp extends ArrayList<StockEntry> implements TableDAO {
	
	private static final long serialVersionUID = 1574990503341685712L;
	private HashMap<String, StockName> hashStockName = new HashMap<String, StockName>();
	private HashMap<String, StockDate> hashStockDate = new HashMap<String, StockDate>();
	
	
	@Override
	public boolean add(StockEntry e) {
		addStrockName(e);
		addStrockDate(e);
		return super.add(e);
	}
	
	private void addStrockName(StockEntry e) {
		if(hashStockName.get(e.getStockEntryName()) == null)
			hashStockName.put(e.getStockEntryName() , new StockName());
		hashStockName.get(e.getStockEntryName()).add(e);
	}
	
	private void addStrockDate(StockEntry e) {
		if(hashStockDate.get(e.getDate()) == null)
			hashStockDate.put(e.getDate() , new StockDate());
		hashStockDate.get(e.getDate()).add(e);
	}
	
	@Override
	public StockName getStrockNameTable(String str) {
		return hashStockName.get(str);
	}
	
	@Override
	public StockDate getStrockDateTable(String str) {
		return hashStockDate.get(str);
	}
	
	@Override
	public String getProximunTimePrice(String stockName, String stockDate) throws Exception {
		if(hashStockName.get(stockName) == null) return "";
		return hashStockName.get(stockName).getProximunTimesPrice(stockDate);
	}

}
