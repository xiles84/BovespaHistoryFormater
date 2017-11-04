package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;

public class DateGroup {

	private int[] dates = null;
	private static SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");

	public DateGroup(int initialDate, int finalDate , int interval, int maxDates) throws ExceptionInvalidFormat, ExceptionOutOfRangeDate {
		if(initialDate < 10000000 || initialDate >= 100000000 || finalDate < 10000000 || finalDate >= 100000000)
			throw new ExceptionInvalidFormat("One of the date formats is not correct [YYYYMMDD]");
		if(initialDate > finalDate)
			throw new ExceptionOutOfRangeDate();
		rotateDateLocal(initialDate, finalDate, interval, maxDates);
	}

	public DateGroup(String initialDate, String finalDate , int interval, int maxDates) throws NumberFormatException, ExceptionInvalidFormat, ExceptionOutOfRangeDate {
		this(Integer.parseInt(initialDate), Integer.parseInt(finalDate), interval, maxDates);
	}

	public DateGroup(int initialDate, int finalDate , int interval) throws ExceptionInvalidFormat, ExceptionOutOfRangeDate {
		this(initialDate, finalDate , interval, 10000);
	}

	public DateGroup(String initialDate, String finalDate , int interval) throws NumberFormatException, ExceptionInvalidFormat, ExceptionOutOfRangeDate {
		this(Integer.parseInt(initialDate), Integer.parseInt(finalDate), interval);
	}

	private void rotateDateLocal(int startDate, int endDate, int interval, int maxDates) {
		this.dates = rotateDate(startDate, endDate, interval, maxDates);
	}

	public static int[] rotateDate(int startDate, int endDate, int interval, int maxDates) {
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
