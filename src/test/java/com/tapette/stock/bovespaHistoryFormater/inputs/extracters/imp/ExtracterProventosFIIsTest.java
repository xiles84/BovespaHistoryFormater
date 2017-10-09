package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ExtracterProventosFIIsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() throws Exception {
		Stock stock = new Stock("MFII", "MFII11");
		ArrayList<Stock> stockArray = new ArrayList<>();
		stockArray.add(stock);
		ExtracterProventosFIIs extract = new ExtracterProventosFIIs(stockArray);
		extract.execute();
		assertNotNull(extract.getList());
	}

}
