package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.util.collections.ArrayUtils;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyFile;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyStockArray;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp.ExtracterBovespaXLS;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp.ExtracterProventosMultiThread;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserBovespaProventos;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserBovespaXLSPrices;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserFIIsProventos;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.DateGroup;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockGroup;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;
import com.tapette.stock.bovespaHistoryFormater.stock.types.Type;

public class StockNameTest {
	
	private Double[][] doubleprimarrayToDoubleobjarray(double[][] dd) {
		Double[][] ret = new Double[dd.length][dd[0].length];
		for (int i = 0; i < dd.length; i++) {
			ret[i] = doubleprimarrayToDoubleobjarray(dd[i]);
		}
		return ret;
	}
	
	private Double[] doubleprimarrayToDoubleobjarray(double[] dd) {
		Double[] ret = new Double[dd.length];
		for (int i = 0; i < dd.length; i++) {
			ret[i] = new Double(dd[i]);
		}
		return ret;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test()
	public void testGeneral() throws ExceptionInvalidFormat, ExceptionOutOfRangeDate, ExceptionEmptyStockArray, IOException, ExceptionEmptyFile, InterruptedException {
		Stock stock1 = new Stock("MFII11", "MFII", Type.FII);
		Stock stock2 = new Stock("WPLZ11B", "WPLZ", Type.FII);
		Stock stock3 = new Stock("ABCP11", "ABCP", Type.FII);
		TableDAOImp table = new TableDAOImp();
		DateGroup dates = new DateGroup(20161009, 20171109, 7);
		ArrayList<Stock> stocks = new ArrayList<>();
		stocks.add(stock1);
		stocks.add(stock2);
		stocks.add(stock3);
		ArrayList<String> files = new ArrayList<>();
		files.add("./src/test/resources/stocks/");
		ParserBovespaXLSPrices parserBovespaXLSPrices = new ParserBovespaXLSPrices(stocks);
		ExtracterBovespaXLS extracterBovespaXLS = new ExtracterBovespaXLS(files, stocks, parserBovespaXLSPrices);
		table.addExtracter(extracterBovespaXLS);
		ParserFIIsProventos parserBovespaProventos = new ParserFIIsProventos();
		ExtracterProventosMultiThread extracterProventosMultiThread = new ExtracterProventosMultiThread(stocks, table, parserBovespaProventos);
		table.addExtracter(extracterProventosMultiThread);
		StockGroup group = new StockGroup(stocks, table, dates);
		assertEquals(13.31d, group.getResultIntArray()[12][0], 0);
		assertEquals(-1d, group.getResultIntArray()[12][1], 0);
		assertEquals(-1d, group.getResultIntArray()[12][2], 0);
		assertEquals(13.31d, group.getResultIntArray()[13][0], 0);
		assertEquals(-1d, group.getResultIntArray()[13][1], 0);
		assertEquals(11.36d, group.getResultIntArray()[13][2], 0);
		assertEquals(13.31d, group.getResultIntArray()[group.getResultIntArray().length-1][0], 0);
		assertEquals(-1d, group.getResultIntArray()[group.getResultIntArray().length-1][1], 0);
		assertEquals(11.36d, group.getResultIntArray()[group.getResultIntArray().length-1][2], 0);
		System.out.println(table.getStockEntriesGrupped(stock1).getRelativeDateStockEntry(20160201).size());
		//assertEquals(1.1d,table.getStockEntriesGrupped(stock1).getRelativeDateStockEntry(20150201).get(0).getProventos(), 0);
		assertEquals(1.12d,table.getStockEntriesGrupped(stock1).getRelativeDateStockEntry(20160201).get(1).getProventos(), 0);
		//assertEquals(1.17d,table.getStockEntriesGrupped(stock1).getRelativeDateStockEntry(20160501).get(0).getProventos(), 0);
	}
	
	
	@Test()
	public void testGeneral2() throws ExceptionInvalidFormat, ExceptionOutOfRangeDate, ExceptionEmptyStockArray, IOException, ExceptionEmptyFile, InterruptedException {
		Stock stock1 = new Stock("ABCB4", "ABCB", Type.CommonStock);
		Stock stock2 = new Stock("AALR3", "AALR3", Type.CommonStock);
		Stock stock3 = new Stock("ABCP11", "ABCP", Type.CommonStock);
		TableDAOImp table = new TableDAOImp();
		DateGroup dates = new DateGroup(20170101, 20170104, 1);
		ArrayList<Stock> stocks = new ArrayList<>();
		stocks.add(stock1);
		stocks.add(stock2);
		stocks.add(stock3);
		ArrayList<String> files = new ArrayList<>();
		files.add("./src/test/resources/stocks/");
		ParserBovespaXLSPrices parserBovespaXLSPrices = new ParserBovespaXLSPrices(stocks);
		ExtracterBovespaXLS extracterBovespaXLS = new ExtracterBovespaXLS(files, stocks, parserBovespaXLSPrices);
		table.addExtracter(extracterBovespaXLS);
		StockGroup group = new StockGroup(stocks, table, dates);
		assertNotNull(group.getResultIntArray());
		assertEquals(4, group.getResultIntArray().length);
		assertNotNull(group.getResultIntArray()[0]);
		assertEquals(3, group.getResultIntArray()[0].length);
		assertArrayEquals(new Double[] {-1d,-1d,-1d}, doubleprimarrayToDoubleobjarray(group.getResultIntArray()[0]));
		assertArrayEquals(new Double[] {13.31d,14.6d,11.36d}, doubleprimarrayToDoubleobjarray(group.getResultIntArray()[1]));
		assertArrayEquals(new Double[] {13.31d,14.6d,11.36d}, doubleprimarrayToDoubleobjarray(group.getResultIntArray()[2]));
		assertArrayEquals(new Double[] {13.31d,14.6d,11.36d}, doubleprimarrayToDoubleobjarray(group.getResultIntArray()[3]));
	}
	
	@Test()
	public void testGeneral3() throws ExceptionInvalidFormat, ExceptionOutOfRangeDate, ExceptionEmptyStockArray, IOException, ExceptionEmptyFile, InterruptedException {
		Stock stock1 = new Stock("MFII11", "MFII", Type.FII);
		TableDAOImp table = new TableDAOImp();
		DateGroup dates = new DateGroup(20150101, 20170104, 365);
		ArrayList<String> files = new ArrayList<>();
		files.add("./src/test/resources/stocks/");
		ArrayList<Stock> stocks = new ArrayList<>();
		stocks.add(stock1);
		ParserFIIsProventos parserFIIsProventos = new ParserFIIsProventos();
		ExtracterProventosMultiThread extracterProventosMultiThread = new ExtracterProventosMultiThread(stocks, table, parserFIIsProventos);
		StockGroup group = new StockGroup(stocks, table, dates);
		ParserBovespaXLSPrices parserBovespaXLSPrices = new ParserBovespaXLSPrices(stocks);
		ExtracterBovespaXLS extracterBovespaXLS = new ExtracterBovespaXLS(files, stocks, parserBovespaXLSPrices);
		assertNotNull(group.getResultIntArray());
		assertEquals(3, group.getResultIntArray().length);
		assertNotNull(group.getResultIntArray()[0]);
		assertEquals(1, group.getResultIntArray()[0].length);
		assertEquals(-10d, table.getRelativeDateEntries(stock1, 20150101).get(0).getClosePrice(), 0);
		assertEquals(-1d, table.getRelativeDateEntries(stock1, 20160101).get(0).getClosePrice(), 0);
		assertEquals(-1d, table.getRelativeDateEntries(stock1, 20170101).get(0).getClosePrice(), 0);
		assertArrayEquals(new Double[] {-1d}, doubleprimarrayToDoubleobjarray(group.getResultIntArray()[0]));
		assertArrayEquals(new Double[] {-1d}, doubleprimarrayToDoubleobjarray(group.getResultIntArray()[1]));
		assertArrayEquals(new Double[] {-1d}, doubleprimarrayToDoubleobjarray(group.getResultIntArray()[2]));
	}

}
