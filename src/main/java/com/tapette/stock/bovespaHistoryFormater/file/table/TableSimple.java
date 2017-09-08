package com.tapette.stock.bovespaHistoryFormater.file.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableSimple extends ArrayList<Line>{
	
	private static final long serialVersionUID = 2888786708507621471L;
	
	public Line getStrockName(String str) {
		for (int i = 0; i < size(); i++) {
			if(get(i).getStockName().equalsIgnoreCase(str))
				return get(i);
		}
		return null;
	}
	
	public Line getStrockDate(String str) {
		for (int i = 0; i < size(); i++) {
			if(get(i).getDate().equalsIgnoreCase(str))
				return get(i);
		}
		return null;
	}
	
	public List<Line> getStrockName(List<String> strList) {
		List<Line> table = new ArrayList<Line>();
		for (int i = 0; i < strList.size(); i++) {
			if(getStrockName(strList.get(i)) != null){
				table.add(getStrockName(strList.get(i)));
			}else {
				table.add(new Line());
			}
		}
		return table;
	}
	
	public List<Line> getStrockDate(List<String> strList) {
		List<Line> table = new ArrayList<Line>();
		for (int i = 0; i < size(); i++) {
			table.add(getStrockDate(strList.get(i)));
		}
		if(table.size()<1)
			table.add(new Line(""));
		return table;
	}
	
	public List<String> getDateList() {
		List<String> dateList = new ArrayList<String>();
		for (int i = 0; i < size(); i++) {
			if(!dateList.contains(get(i).getDate()))
				dateList.add(get(i).getDate());
		}
		Collections.sort(dateList);
		return dateList;
	}
	
	@Override
	public String toString() {
		if(size()<1) return "";
		StringBuilder str = new StringBuilder();
		str.append(get(0));
		for (int i = 1; i < size(); i++) {
			str.append("\n").append(get(i));
		}
		return str.toString();
	}

}
