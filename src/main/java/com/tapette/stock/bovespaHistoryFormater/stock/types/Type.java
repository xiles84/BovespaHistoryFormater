package com.tapette.stock.bovespaHistoryFormater.stock.types;

public enum Type {
	CommonStock(0), ETF(1), FII(2);

	int type = 0;

	Type (int type){
		this.type = type;
	}

	public double getUsualTaxes() {
		switch (type) {
		case 1:
			return 0.2;
		case 2:
			return 0.2;
		default:
			return 0.15;
		}
	}
	
	public int getIntType() {
		return type;
	}

}
