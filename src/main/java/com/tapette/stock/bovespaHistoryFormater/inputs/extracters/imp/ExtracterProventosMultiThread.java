package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Extracters;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.Parsers;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ExtracterProventosMultiThread  implements Extracters {

	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	private ArrayList<WebMultiThread> threads = new ArrayList<WebMultiThread>();
	private TableDAO table = null;
	private TableDAO tabletmp = null;
	private Parsers parser = null;
	private int loopTimeout = 30;

	public ExtracterProventosMultiThread(ArrayList<Stock> stocks, TableDAO table, Parsers parser) {
		this.stocks = stocks;
		this.tabletmp = table;
		this.parser = parser;
	}

	@Override
	public boolean execute() {
		for (int i = 0; i < stocks.size(); i++) {
			table = tabletmp;
			WebMultiThread th = new WebMultiThread(stocks.get(i), table, parser);
			th.start();
			threads.add(th);
		}
		return true;
	}

	@Override
	public TableDAO getList() throws InterruptedException {
		if(table == null)
			execute();
		int loopCount = 0;
		while(!hasFinished() && loopCount < loopTimeout*2) {
			Thread.sleep(500);
			loopCount++;
		}
		return table;
	}

	@Override
	public ArrayList<String> getFileDir() {
		return null;
	}

	public boolean hasFinished() {
		boolean ret = true;
		for (int i = 0; i < threads.size(); i++) {
			ret = ret && threads.get(i).hasFinished();
		}
		return ret;
	}

}
