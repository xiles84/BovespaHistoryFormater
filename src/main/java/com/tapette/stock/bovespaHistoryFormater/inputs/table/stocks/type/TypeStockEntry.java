package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type;

public enum TypeStockEntry {

	GENERIC(0), PRICE(1), PROVENTOS(2);

	int type = 0;

	private TypeStockEntry(int type) {
		this.type = type;
	}
	
	public int getIntType() {
		return type;
	}

}
