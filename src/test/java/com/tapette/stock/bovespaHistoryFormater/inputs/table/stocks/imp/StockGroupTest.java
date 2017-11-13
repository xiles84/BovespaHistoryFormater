package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockEntriesGrupped;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;
import com.tapette.stock.bovespaHistoryFormater.stock.types.Type;

@RunWith(MockitoJUnitRunner.class)
public class StockGroupTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testStockGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetResultIntArray() throws ExceptionInvalidFormat, ExceptionOutOfRangeDate {
		final Stock stock1 = new Stock("MFII11", "MFII", Type.FII);
		final Stock stock2 = new Stock("WPLZ11B", "WPLZ", Type.FII);
		final Stock stock3 = new Stock("ABCP11", "ABCP", Type.FII);
		ArrayList<Stock> stocks = new ArrayList<>();
		stocks.add(stock1);
		stocks.add(stock2);
		stocks.add(stock3);
		TableDAOImp table = Mockito.mock(TableDAOImp.class);
		Mockito.when(table.getStockEntriesGrupped(Mockito.any(Stock.class))).thenAnswer(new Answer<StockEntriesGrupped>() {

			@Override
			public StockEntriesGrupped answer(InvocationOnMock invocation) throws Throwable {
				Stock bb = (Stock)invocation.getArguments()[0];
				StockEntriesGrupped stockEntriesGrupped = new StockEntriesGrupped();
				if(bb.getStock().equalsIgnoreCase("MFII11")) {
					StockEntryImp stockEntry1 = new StockEntryImp(stock1, 20161001, 10d, -1, "100", TypeStockEntry.PRICE);
					StockEntryImp stockEntry5 = new StockEntryImp(stock1, 20161001, 11d, -1, "55", TypeStockEntry.PROVENTOS);
					StockEntryImp stockEntry2 = new StockEntryImp(stock1, 20161005, 12d, -1, "100", TypeStockEntry.PRICE);
					StockEntryImp stockEntry3 = new StockEntryImp(stock1, 20161015, 13d, -1, "100", TypeStockEntry.PRICE);
					StockEntryImp stockEntry4 = new StockEntryImp(stock1, 20161022, 14d, -1, "100", TypeStockEntry.PRICE);
					stockEntriesGrupped.add(stockEntry1);
					stockEntriesGrupped.add(stockEntry2);
					stockEntriesGrupped.add(stockEntry3);
					stockEntriesGrupped.add(stockEntry4);
					stockEntriesGrupped.add(stockEntry5);
				}else if(bb.getStock().equalsIgnoreCase("WPLZ11B")) {
					StockEntryImp stockEntry1 = new StockEntryImp(stock2, 20161001, 10d, -1, "100", TypeStockEntry.PRICE);
					StockEntryImp stockEntry2 = new StockEntryImp(stock2, 20161008, 10d, -1, "100", TypeStockEntry.PRICE);
					StockEntryImp stockEntry3 = new StockEntryImp(stock2, 20161015, 10d, -1, "100", TypeStockEntry.PRICE);
					StockEntryImp stockEntry4 = new StockEntryImp(stock2, 20161022, 10d, -1, "100", TypeStockEntry.PRICE);
					stockEntriesGrupped.add(stockEntry1);
					stockEntriesGrupped.add(stockEntry2);
					stockEntriesGrupped.add(stockEntry3);
					stockEntriesGrupped.add(stockEntry4);
				}else if(bb.getStock().equalsIgnoreCase("ABCP11")) {
					StockEntryImp stockEntry1 = new StockEntryImp(stock3, 20161001, 10d, -1, "100", TypeStockEntry.PRICE);
					StockEntryImp stockEntry2 = new StockEntryImp(stock3, 20161008, 10d, -1, "100", TypeStockEntry.PRICE);
					StockEntryImp stockEntry3 = new StockEntryImp(stock3, 20161015, 10d, -1, "100", TypeStockEntry.PRICE);
					StockEntryImp stockEntry4 = new StockEntryImp(stock3, 20161022, 10d, -1, "100", TypeStockEntry.PRICE);
					stockEntriesGrupped.add(stockEntry1);
					stockEntriesGrupped.add(stockEntry2);
					stockEntriesGrupped.add(stockEntry3);
					stockEntriesGrupped.add(stockEntry4);
				}
				return stockEntriesGrupped;
			}
		});
		DateGroup dates = new DateGroup(20161001, 20171122, 7);
		StockGroup aa = new StockGroup(stocks, table, dates);
		aa.getResultIntArray();
	}

}
