package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.Parsers;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public abstract class ParserAbstract implements Parsers{

	@Override
	public abstract void preFilter(String line ,List<String> list);

	@Override
	public abstract StockEntry parseTags(String str, Stock stock) throws Exception;
	
	@Override
	public abstract URL getStockURL(Stock stock) throws MalformedURLException;
	
	protected String transformBrazilDateIntoUniversalDate(String brazilDate) throws Exception {
		if(!brazilDate.contains("/")) {
			if(brazilDate.length() == 8)
				return brazilDate;
			throw new Exception("Date [" + brazilDate + "] does not contain \"/\"");
		}
		String[] list = brazilDate.split("/");
		if(list.length < 3)
			throw new Exception("Date [" + brazilDate + "] does not have the expected format");
		StringBuilder str = new StringBuilder();
		str.append(list[2]).append(list[1]).append(list[0]);
		return str.toString();
	}
	
	protected String valuePontuationConverter(String str) {
		if(str.substring(str.lastIndexOf(".")+1).contains(","))
			return str.replaceAll("\\.", "%").replaceAll(",", ".").replaceAll("%", ",");
		return str;
	}

}
