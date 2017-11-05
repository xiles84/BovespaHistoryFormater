package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Extracters;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.Parsers;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ExtracterProventosMultiThread  implements Extracters {

	private static Logger logger = LoggerFactory.getLogger( ExtracterProventosMultiThread.class );

	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	private ArrayList<WebMultiThread> threads = new ArrayList<WebMultiThread>();
	private Parsers parser = null;

	public ExtracterProventosMultiThread(ArrayList<Stock> stocks, TableDAO table, Parsers parser) {
		this.stocks = stocks;
		this.parser = parser;
	}

	@Override
	public boolean execute(TableDAO table) {
		if(logger.isDebugEnabled())
			logger.debug("execute will begin now");
		for (int i = 0; i < stocks.size(); i++) {
			WebMultiThread th = new WebMultiThread(stocks.get(i), table, parser);
			th.start();
			threads.add(th);
		}
		return true;
	}

	@Override
	public ArrayList<String> getFileDir() {
		return null;
	}

	@Override
	public boolean hasFinished() {
		if(logger.isDebugEnabled())
			logger.debug(String.format("hasFinished will begin now with size [%s]", threads.size()));
		if(threads.size() == 0)
			return false;
		for (int i = 0; i < threads.size(); i++) {
			if(!threads.get(i).hasFinished())
				return false;
		}
		if(logger.isDebugEnabled())
			logger.debug("hasFinished will return true");
		return true;
	}

}
