package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyFile;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyStockArray;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp.ExtracterBovespaXLS;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp.ExtracterProventosMultiThread;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserBovespaProventos;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserBovespaXLSPrices;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.DateGroup;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockGroup;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;
import com.tapette.stock.bovespaHistoryFormater.stock.types.Type;

public class StockNameTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test()
	public void testExceptionOutOfRangeDate() throws ExceptionInvalidFormat, ExceptionOutOfRangeDate, ExceptionEmptyStockArray, IOException, ExceptionEmptyFile, InterruptedException {
		Stock stock1 = new Stock("MFII11", "MFII", Type.FII);
		Stock stock2 = new Stock("WPLZ11B", "WPLZ", Type.FII);
		Stock stock3 = new Stock("ABCP11", "ABCP", Type.FII);
		TableDAOImp table = new TableDAOImp();
		DateGroup dates = new DateGroup(20161009, 20171009, 7);
		ArrayList<Stock> stocks = new ArrayList<>();
		stocks.add(stock1);
		stocks.add(stock2);
		stocks.add(stock3);
		ArrayList<String> files = new ArrayList<>();
		files.add("./src/test/resources/stocks/");
		ParserBovespaXLSPrices parserBovespaXLSPrices = new ParserBovespaXLSPrices(stocks);
		ExtracterBovespaXLS extracterBovespaXLS = new ExtracterBovespaXLS(files, stocks, table, parserBovespaXLSPrices);
		extracterBovespaXLS.getList();
		ParserBovespaProventos parserBovespaProventos = new ParserBovespaProventos();
		ExtracterProventosMultiThread extracterProventosMultiThread = new ExtracterProventosMultiThread(stocks, table, parserBovespaProventos);
		StockGroup group = new StockGroup(stocks, extracterBovespaXLS, dates);
		StockGroup group2 = new StockGroup(stocks, extracterProventosMultiThread, dates);
		for (int i = 0; i < group.getResultIntArray().length; i++) {
			System.out.println(Arrays.toString(group.getResultIntArray()[i]));
			System.out.println(extracterProventosMultiThread.getList().getStockEntriesGrupped(stock1));
		}
	}

}
