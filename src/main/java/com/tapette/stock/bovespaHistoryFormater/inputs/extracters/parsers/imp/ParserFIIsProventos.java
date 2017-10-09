package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp;

import java.util.ArrayList;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.Parsers;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockEntryProvFIIsImp;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ParserFIIsProventos implements Parsers{

	@Override
	public void preFilter(String line, List<String> list) {
		if(line.contains("Data") && line.contains("Abertura")&& line.contains("Fechamento") && line.contains("Negociadas")) {
			String[] column = line.split("</TR>");
			if(column.length < 1)
				return;
			for (int i = 1; i < column.length-1; i++)
				list.add(column[i]);
		}
	}

	public StockEntry parseTags(String str, Stock stock) throws Exception {
		if(str.contains("/>")) {
			throw new Exception("Invalid sequence of character \"/>\" found [" + str + "]");
		}
		ArrayList<String> list = new ArrayList<String>();
		parseTags2(str, str.indexOf(">"), list);
		return new StockEntryProvFIIsImp(stock.getStock(), null, "01/" + list.get(1), list.get(17), null);
	}

	private void parseTags2(String str, int initialTag, ArrayList<String> list) {
		if(str.indexOf("<",initialTag)<0)
			return;
		int localClose = str.indexOf("<",initialTag);
		if(!(str.substring(initialTag+1, localClose)).isEmpty()) {
			list.add(str.substring(initialTag+1, localClose));
			parseTags2(str, str.indexOf(">",localClose), list);
		}else {
			list.add(str.substring(initialTag+1, localClose));
			parseTags2(str, str.indexOf(">",localClose), list);
		}
	}

}
