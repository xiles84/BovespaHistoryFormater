package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;

public class DateGroupTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test(expected=ExceptionOutOfRangeDate.class)
	public void testExceptionOutOfRangeDate() throws ExceptionInvalidFormat, ExceptionOutOfRangeDate {
		DateGroup dates = new DateGroup(20161009, 20151010, 7);
	}

	@Test
	public void testDateLength() throws ExceptionInvalidFormat, ExceptionOutOfRangeDate {
		DateGroup dates = new DateGroup(20170601, 20170626, 7);
		assertEquals(4, dates.getDates().length);
	}
	
	@Test(expected=ExceptionInvalidFormat.class)
	public void testExceptionInvalidFormat() throws ExceptionInvalidFormat, ExceptionOutOfRangeDate {
		DateGroup dates = new DateGroup(20161009, 2017101, 7);
	}

}
