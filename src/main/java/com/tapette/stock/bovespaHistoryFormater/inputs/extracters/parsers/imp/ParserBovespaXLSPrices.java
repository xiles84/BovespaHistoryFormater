package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.ParserAbstract;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockEntryImp;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ParserBovespaXLSPrices extends ParserAbstract {

	private static Logger logger = LoggerFactory.getLogger( ParserBovespaXLSPrices.class );
	List<Stock> stocks = null;

	public ParserBovespaXLSPrices(List<Stock> stocks) {
		this.stocks = stocks;
	}

	@Override
	public void preFilter(String line, List<String> list) {
		for (int i = 0; i < stocks.size(); i++)
			if(line.substring(12, 24).trim().contains(stocks.get(i).getStock()))
				list.add(line);
	}

	@Override
	public StockEntry parseTags(String str, Stock stock) {

		ArrayList<StringBuilder> array = previousParseTags(str);
		if(logger.isDebugEnabled())
			logger.debug(String.format("The following tags were parsed [%s]", array));
		return new StockEntryImp(
				stock,
				Integer.parseInt(array.get(1).toString()),
				stringToDouble(array.get(13).toString()),
				-1,
				array.get(18).toString(),
				TypeStockEntry.PRICE);
	}

	@Override
	public URL getStockURL(Stock stock) throws MalformedURLException {
		return null;
	}

	private ArrayList<StringBuilder> previousParseTags(String str){
		ArrayList<StringBuilder> array = new ArrayList<StringBuilder>();
		String sep = ".";
		array.add(new StringBuilder(str.substring(0, 2))); // field 0
		array.add(new StringBuilder(str.substring(2, 10))); // field 1 - date
		array.add(new StringBuilder(str.substring(10, 12))); // field 2
		array.add(new StringBuilder(str.substring(12, 24).trim())); // field 3 - stock
		array.add(new StringBuilder(str.substring(24, 27))); // field 4
		array.add(new StringBuilder(str.substring(27, 39))); // field 5
		array.add(new StringBuilder(str.substring(39, 49))); // field 6
		array.add(new StringBuilder(str.substring(49, 52))); // field 7
		array.add(new StringBuilder(str.substring(52, 56).trim())); // field 8
		array.add(new StringBuilder(str.substring(56, 67)).append(sep).append(str.substring(67, 69))); // field 9
		array.add(new StringBuilder(str.substring(69, 80)).append(sep).append(str.substring(80, 82))); // field 10
		array.add(new StringBuilder(str.substring(82, 93)).append(sep).append(str.substring(93, 95))); // field 11
		array.add(new StringBuilder(str.substring(95, 106)).append(sep).append(str.substring(106, 108))); // field 12
		array.add(new StringBuilder(str.substring(108, 119)).append(sep).append(str.substring(119, 121))); // field 13 - profit
		array.add(new StringBuilder(str.substring(121, 132)).append(sep).append(str.substring(132, 134))); // field 14
		array.add(new StringBuilder(str.substring(134, 143)).append(sep).append(str.substring(143, 145))); // field 15
		array.add(new StringBuilder(str.substring(147, 152))); // field 16
		array.add(new StringBuilder(str.substring(152, 170))); // field 17
		array.add(new StringBuilder(str.substring(170, 188)).append(sep).append(str.substring(186, 188))); // field 18 - volume
		array.add(new StringBuilder(str.substring(188, 201)).append(sep).append(str.substring(199, 201))); // field 19
		array.add(new StringBuilder(str.substring(201, 202))); // field 20
		array.add(new StringBuilder(str.substring(202, 210))); // field 21
		array.add(new StringBuilder(str.substring(210, 217))); // field 22
		array.add(new StringBuilder(str.substring(217, 230)).append(sep).append(str.substring(228, 230))); // field 23
		array.add(new StringBuilder(str.substring(230, 242).trim())); // field 24
		array.add(new StringBuilder(str.substring(242, 245))); // field 25
		return array;
	}

}
