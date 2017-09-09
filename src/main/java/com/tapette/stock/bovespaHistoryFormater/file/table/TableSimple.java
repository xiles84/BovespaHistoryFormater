package com.tapette.stock.bovespaHistoryFormater.file.table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class TableSimple extends ArrayList<Line>{
	
	public enum Type{
		NAME, DATE;
	}
	
	private static final long serialVersionUID = 2888786708507621471L;
	private Type type;
	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	List<String> dateList = null;
	
	public TableSimple() {
		this.type = Type.NAME;
	}
	
	public TableSimple(Type type) {
		this.type = type;
	}
	
	public Line getFirstStrockByName(String str) throws Exception {
		if(type == Type.NAME) throw new Exception("Dont filter a per name twice");
		for (int i = 0; i < size(); i++) {
			if(get(i).getStockName().equalsIgnoreCase(str))
				return get(i);
		}
		return new Line();
	}
	
	public Line getFirstStrockByDate(String str) throws Exception {
		if(type == Type.DATE) throw new Exception("Dont filter a per date twice");
		for (int i = 0; i < size(); i++) {
			if(get(i).getDate().equalsIgnoreCase(str))
				return get(i);
		}
		return new Line();
	}
	
	public List<Line> getFirstStrockByName(List<String> strList) throws Exception {
		List<Line> table = new ArrayList<Line>();
		for (int i = 0; i < strList.size(); i++) {
			if(getFirstStrockByName(strList.get(i)) != null){
				table.add(getFirstStrockByName(strList.get(i)));
			}else {
				table.add(new Line());
			}
		}
		return table;
	}
	
	public List<Line> getFirstStrockByDate(List<String> strList) throws Exception {
		List<Line> table = new ArrayList<Line>();
		for (int i = 0; i < size(); i++) {
			table.add(getFirstStrockByDate(strList.get(i)));
		}
		if(table.size()<1)
			table.add(new Line(""));
		return table;
	}
	
	public List<String> getDateList() {
		if(dateList != null)
			return dateList;
		dateList = new ArrayList<String>();
		for (int i = 0; i < size(); i++) {
			if(!dateList.contains(get(i).getDate()))
				dateList.add(get(i).getDate());
		}
		Collections.sort(dateList);
		return dateList;
	}
	
	public List<Line> getFirstStrockByNameWeekly(List<String> strList, String startDate, String endDate, int interval) throws Exception {
		List<Line> table = new ArrayList<Line>();
		for (int i = 0; i < size(); i++) {
			table.add(getFirstStrockByDate(strList.get(i)));
		}
		if(table.size()<1)
			table.add(new Line(""));
		return table;
	}
	
	public String getProximunTimesPrice(String date) throws Exception {
		if(type == Type.DATE)
			throw new Exception("Is not possible to get a proximun price with just one date and multiple stocks");
		if(!getFirstStrockByDate(date).getClosePrice().isEmpty())
			return getFirstStrockByDate(date).getClosePrice();
		return getFirstStrockByDate(rotateDate(date)).getClosePrice();
	}
	
	private String rotateDate(String date) throws Exception {
		Integer intDate = Integer.parseInt(date);
		if(Integer.parseInt(getDateList().get(0)) >  intDate)
			return "";
		if(Integer.parseInt(getDateList().get(getDateList().size()-1)) <  intDate)
			return getDateList().get(getDateList().size()-1);
		Calendar cal = new GregorianCalendar(intDate/10000, (intDate/100)%100, intDate%100);
		for (int i = 0; i < getDateList().size(); i++) {
			cal.add(Calendar.DAY_OF_YEAR, -1);
			if(!getFirstStrockByDate(format1.format(cal.getTime())).getClosePrice().isEmpty())
				return format1.format(cal.getTime());
		}
		return "";
	}
	
	@Override
	public String toString() {
		if(size()<1)
			return "";
		StringBuilder str = new StringBuilder();
		str.append(get(0));
		for (int i = 1; i < size(); i++)
			str.append("\n").append(get(i));
		return str.toString();
	}

}
