package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp;

import java.util.ArrayList;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.Parsers;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockEntryProv;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ParserBovespaProventos implements Parsers{

	@Override
	public void preFilter(String line ,List<String> list) {
		if(line.contains("RENDIMENTO"))
			list.add(line);
	}

	@Override
	public StockEntry parseTags(String str, Stock stock) throws Exception {
		if(str.contains("/>")) {
			throw new Exception("Invalid sequence of character \"/>\" found [" + str + "]");
		}
		ArrayList<String> list = new ArrayList<String>();
		parseTags2(str, str.indexOf(">"), list);
		return new StockEntryProv(stock.getStock(),
				list.get(1),
				transformBrazilDateIntoUniversalDate(list.get(3)),
				valuePontuationConverter(list.get(4)),
				transformBrazilDateIntoUniversalDate(list.get(6)),
				null);
	}

	private void parseTags2(String str, int initialTag, ArrayList<String> list) {
		if(str.indexOf("<",initialTag)<0)
			return;
		int localClose = str.indexOf("<",initialTag);
		if(!(str.substring(initialTag+1, localClose)).isEmpty()) {
			list.add(str.substring(initialTag+1, localClose));
			parseTags2(str, str.indexOf(">",localClose), list);
		}else
			parseTags2(str, str.indexOf(">",localClose), list);
	}
	

	
	private String transformBrazilDateIntoUniversalDate(String brazilDate) throws Exception {
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
	
	private String valuePontuationConverter(String str) {
		if(str.substring(str.lastIndexOf(".")+1).contains(","))
			return str.replaceAll("\\.", "%").replaceAll(",", ".").replaceAll("%", ",");
		return str;
	}

}
