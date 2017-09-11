package com.tapette.stock.bovespaHistoryFormater.file.table;

import java.util.HashMap;

import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.StockDate;
import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.StockName;

public class TableDAO extends StockName{
	
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
	
	
	public StockName getStrockNameTable(String str) {
		return hashStockName.get(str);
	}
	
	
	public StockDate getStrockDateTable(String str) {
		return hashStockDate.get(str);
	}
	
	public String getProximunTimePrice(String stockName, String stockDate) throws Exception {
		if(hashStockName.get(stockName) == null) return "";
		return hashStockName.get(stockName).getProximunTimesPrice(stockDate);
	}

}
