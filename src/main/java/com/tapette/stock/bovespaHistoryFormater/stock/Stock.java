package com.tapette.stock.bovespaHistoryFormater.stock;

import com.tapette.stock.bovespaHistoryFormater.stock.types.Type;

public class Stock {
	
	private String codigoBovespa = null;
	private String stock = null;
	private Type type = null;
	private String taxes = null;
	
	public Stock(String stock , String codigoBovespa, Type type) {
		this.codigoBovespa = codigoBovespa;
		this.stock = stock;
		this.type = type;
	}
	
	public Stock(String stock , String codigoBovespa, int type) {
		this.stock = stock;
		this.codigoBovespa = codigoBovespa;
		for (int i = 0; i < Type.values().length; i++) {
			if(Type.values()[i].getIntType() == type)
				this.type = Type.values()[i];
		}
	}
	
	public Stock(String stock , String codigoBovespa, Type type, String taxes) {
		this(stock , codigoBovespa, type);
		this.taxes = taxes;
	}
	
	public String getCodigoBovespa() {
		return this.codigoBovespa;
	}
	
	public String getStock() {
		return this.stock;
	}
	
	public double getTaxes() {
		if(taxes != null)
			return Double.parseDouble(taxes);
		return type.getUsualTaxes();
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("{[stock=").append(getStock()).
		append("][codigoBovespa=").append(getCodigoBovespa()).
		append("][taxes=").append(getTaxes()).
		append("][type=").append(getType()).append("]}");
		return str.toString();
	}

}
