package com.tapette.stock.bovespaHistoryFormater.file.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableDAO extends TableSimple{
	
	private static final long serialVersionUID = 1574990503341685712L;
	private HashMap<String, TableSimple> hashStockName = new HashMap<String, TableSimple>();
	private HashMap<String, TableSimple> hashStockDate = new HashMap<String, TableSimple>();
	
	
	@Override
	public boolean add(Line e) {
		addStrockName(e);
		addStrockDate(e);
		return super.add(e);
	}
	
	private void addStrockName(Line e) {
		getHashMap(hashStockName, Type.NAME,  e.getStockName()).add(e);
	}
	
	private void addStrockDate(Line e) {
		getHashMap(hashStockDate, Type.DATE, e.getDate()).add(e);
	}
	
	
	public TableSimple getStrockNameTable(String str) {
		return hashStockName.get(str);
	}
	
	
	public TableSimple getStrockDateTable(String str) {
		return hashStockDate.get(str);
	}
	
	public TableSimple getHashMap(HashMap<String, TableSimple> hashMap, Type type, String str) {
		if(hashMap.get(str) == null) hashMap.put(str , new TableSimple(type));
		return hashMap.get(str);
	}
	
	public String getProximunTimePrice(String stockName, String stockDate) throws Exception {
		return hashStockName.get(stockName).getProximunTimesPrice(stockDate);
	}
	
	/*public List<Line> getFirstStrockByNameWeekly(List<String> strList, String startDate, String endDate, int interval) throws Exception {
		List<Line> table = new ArrayList<Line>();
		for (int i = 0; i < size(); i++) {
			table.add(hashStockName.get(strList.get(i)));
		}
		if(table.size()<1)
			table.add(new Line(""));
		return table;
	}*/

}
