package com.tapette.stock.bovespaHistoryFormater.file.table;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class StockEntry {

	List<StringBuilder> array = new ArrayList<StringBuilder>();
	
	public StockEntry() {
		super();
	}
	
	public StockEntry(String text) {
		parseColumns(new StringBuilder(text));
	}
	
	private void parseColumns(StringBuilder txt) {
		String sep = ".";
		array.add(new StringBuilder(txt.substring(0, 2)));
		array.add(new StringBuilder(txt.substring(2, 10)));
		array.add(new StringBuilder(txt.substring(10, 12)));
		array.add(new StringBuilder(txt.substring(12, 24).trim()));
		array.add(new StringBuilder(txt.substring(24, 27)));
		array.add(new StringBuilder(txt.substring(27, 39)));
		array.add(new StringBuilder(txt.substring(39, 49)));
		array.add(new StringBuilder(txt.substring(49, 52)));
		array.add(new StringBuilder(txt.substring(52, 56).trim()));
		array.add(new StringBuilder(txt.substring(56, 67)).append(sep).append(txt.substring(67, 69)));
		array.add(new StringBuilder(txt.substring(69, 80)).append(sep).append(txt.substring(80, 82)));
		array.add(new StringBuilder(txt.substring(82, 93)).append(sep).append(txt.substring(93, 95)));
		array.add(new StringBuilder(txt.substring(95, 106)).append(sep).append(txt.substring(106, 108)));
		array.add(new StringBuilder(txt.substring(108, 119)).append(sep).append(txt.substring(119, 121)));
		array.add(new StringBuilder(txt.substring(121, 132)).append(sep).append(txt.substring(132, 134)));
		array.add(new StringBuilder(txt.substring(134, 143)).append(sep).append(txt.substring(143, 145)));
		array.add(new StringBuilder(txt.substring(147, 152)));
		array.add(new StringBuilder(txt.substring(152, 170)));
		array.add(new StringBuilder(txt.substring(170, 188)).append(sep).append(txt.substring(186, 188)));
		array.add(new StringBuilder(txt.substring(188, 201)).append(sep).append(txt.substring(199, 201)));
		array.add(new StringBuilder(txt.substring(201, 202)));
		array.add(new StringBuilder(txt.substring(202, 210)));
		array.add(new StringBuilder(txt.substring(210, 217)));
		array.add(new StringBuilder(txt.substring(217, 230)).append(sep).append(txt.substring(228, 230)));
		array.add(new StringBuilder(txt.substring(230, 242).trim()));
		array.add(new StringBuilder(txt.substring(242, 245)));
	}
	
	public String getStockEntryName() {
		if(array.size() < 4) return "";
		return array.get(3).toString();
	}
	
	public Calendar getCalendar() {
		Integer intDate = Integer.parseInt(array.get(1).toString());
		return new GregorianCalendar(intDate/10000, (intDate/100)%100 -1, intDate%100);
	}
	
	public String getDate() {
		if(array.size() < 2) return "";
		return array.get(1).toString();
	}
	
	public String getClosePrice() {
		if(array.size() < 14) return "";
		return array.get(13).toString();
	}
	
	public String getVolume() {
		if(array.size() < 19) return "";
		return array.get(18).toString();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(array.get(0));
		for (int i = 1; i < array.size(); i++) {
			str.append(";").append(array.get(i));
		}
		str.append(";");
		return str.toString();
	}

}
