package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserBovespaProventos;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserFIIsProventos;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExtracterProventosMultiThread.class, WebMultiThread.class})
public class ExtracterProventosMultiThreadTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() throws Exception {
		Stock stock = new Stock("MFII11", "MFII", 2);
		ArrayList<Stock> stockArray = new ArrayList<>();
		stockArray.add(stock);
		ExtracterProventosMultiThread extract = new ExtracterProventosMultiThread(stockArray, new TableDAOImp(), new ParserFIIsProventos());
		extract.execute();
		assertNotNull(extract.getList());
	}
	
	@Test
	public void test2() throws InterruptedException, ExceptionOutOfRangeDate {
		ArrayList<Stock> stocks = new ArrayList<>();
		Stock aa = new Stock("MFII11" , "MFII", 2);
		stocks.add(aa);
		ExtracterProventosMultiThread form = new ExtracterProventosMultiThread(stocks, new TableDAOImp(), new ParserFIIsProventos());
		assertNotNull(form);
		assertNotNull(form.getList());
		assertNotNull(form.getList().getRelativeDateEntries(aa, 20171010));
		for (int i = 0; i < form.getList().getRelativeDateEntries(aa, 20171010).size(); i++) {
			assertEquals(-1d, form.getList().getRelativeDateEntries(aa, 20171010).get(i).getClosePrice(), 0);
			assertEquals(1.17d, form.getList().getRelativeDateEntries(aa, 20171010).get(i).getProventos(), 0);
		}
	}

}
