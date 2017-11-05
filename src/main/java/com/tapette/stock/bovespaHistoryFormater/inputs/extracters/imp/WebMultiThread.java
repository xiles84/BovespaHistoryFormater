package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.Parsers;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class WebMultiThread extends Thread {

	private static Logger logger = LoggerFactory.getLogger( WebMultiThread.class );

	private Stock stock = null;
	private volatile TableDAO table = null;
	private volatile boolean finished = false;
	private Parsers parser = null;

	public WebMultiThread(Stock stock, TableDAO table, Parsers parser) {
		this.stock = stock;
		this.table = table;
		this.parser = parser;
	}

	@Override
	public void run() {
		finished = false;
		super.run();
		if(logger.isDebugEnabled())
			logger.debug("run will exeture now");
		ArrayList<String> list = getLines();
		if(list == null)
			return;
		if(logger.isDebugEnabled())
			logger.debug(String.format("run got the following lines [%s]", list));
		for (int i = 0; i < list.size(); i++) {
			try {
				StockEntry stockEntry = parser.parseTags(list.get(i), stock);
				if(logger.isDebugEnabled())
					logger.debug(String.format("run will add the following stockEntry [%s]", stockEntry));
				table.addStockEntry(stockEntry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private ArrayList<String> getLines(){
		if(logger.isDebugEnabled())
			logger.debug("getLines will initiated");
		URLConnection conn;
		InputStream is = null;
		BufferedReader br = null;
		String line;
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn = parser.
					getStockURL(stock).
					openConnection();
			conn.setConnectTimeout(120000);
			is = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null)
				parser.preFilter(line, list);
		} catch (IOException e) {
			if(logger.isErrorEnabled())
				logger.error(e.getMessage(),e);
			finished = true;
			return null;
		}finally {
			try {
				if(br != null)
					br.close();
				if(is != null)
					is.close();
			} catch (IOException e) {
				if(logger.isErrorEnabled())
					logger.debug(e.getMessage(), e);
			}
		}
		if(logger.isDebugEnabled())
			logger.debug("getLines finishing");
		finished = true;
		return list;
	}

	public boolean hasFinished() {
		if(logger.isDebugEnabled())
			logger.debug(String.format("hasFinished will return [%s]", finished));
		return finished;
	}



}
