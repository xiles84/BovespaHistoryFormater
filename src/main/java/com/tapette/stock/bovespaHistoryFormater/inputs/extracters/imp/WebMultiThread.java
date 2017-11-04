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
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockGroup;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class WebMultiThread extends Thread {

	private static Logger logger = LoggerFactory.getLogger( WebMultiThread.class );

	private Stock stock = null;
	private volatile TableDAO table = null;
	private boolean finished = false;
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
		ArrayList<String> list = getLines();
		if(list == null)
			return;
		for (int i = 0; i < list.size(); i++) {
			try {
				table.addStockEntry(parser.parseTags(list.get(i), stock));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private ArrayList<String> getLines(){
		URLConnection conn;
		InputStream is = null;
		BufferedReader br = null;
		String line;
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn = parser.
					getStockURL(stock).
					openConnection();
			conn.setConnectTimeout(60000);
			is = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null)
				parser.preFilter(line, list);
		} catch (IOException e) {
			if(logger.isErrorEnabled())
				logger.error(e.getMessage(),e);
			return null;
		}finally {
			try {
				if(br != null)
					br.close();
				if(is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		finished = true;
		return list;
	}

	public boolean hasFinished() {
		return finished;
	}



}
