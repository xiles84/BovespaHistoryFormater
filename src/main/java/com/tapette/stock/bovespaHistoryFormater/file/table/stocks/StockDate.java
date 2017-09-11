package com.tapette.stock.bovespaHistoryFormater.file.table.stocks;

import java.util.ArrayList;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;

public class StockDate extends Stocks{
	
	private static final long serialVersionUID = 1147681261258269178L;

	public StockDate() {
		this.type = Type.DATE;
	}
	
	public StockEntry getFirstStrockEntryByName(String str) throws Exception {
		if(type == Type.NAME) throw new Exception("Dont filter a per name twice");
		for (int i = 0; i < size(); i++) {
			if(get(i).getStockEntryName().equalsIgnoreCase(str))
				return get(i);
		}
		return null;
	}
	
	public StockEntry getFirstStrockEntryByDate(String str) throws Exception {
		throw new Exception("Dont filter a per date twice");
	}
	
	public String getProximunTimesPrice(String date) throws Exception {
		throw new Exception("Is not possible to get a proximun price with just one date and multiple stocks");
	}
	
	public List<StockEntry> getFirstStrockEntryByName(List<String> strList) throws Exception {
		List<StockEntry> table = new ArrayList<StockEntry>();
		for (int i = 0; i < strList.size(); i++) {
			if(getFirstStrockEntryByName(strList.get(i)) != null){
				table.add(getFirstStrockEntryByName(strList.get(i)));
			}else {
				table.add(new StockEntry());
			}
		}
		return table;
	}
	
	public List<StockEntry> getFirstStrockEntryByDate(List<String> strList) throws Exception {
		throw new Exception("Dont filter a per date twice");
	}

}
