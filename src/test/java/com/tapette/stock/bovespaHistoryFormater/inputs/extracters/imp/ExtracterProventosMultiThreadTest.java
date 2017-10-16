package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserFIIsProventos;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

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

}
