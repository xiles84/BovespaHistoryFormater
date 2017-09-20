package com.tapette.stock.bovespaHistoryFormater.file;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;

@RunWith(MockitoJUnitRunner.class)
public class FormaterTest {
	static Formater form = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		form = new Formater("C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\test\\resources\\stocks");
	}

	@Test
	public void testExecute() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("stocks/").getFile());
		Formater form = new Formater(file.getAbsolutePath());
		StockEntry stock = new StockEntry("012017010212ABCP11      010FII ABC IMOBCI  ER       R$  000000000118300000000011830000000001101000000000116200000000011360000000001138000000000120500019000000000000001210000000000001407143000000000000009999123100000010000000000000BRABCPCTF000255");
		form.execute();
		for (int i = 0; i < form.getList().get(4).getFields().size(); i++)
			assertEquals(stock.getFields().get(i).toString() , form.getList().get(4).getFields().get(i).toString());
		assertEquals( 7, form.getList().size());
	}

	@Test
	public void testExecuteSpecificStock() throws Exception {
		Formater form = new Formater("") {
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
		StockEntry stock = new StockEntry("012017010212ABCP11      010FII ABC IMOBCI  ER       R$  000000000118300000000011830000000001101000000000116200000000011360000000001138000000000120500019000000000000001210000000000001407143000000000000009999123100000010000000000000BRABCPCTF000255");
		form.execute();
		for (int i = 0; i < form.getList().size(); i++)
			for (int j = 0; j < form.getList().get(i).getFields().size(); j++)
				assertEquals(stock.getFields().get(j).toString() , form.getList().get(i).getFields().get(j).toString());
	}

}