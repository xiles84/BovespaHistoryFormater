package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp;

import java.util.ArrayList;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.StocksEntryGrouped;

public class StockName extends StocksAbstractImp {
	
	private static final long serialVersionUID = -2734473156485806924L;

	public StockName() {
		this.type = Type.NAME;
	}
	
	@Override
	public StockEntry getFirstStrockEntryByName(String str) throws Exception {
		throw new Exception("Dont filter a per name twice");
	}
	
	@Override
	public String getProximunTimesPrice(String date) throws Exception {
		if(!getCloseTime(getFirstStrockEntryByDate(date)).isEmpty())
			return getCloseTime(getFirstStrockEntryByDate(date));
		return getCloseTime(getFirstStrockEntryByDate(rotateDate(date)));
	}
	
	@Override
	public StockEntry getFirstStrockEntryByDate(String str) throws Exception {
		for (int i = 0; i < size(); i++) {
			if(get(i).getDate().equalsIgnoreCase(str))
				return get(i);
		}
		return null;
	}
	
	@Override
	public List<StockEntry> getFirstStrockEntryByName(List<String> strList) throws Exception {
		throw new Exception("Dont filter a per name twice");
	}
	
	@Override
	public List<StockEntry> getFirstStrockEntryByDate(List<String> strList) throws Exception {
		List<StockEntry> table = new ArrayList<StockEntry>();
		for (int i = 0; i < size(); i++)
			table.add(getFirstStrockEntryByDate(strList.get(i)));
		return table;
	}

}
