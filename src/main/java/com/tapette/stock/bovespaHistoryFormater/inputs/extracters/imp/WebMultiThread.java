package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.Parsers;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class WebMultiThread extends Thread{
	
	private Stock stock = null;
	private URL url = null;
	private int[] tags = new int[7]; 
	private TableDAO table = null;
	private volatile int count = 0;
	private Parsers parser = null;

	public WebMultiThread(Stock stock ,URL url, TableDAO table, Parsers parser, int count) {
		this.stock = stock;
		this.url = url;
		this.table = table;
		this.count = count;
		this.parser = parser;
	}

	@Override
	public void run() {
		super.run();
		ArrayList<String> list = getLines();
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println(parser.parseTags(list.get(i), stock));
				//System.out.println((new StockEntryProvImp(stock, stringArray[1], stringArray[3], stringArray[4], stringArray[3]).toString()));
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
			conn = url.openConnection();
			conn.setConnectTimeout(60000);
			is = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null)
				parser.preFilter(line, list);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		count++;
		return list;
	}

}
