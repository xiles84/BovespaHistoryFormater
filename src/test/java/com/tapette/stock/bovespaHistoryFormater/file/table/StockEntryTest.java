package com.tapette.stock.bovespaHistoryFormater.file.table;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockEntryBovespaXLSImp;

public class StockEntryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testStockEntry() {

		String str1 = "012017010202AALR3       010ALLIAR      ON      NM   R$  000000000146200000000014880000000001440000000000145800000000014600000000001460000000000147300087000000000000035900000000000052350500000000000000009999123100000010000000000000BRAALRACNOR6100";
		String str2 = "012017010210ABCB2       010ABC BRASIL  DIR PRE N2   R$  000000000028500000000002850000000000285000000000028500000000002850000000000200000000000000000001000000000000000200000000000000057000000000000000009999123100000010000000000000BRABCBD10PR0126";
		String str3 = "012017010202ABCB4       010ABC BRASIL  PN  EJS N2   R$  000000000134000000000013520000000001308000000000133000000000013310000000001331000000000133400998000000000000195800000000000260557000000000000000009999123100000010000000000000BRABCBACNPR4127";
		String str4 = "012017010296ABCB4F      020ABC BRASIL  PN  EJS N2   R$  000000000134500000000013480000000001315000000000132900000000013260000000001326000000000134800020000000000000000582000000000000773943000000000000009999123100000010000000000000BRABCBACNPR4127";
		String str5 = "012017010212ABCP11      010FII ABC IMOBCI  ER       R$  000000000118300000000011830000000001101000000000116200000000011360000000001138000000000120500019000000000000001210000000000001407143000000000000009999123100000010000000000000BRABCPCTF000255";
		String str6 = "012017010202ABEV3       010AMBEV S/A   ON  EJ       R$  000000000163400000000016660000000001626000000000164200000000016310000000001631000000000163609421000000000004265400000000007004006700000000000000009999123100000010000000000000BRABEVACNOR1115";
		String str7 = "012017090612XTED11      010FII TRXE CORCI           R$  000000000218900000000021890000000002100000000000210400000000021500000000002100000000000214900005000000000000000211000000000000443995000000000000009999123100000010000000000000BRXTEDCTF006143";

		StockEntry stock1 = new StockEntryBovespaXLSImp(str1);
		StockEntry stock2 = new StockEntryBovespaXLSImp(str2);
		StockEntry stock3 = new StockEntryBovespaXLSImp(str3);
		StockEntry stock4 = new StockEntryBovespaXLSImp(str4);
		StockEntry stock5 = new StockEntryBovespaXLSImp(str5);
		StockEntry stock6 = new StockEntryBovespaXLSImp(str6);
		StockEntry stock7 = new StockEntryBovespaXLSImp(str7);

		ArrayList<StringBuilder> arr1 = parseColumns(new StringBuilder(str1));
		ArrayList<StringBuilder> arr2 = parseColumns(new StringBuilder(str2));
		ArrayList<StringBuilder> arr3 = parseColumns(new StringBuilder(str3));
		ArrayList<StringBuilder> arr4 = parseColumns(new StringBuilder(str4));
		ArrayList<StringBuilder> arr5 = parseColumns(new StringBuilder(str5));
		ArrayList<StringBuilder> arr6 = parseColumns(new StringBuilder(str6));
		ArrayList<StringBuilder> arr7 = parseColumns(new StringBuilder(str7));

		for (int i = 0; i < arr1.size(); i++)
			assertEquals(arr1.get(i).toString(), stock1.getFields().get(i).toString());
		for (int i = 0; i < arr2.size(); i++)
			assertEquals(arr2.get(i).toString(), stock2.getFields().get(i).toString());
		for (int i = 0; i < arr3.size(); i++)
			assertEquals(arr3.get(i).toString(), stock3.getFields().get(i).toString());
		for (int i = 0; i < arr4.size(); i++)
			assertEquals(arr4.get(i).toString(), stock4.getFields().get(i).toString());
		for (int i = 0; i < arr5.size(); i++)
			assertEquals(arr5.get(i).toString(), stock5.getFields().get(i).toString());
		for (int i = 0; i < arr6.size(); i++)
			assertEquals(arr6.get(i).toString(), stock6.getFields().get(i).toString());
		for (int i = 0; i < arr7.size(); i++)
			assertEquals(arr7.get(i).toString(), stock7.getFields().get(i).toString());
	}

	public static ArrayList<StringBuilder> parseColumns(StringBuilder txt) {
		String sep = ".";
		ArrayList<StringBuilder> array = new ArrayList<StringBuilder>();
		array.add(new StringBuilder(txt.substring(0, 2)));
		array.add(new StringBuilder(txt.substring(2, 10)));
		array.add(new StringBuilder(txt.substring(10, 12)));
		array.add(new StringBuilder(txt.substring(12, 24).trim()));
		array.add(new StringBuilder(txt.substring(24, 27)));
		array.add(new StringBuilder(txt.substring(27, 39)));
		array.add(new StringBuilder(txt.substring(39, 49)));
		array.add(new StringBuilder(txt.substring(49, 52)));
		array.add(new StringBuilder(txt.substring(52, 56).trim()));
		array.add(new StringBuilder(txt.substring(56, 67)).append(sep).append(txt.substring(67, 69)));
		array.add(new StringBuilder(txt.substring(69, 80)).append(sep).append(txt.substring(80, 82)));
		array.add(new StringBuilder(txt.substring(82, 93)).append(sep).append(txt.substring(93, 95)));
		array.add(new StringBuilder(txt.substring(95, 106)).append(sep).append(txt.substring(106, 108)));
		array.add(new StringBuilder(txt.substring(108, 119)).append(sep).append(txt.substring(119, 121)));
		array.add(new StringBuilder(txt.substring(121, 132)).append(sep).append(txt.substring(132, 134)));
		array.add(new StringBuilder(txt.substring(134, 143)).append(sep).append(txt.substring(143, 145)));
		array.add(new StringBuilder(txt.substring(147, 152)));
		array.add(new StringBuilder(txt.substring(152, 170)));
		array.add(new StringBuilder(txt.substring(170, 188)).append(sep).append(txt.substring(186, 188)));
		array.add(new StringBuilder(txt.substring(188, 201)).append(sep).append(txt.substring(199, 201)));
		array.add(new StringBuilder(txt.substring(201, 202)));
		array.add(new StringBuilder(txt.substring(202, 210)));
		array.add(new StringBuilder(txt.substring(210, 217)));
		array.add(new StringBuilder(txt.substring(217, 230)).append(sep).append(txt.substring(228, 230)));
		array.add(new StringBuilder(txt.substring(230, 242).trim()));
		array.add(new StringBuilder(txt.substring(242, 245)));
		return array;
	}

}
