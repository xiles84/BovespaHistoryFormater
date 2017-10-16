package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockEntryImp;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class StocksAbstractImpTest {
	
	TypeStockEntry typeGeneric = TypeStockEntry.GENERIC;
	TypeStockEntry typePrice = TypeStockEntry.PRICE;
	TypeStockEntry typeProventos = TypeStockEntry.PROVENTOS;
	ArrayList<StockEntryImp> stockEntries = null;
	
	StocksAbstractImp stocksAbstractImp = new StocksAbstractImp() {
		@Override
		public StockEntry getRelativeDateStockEntry(int[] date, TypeStockEntry stockEntry) throws Exception {return null;}
		@Override
		public StockEntry getRelativeDateStockEntry(int[] date) throws Exception {return null;}
		@Override
		public StockEntry getRelativeDateStockEntry(int date, TypeStockEntry stockEntry) throws Exception {return null;}
		@Override
		public StockEntry getRelativeDateStockEntry(int date) throws Exception {return null;}
	};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	private void genericTestSetter() throws Exception {
		Stock stock1 = new Stock("MFII11", "MFII", 2);
		stockEntries = new ArrayList<StockEntryImp>();
		stockEntries.add(new StockEntryImp(stock1, 20171009, "11.02", "102", TypeStockEntry.PRICE)); // 0
		stockEntries.add(new StockEntryImp(stock1, 20171010, "12.02", "102", TypeStockEntry.PROVENTOS)); // 1
		stockEntries.add(new StockEntryImp(stock1, 20171011, "13.02", "102", TypeStockEntry.PRICE)); // 2
		stockEntries.add(new StockEntryImp(stock1, 20171012, "14.02", "102", TypeStockEntry.PROVENTOS)); // 3
		stockEntries.add(new StockEntryImp(stock1, 20171013, "15.02", "102", TypeStockEntry.PRICE)); // 4
		stockEntries.add(new StockEntryImp(stock1, 20171017, "16.02", "102", TypeStockEntry.PROVENTOS)); // 5
		stockEntries.add(new StockEntryImp(stock1, 20171018, "17.02", "102", TypeStockEntry.PRICE)); // 6
		stockEntries.add(new StockEntryImp(stock1, 20171013, "15.02", "102", TypeStockEntry.PRICE)); // 7
		stockEntries.add(new StockEntryImp(stock1, 20171024, "16.02", "102", TypeStockEntry.PROVENTOS)); // 8
		stockEntries.add(new StockEntryImp(stock1, 20171026, "16.02", "102", TypeStockEntry.PROVENTOS)); // 8
		for (int i = 0; i < stockEntries.size(); i++) {
			stocksAbstractImp.add(stockEntries.get(i));
		}
	}

	@Test
	public void testGetInt1() throws Exception {
		genericTestSetter();
		assertEquals(stockEntries.get(1), stocksAbstractImp.get(1));
		assertEquals(stockEntries.get(5), stocksAbstractImp.get(2 , TypeStockEntry.PROVENTOS));
	}

	@Test
	public void testGetIntTypeStockEntry() throws Exception {
		genericTestSetter();
		assertEquals(stockEntries.get(5), stocksAbstractImp.get(2 , TypeStockEntry.PROVENTOS));
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDateArray1() throws Exception {
		int[] dates = new int[] { 20171009 , 20171010 , 20171011 , 20171012 , 20171013 , 20171017 , 20171018 , 20171024 , 20171026 };
		genericTestSetter();
		assertArrayEquals(dates, stocksAbstractImp.getDateArray());
	}
	
	@Test
	public void testGetDateArray3() throws Exception {
		genericTestSetter();
		assertEquals(9, stocksAbstractImp.getDateArray().length);
		assertEquals(4, stocksAbstractImp.getDateArray(TypeStockEntry.PRICE).length);
	}

	@Test
	public void testGetDateArrayTypeStockEntry1() throws Exception {
		genericTestSetter();
		assertEquals(4, stocksAbstractImp.getDateArray(TypeStockEntry.PRICE).length);
	}
	
	@Test
	public void testGetDateArrayTypeStockEntry2() throws Exception {
		int[] dates = new int[] { 20171010 , 20171012 , 20171017 };
		genericTestSetter();
		assertArrayEquals(dates, stocksAbstractImp.getDateArray(TypeStockEntry.PROVENTOS));
	}

	@Test
	public void testRotateDateInt() throws Exception {
		genericTestSetter();
		assertEquals(20171013 , stocksAbstractImp.rotateDate(20171013));
		assertEquals(20171013 , stocksAbstractImp.rotateDate(20171013));
		assertEquals(20171013 , stocksAbstractImp.rotateDate(20171014));
		assertEquals(20171013 , stocksAbstractImp.rotateDate(20171015));
		assertEquals(20171013 , stocksAbstractImp.rotateDate(20171016));
		assertEquals(20171018 , stocksAbstractImp.rotateDate(20171020));
		assertEquals(20171026 , stocksAbstractImp.rotateDate(20171028));
	}

	@Test
	public void testRotateDateIntTypeStockEntry() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
