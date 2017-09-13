package com.tapette.stock.bovespaHistoryFormater.file.table.stocks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAOImp;

public class StockGroup {
	
	TableDAOImp stocks = null;
	List<String> dates = null;
	String[][] resultStringArray;
	double[][] resultIntArray;
	boolean executed = false;
	List<String> names = null;
	static SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	List<StockName> stockNames = new ArrayList<>();
	
	public StockGroup(List<String> names, TableDAOImp stocks, List<String> dates) {
		this.stocks = stocks;
		this.dates = dates;
		this.resultStringArray = new String[dates.size()+1][names.size()+1];
		this.resultIntArray = new double[dates.size()][names.size()];
		this.executed = false;
		this.names = names;
	}
	
	public boolean execute() throws Exception {
		resultStringArray[0][0] = "Dates\\Prices";
		for (int i = 0; i < names.size(); i++) {
			stockNames.add(stocks.getStrockNameTable(names.get(i)));
			resultStringArray[0][i+1] = names.get(i);
		}
		for (int i = 0; i < dates.size(); i++) {
			resultStringArray[i+1][0] = dates.get(i);
			for (int j = 0; j < stockNames.size(); j++) {
				resultStringArray[i+1][j+1] = getProximunTimesPrice(stockNames.get(j), dates.get(i));
				resultIntArray[i][j] = stringToDouble(getProximunTimesPrice(stockNames.get(j), dates.get(i)));
			}
		}
		this.executed = true;
		return true;
	}
	
	private String getProximunTimesPrice(StockName stockName, String date) throws Exception {
		if(stockName != null) return stockName.getProximunTimesPrice(date);
		return "";
	}

	public boolean isExecuted() {
		return executed;
	}
	
	private double stringToDouble(String str) {
		if(str == null || str.isEmpty()) return -1d;
		return Double.parseDouble(str);
	}
	
	public static List<String> rotateDate(String startDate, String endDate, int interval, int maxDates) throws Exception {
		Integer date = Integer.parseInt(startDate);
		Integer finalDate = Integer.parseInt(endDate);
		List<String> dates = new ArrayList<String>();
		Calendar cal = new GregorianCalendar(date/10000, (date/100)%100 - 1, date%100);
		dates.add(startDate);
		Calendar finalCal = new GregorianCalendar(finalDate/10000, (finalDate/100)%100 - 1, finalDate%100);
		for (int i = 0; i < maxDates; i++) {
			cal.add(Calendar.DAY_OF_MONTH, interval);
			if(cal.after(finalCal))
				return dates;
			dates.add(format1.format(cal.getTime()));
		}
		return dates;
	}

	public String[][] getResultStringArray() {
		return resultStringArray;
	}

	public double[][] getResultIntArray() {
		return resultIntArray;
	}
	
	
	

}
