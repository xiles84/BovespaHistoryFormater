package com.tapette.stock.bovespaHistoryFormater.stock;

public class Stock {
	
	String codigo;
	String stock;
	
	public Stock(String codigo, String stock) {
		this.codigo = codigo;
		this.stock = stock;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public String getStock() {
		return this.stock;
	}

}
