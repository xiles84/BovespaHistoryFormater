package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateGroup {

	private int[] dates = null;
	private static SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");

	public DateGroup(int initialDate, int finalDate , int interval, int maxDates) throws Exception {
		if(initialDate > finalDate)
			throw new Exception("Final date should be greater than initial date");
		if(initialDate < 10000000 || initialDate >= 100000000 || finalDate < 10000000 || finalDate >= 100000000)
			throw new Exception("One of the date formats is wrong, should have 8 digits");
		rotateDateLocal(initialDate, finalDate, interval, maxDates);
	}

	public DateGroup(String initialDate, String finalDate , int interval, int maxDates) throws NumberFormatException, Exception {
		this(Integer.parseInt(initialDate), Integer.parseInt(finalDate), interval, maxDates);
	}

	public DateGroup(int initialDate, int finalDate , int interval) throws Exception {
		this(initialDate, finalDate , interval, 10000);
	}

	public DateGroup(String initialDate, String finalDate , int interval) throws NumberFormatException, Exception {
		this(Integer.parseInt(initialDate), Integer.parseInt(finalDate), interval);
	}

	private void rotateDateLocal(int startDate, int endDate, int interval, int maxDates) throws Exception {
		this.dates = rotateDate(startDate, endDate, interval, maxDates);
	}

	public static int[] rotateDate(int startDate, int endDate, int interval, int maxDates) throws Exception {
		int[] datesTemp = new int[maxDates];
		int count = 0;
		Calendar cal = new GregorianCalendar(startDate/10000, (startDate/100)%100 - 1, startDate%100);
		datesTemp[count] =  startDate;
		count++;
		Calendar finalCal = new GregorianCalendar(endDate/10000, (endDate/100)%100 - 1, endDate%100);
		for (int i = 0; i < maxDates; i++) {
			cal.add(Calendar.DAY_OF_MONTH, interval);
			if(cal.after(finalCal))
				continue;
			datesTemp[i] = Integer.parseInt(format1.format(cal.getTime()));
			count++;
		}
		int[] returnResult = new int[count];
		for (int i = 0; i < count; i++) {
			returnResult[i] = datesTemp[i];
		}
		return returnResult;
	}

	public int[] getDates() {
		return dates;
	}

}
