package com.tapette.stock.bovespaHistoryFormater.file.table.stocks.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.file.table.imp.StockEntryXLSImp;
import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.Stocks;

public abstract class StocksAbstractImp extends ArrayList<StockEntry> implements Stocks{
	
	private static final long serialVersionUID = 2888786708507621471L;
	protected Type type;
	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	List<String> dateList = null;
	
	public StocksAbstractImp() {
		this.type = Type.NAME;
	}
	
	public StocksAbstractImp(Type type) {
		this.type = type;
	}

	public abstract StockEntry getFirstStrockEntryByName(String str) throws Exception;

	public abstract StockEntry getFirstStrockEntryByDate(String str) throws Exception;
	
	public abstract List<StockEntry> getFirstStrockEntryByName(List<String> strList) throws Exception;
	
	public abstract List<StockEntry> getFirstStrockEntryByDate(List<String> strList) throws Exception;
	
	public List<String> getDateList() {
		if(dateList != null)
			return dateList;
		dateList = new ArrayList<String>();
		for (int i = 0; i < size(); i++) {
			if(!dateList.contains(get(i).getDate()))
				dateList.add(get(i).getDate());
		}
		Collections.sort(dateList);
		return dateList;
	}
	
	public List<StockEntry> getFirstStockEntryByNameJumpDays(List<String> strList, String startDate, String endDate, int interval) throws Exception {
		List<StockEntry> table = new ArrayList<StockEntry>();
		for (int i = 0; i < size(); i++) {
			table.add(getFirstStrockEntryByDate(strList.get(i)));
		}
		if(table.size()<1)
			table.add(new StockEntryXLSImp(""));
		return table;
	}
	
	protected String rotateDate(String date) throws Exception {
		Integer intDate = Integer.parseInt(date);
		if(Integer.parseInt(getDateList().get(0)) >  intDate)
			return "";
		if(Integer.parseInt(getDateList().get(getDateList().size()-1)) <  intDate)
			return getDateList().get(getDateList().size()-1);
		Calendar cal = new GregorianCalendar(intDate/10000, (intDate/100)%100 - 1, intDate%100);
		for (int i = 0; i < getDateList().size(); i++) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			if(!getCloseTime(getFirstStrockEntryByDate(
					format1.format(cal.getTime()))).
					isEmpty())
				return format1.format(cal.getTime());
		}
		return "";
	}
	
	@Override
	public String toString() {
		if(size()<1)
			return "";
		StringBuilder str = new StringBuilder();
		str.append(get(0));
		for (int i = 1; i < size(); i++)
			str.append("\n").append(get(i));
		return str.toString();
	}
	
	protected String getCloseTime(StockEntry line) {
		if(line == null) return "";
		return line.getClosePrice();
	}

	public Type getType() {
		return type;
	}
	
}
