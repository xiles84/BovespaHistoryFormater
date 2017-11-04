package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;
import com.tapette.stock.bovespaHistoryFormater.stock.types.Type;

public class StockEntryImp implements StockEntry {

	private int date = -1;
	private double closePrice = -1;
	private double proventos = -1;
	private int sortType = 0;
	private Stock stock = null;
	private String volume = null;
	private TypeStockEntry type = null;

	public StockEntryImp( Stock stock, int date, double closePrice, double proventos, String volume, TypeStockEntry type) {
		this.stock = stock;
		this.date = date;
		this.closePrice = closePrice;
		this.proventos = proventos;
		this.volume = volume;
		this.type = type;
	}

	public StockEntryImp( Stock stock, int date, double closePrice, double proventos, String volume, int type) {
		this.stock = stock;
		this.date = date;
		this.closePrice = proventos;
		this.volume = volume;
		for (int i = 0; i < TypeStockEntry.values().length; i++) {
			if(Type.values()[i].getIntType() == type)
				this.type = TypeStockEntry.values()[i];
		}
	}

	public StockEntryImp( Stock stock, int date, double closePrice, double proventos, String volume, int type, int sortType) {
		this(stock, date, closePrice, proventos, volume, type);
		this.sortType = sortType;
	}

	public StockEntryImp( Stock stock, int date, double closePrice, double proventos, String volume, TypeStockEntry type, int sortType) {
		this(stock, date, closePrice, proventos, volume, type);
		this.sortType = sortType;
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
	public double getClosePrice() {
		return closePrice;
	}

	@Override
	public double getProventos() {
		return proventos;
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
		append("][proventos=").append(getProventos()).
		append("][volume=").append(getVolume()).append("]}");
		return str.toString();
	}

	@Override
	public TypeStockEntry getType() {
		return type;
	}

	public int getSortType() {
		return sortType;
	}

	public void setSortType(int sortType) {
		this.sortType = sortType;
	}

	@Override
	public int compareTo(StockEntry arg0) {
		if(sortType == 1) {
			if(getClosePrice() > arg0.getClosePrice())
				return 1;
			if(getClosePrice() < arg0.getClosePrice())
				return -1;
			else
				return 0;
		} else {
			if(getDate() > arg0.getDate())
				return 1;
			if(getDate() < arg0.getDate())
				return -1;
			else
				return 0;
		}
	}

}
