package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;
import com.tapette.stock.bovespaHistoryFormater.stock.types.Type;

public class StockEntryImp implements StockEntry {

	private int date = -1;
	private String closePrice = null;
	private Stock stock = null;
	private String volume = null;
	private TypeStockEntry type = null;

	public StockEntryImp( Stock stock, int date, String proventos, String volume, TypeStockEntry type) throws Exception {
		this.stock = stock;
		this.date = date;
		this.closePrice = proventos;
		this.volume = volume;
		this.type = type;
	}
	
	public StockEntryImp( Stock stock, int date, String proventos, String volume, int type) throws Exception {
		this.stock = stock;
		this.date = date;
		this.closePrice = proventos;
		this.volume = volume;
		for (int i = 0; i < TypeStockEntry.values().length; i++) {
			if(Type.values()[i].getIntType() == type)
				this.type = TypeStockEntry.values()[i];
		}
	}

	@Override
	public Stock getStock() {
		return stock;
	}

	@Override
	public Calendar getCalendar() {
		return new GregorianCalendar(date/10000, (date/100)%100 -1, date%100);
	}

	@Override
	public int getDate() {
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
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("{[stock=").append(getStock()).
		append("][date=").append(getDate()).
		append("][closePrice=").append(getClosePrice()).
		append("][volume=").append(getVolume()).append("]}");
		return str.toString();
	}

	@Override
	public TypeStockEntry getType() {
		return type;
	}

}
