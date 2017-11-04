package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.ParserAbstract;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockEntryImp;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ParserFIIsProventos extends ParserAbstract {

	private static Logger logger = LoggerFactory.getLogger( ParserFIIsProventos.class );

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

	@Override
	public StockEntry parseTags(String str, Stock stock) throws ExceptionInvalidFormat {
		if(str.contains("/>")) {
			throw new ExceptionInvalidFormat("Invalid sequence of character \"/>\" found [" + str + "]");
		}
		ArrayList<String> list = new ArrayList<String>();
		parseTags2(str, str.indexOf(">"), list);
		if(logger.isDebugEnabled())
			logger.debug(String.format("The following tags were parsed [%s]", list));
		return new StockEntryImp(
				stock,
				Integer.parseInt(transformBrazilDateIntoUniversalDate("01/" + list.get(1))),
				-1,
				stringToDouble(valuePontuationConverter(list.get(17))),
				valuePontuationConverter(list.get(13)),
				TypeStockEntry.PROVENTOS);
	}

	@Override
	public URL getStockURL(Stock stock) throws MalformedURLException {
		StringBuilder url = new StringBuilder();
		url.append("http://fiis.com.br/").append(stock.getStock()).append("/?aba=tabela");
		return new URL(url.toString());
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
