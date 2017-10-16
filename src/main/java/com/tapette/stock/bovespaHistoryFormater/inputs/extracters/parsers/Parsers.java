package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public interface Parsers {

	public void preFilter(String line, List<String> list);

	public StockEntry parseTags(String str, Stock stock) throws Exception;
	
	public URL getStockURL(Stock stock) throws MalformedURLException;

}
