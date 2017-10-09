package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;

public class StockEntryProvFIIsImp implements StockEntry {
	
	String stockEntryName = null;
	String date = null;
	String closePrice = null;
	String payDay = null;
	String specificEntryName = null;
	
	public StockEntryProvFIIsImp( String entryName, String specificEntryName, String date, String proventos, String payDay ) throws Exception {
		this.stockEntryName = entryName;
		this.date = transformBrazilDateIntoUniversalDate(date);
		this.closePrice = moneyConverter(proventos);
		this.payDay = transformBrazilDateIntoUniversalDate(payDay);
		this.specificEntryName = specificEntryName;
	}
	
	private String transformBrazilDateIntoUniversalDate(String brazilDate) throws Exception {
		if(brazilDate == null)
			return null;
		if(!brazilDate.contains("/")) {
			if(brazilDate.length() == 8)
				return brazilDate;
			throw new Exception("Date [" + brazilDate + "] does not contain \"/\"");
		}
		String[] list = brazilDate.split("/");
		if(list.length < 3)
			throw new Exception("Date [" + brazilDate + "] does not have the expected format");
		StringBuilder str = new StringBuilder();
		str.append(list[2]).append(list[1]).append(list[0]);
		return str.toString();
	}
	
	private String moneyConverter(String str) {
		if(str.substring(str.lastIndexOf(".")+1).contains(","))
			return str.replaceAll("\\.", "%").replaceAll(",", ".").replaceAll("%", ",");
		return str;
	}

	@Override
	public String getStockEntryName() {
		return stockEntryName;
	}

	@Override
	public Calendar getCalendar() {
		Integer intDate = Integer.parseInt(date);
		return new GregorianCalendar(intDate/10000, (intDate/100)%100 -1, intDate%100);
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public String getClosePrice() {
		return closePrice;
	}

	@Override
	public String getVolume() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StringBuilder> getFields() {
		return null;
	}
	
	public String getPayDay() {
		return payDay;
	}
	
	public String getSpecificEntryName() {
		return specificEntryName;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("{[stockEntryName=").append(getStockEntryName()).
		append("][specificEntryName=").append(getSpecificEntryName()).
		append("][date=").append(getDate()).
		append("][closePrice=").append(getClosePrice()).
		append("][payDay=").append(getPayDay()).append("]}");
		return str.toString();
	}

}
