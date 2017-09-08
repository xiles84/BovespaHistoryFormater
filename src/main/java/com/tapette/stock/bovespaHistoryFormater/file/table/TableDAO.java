package com.tapette.stock.bovespaHistoryFormater.file.table;

import java.util.HashMap;

public class TableDAO extends TableSimple{
	
	private static final long serialVersionUID = 1574990503341685712L;
	private HashMap<String, TableSimple> hashStockName = new HashMap<>();
	private HashMap<String, TableSimple> hashStockDate = new HashMap<>();
	
	
	@Override
	public boolean add(Line e) {
		addStrockName(e);
		addStrockDate(e);
		return super.add(e);
	}
	
	private void addStrockName(Line e) {
		if(!hashStockName.containsKey(e.getStockName()))
			hashStockName.put(e.getStockName(), new TableSimple());
		hashStockName.get(e.getStockName()).add(e);
	}
	
	private void addStrockDate(Line e) {
		if(!hashStockDate.containsKey(e.getDate()))
			hashStockDate.put(e.getDate(), new TableSimple());
		hashStockDate.get(e.getDate()).add(e);
	}
	
	
	public TableSimple getStrockNameTable(String str) {
		return hashStockName.get(str);
	}
	
	
	public TableSimple getStrockDateTable(String str) {
		return hashStockDate.get(str);
	}

}
