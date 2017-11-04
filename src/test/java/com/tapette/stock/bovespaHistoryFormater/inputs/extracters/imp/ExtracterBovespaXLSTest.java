package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyFile;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyStockArray;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserBovespaXLSPrices;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

@RunWith(MockitoJUnitRunner.class)
public class ExtracterBovespaXLSTest {

	@BeforeClass
	public static void setUpBeforeClass(){
	}

	@Test
	public void testExecute() throws ExceptionEmptyStockArray, IOException, ExceptionEmptyFile, ExceptionInvalidFormat{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("stocks/").getFile());
		ArrayList<Stock> stocks = new ArrayList<>();
		ArrayList<String> paths = new ArrayList<>();
		Stock stock1 = new Stock("ABCP11", "ABCP" , 1);
		Stock stock2 = new Stock("ABCB2", "ABCB" , 1);
		stocks.add(stock1);
		stocks.add(stock2);
		paths.add(file.getAbsolutePath());
		ExtracterBovespaXLS form = new ExtracterBovespaXLS(paths, stocks, new TableDAOImp(), new ParserBovespaXLSPrices(stocks));
		assertEquals( 1, form.getList().getStockEntriesGrupped(stock1).getDateArray().length);
		assertEquals( 2, form.getList().getStockEntriesGrupped(stock2).getDateArray().length);
		assertEquals( 20170102 , form.getList().getStockEntriesGrupped(stock1).getDateArray()[0]);
		assertEquals( 20170102 , form.getList().getStockEntriesGrupped(stock1).getDateArray()[0]);
		assertEquals( 20170108 , form.getList().getStockEntriesGrupped(stock2).getDateArray()[1]);
	}

	/*@Test
	public void testExecuteSpecificStock() throws Exception {
		ExtracterBovespaXLS form = new ExtracterBovespaXLS("", new ArrayList<Stock>()) {
			int count = 0;
			@Override
			public boolean execute() throws Exception { run(" "); return true;}
			@Override
			protected FileReader fileReader(File file) throws FileNotFoundException {return null;}
			@Override
			protected BufferedReader bufferedReader(FileReader fileReader) {return null;}
			@Override
			protected String readLine(BufferedReader reader) throws IOException {
				count++;
				if(count == 1)
					return "00COTAHIST.2017BOVESPA 20170906                                                                                                                                                                                                                      ";
				else if(count == 2)
					return "012017010212ABCP11      010FII ABC IMOBCI  ER       R$  000000000118300000000011830000000001101000000000116200000000011360000000001138000000000120500019000000000000001210000000000001407143000000000000009999123100000010000000000000BRABCPCTF000255";
				else if( count == 3)
					return "99COTAHIST.2017BOVESPA 2017090600000330110                                                                                                                                                                                                           ";
				return null;
			};
		};
		StockEntryBovespaXLSImp stock = new StockEntryBovespaXLSImp("012017010212ABCP11      010FII ABC IMOBCI  ER       R$  000000000118300000000011830000000001101000000000116200000000011360000000001138000000000120500019000000000000001210000000000001407143000000000000009999123100000010000000000000BRABCPCTF000255");
		form.execute();
		for (int i = 0; i < form.getList().size(); i++)
			for (int j = 0; j < form.getList().get(i).getFields().size(); j++)
				assertEquals(stock.getFields().get(j).toString() , form.getList().get(i).getFields().get(j).toString());
	}*/

}
