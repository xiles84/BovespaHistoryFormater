package com.tapette.stock.bovespaHistoryFormater.file.table.stocks;

import java.util.ArrayList;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;

public class StockName extends Stocks{
	
	private static final long serialVersionUID = -2734473156485806924L;

	public StockName() {
		this.type = Type.NAME;
	}
	
	public StockEntry getFirstStrockEntryByName(String str) throws Exception {
		throw new Exception("Dont filter a per name twice");
	}
	
	public String getProximunTimesPrice(String date) throws Exception {
		if(!getCloseTime(getFirstStrockEntryByDate(date)).isEmpty())
			return getCloseTime(getFirstStrockEntryByDate(date));
		return getCloseTime(getFirstStrockEntryByDate(rotateDate(date)));
	}
	
	public StockEntry getFirstStrockEntryByDate(String str) throws Exception {
		for (int i = 0; i < size(); i++) {
			if(get(i).getDate().equalsIgnoreCase(str))
				return get(i);
		}
		return null;
	}
	
	public List<StockEntry> getFirstStrockEntryByName(List<String> strList) throws Exception {
		throw new Exception("Dont filter a per name twice");
	}
	
	public List<StockEntry> getFirstStrockEntryByDate(List<String> strList) throws Exception {
		List<StockEntry> table = new ArrayList<StockEntry>();
		for (int i = 0; i < size(); i++)
			table.add(getFirstStrockEntryByDate(strList.get(i)));
		return table;
	}

}
