package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;

public class StockEntryProv implements StockEntry {
	
	private String stockEntryName = null;
	private String date = null;
	private String closePrice = null;
	private String payDay = null;
	private String specificEntryName = null;
	private String volume = null;
	
	public StockEntryProv( String entryName, String specificEntryName, String date, String proventos, String payDay, String volume ) throws Exception {
		this.stockEntryName = entryName;
		this.date = date;
		this.closePrice = proventos;
		this.payDay = payDay;
		this.specificEntryName = specificEntryName;
		this.volume = volume;
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
		return volume;
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
		append("][volume=").append(getVolume()).
		append("][payDay=").append(getPayDay()).append("]}");
		return str.toString();
	}

	@Override
	public Type getType() {
		return Type.PROVENTOS;
	}

}
