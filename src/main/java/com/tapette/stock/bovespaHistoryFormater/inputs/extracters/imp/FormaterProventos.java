package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Formaters;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockEntryProvImp;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class FormaterProventos  implements Formaters{

	public class SubThread extends Thread{

		String stock = null;
		URL url = null;
		int[] tags = new int[7]; 
		TableDAO table = null;

		public SubThread(String stock ,URL url, TableDAO table) {
			this.stock = stock;
			this.url = url;
			this.table = table;
		}

		@Override
		public void run() {
			super.run();
			ArrayList<String> list = getProventos();
			String[] stringArray = new String[15];
			for (int i = 0; i < list.size(); i++) {
				try {
					stringArray = parseTags(list.get(i)).split(";");
					System.out.println((new StockEntryProvImp(stock, stringArray[1], stringArray[3], stringArray[4], stringArray[3]).toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private ArrayList<String> getProventos(){
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
					if(line.contains("RENDIMENTO"))
						list.add(line);
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

		private String parseTags(String str) throws Exception {
			if(str.contains("/>")) {
				throw new Exception("Invalid sequence of character \"/>\" found [" + str + "]");
			}
			StringBuilder strBdr = new StringBuilder();
			parseTags2(str, str.indexOf(">"), strBdr);
			return strBdr.toString();
		}

		private void parseTags2(String str, int initialTag, StringBuilder strBdr) {
			if(str.indexOf("<",initialTag)<0)
				return;
			int localClose = str.indexOf("<",initialTag);
			if(!(str.substring(initialTag+1, localClose)).isEmpty())
				if(localClose == str.lastIndexOf("<",initialTag))
					strBdr.append(str.substring(initialTag+1, localClose));
				else{
					strBdr.append(str.substring(initialTag+1, localClose)).append(";");
					parseTags2(str, str.indexOf(">",localClose), strBdr);
				}
			else
				parseTags2(str, str.indexOf(">",localClose), strBdr);
		}

	}

	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	volatile int count = 0;
	private TableDAO table = null;
	private int loopTimeout = 10;

	public FormaterProventos(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}

	@Override
	public boolean execute() throws Exception {
		StringBuilder url = null;
		table = new TableDAOImp();
		for (int i = 0; i < stocks.size(); i++) {
			url = new StringBuilder();
			url.append("http://bvmf.bmfbovespa.com.br/Fundos-Listados/FundosListadosDetalhe.aspx?Sigla=").append(stocks.get(i).getCodigo()).append("&tipoFundo=Imobiliario&aba=abaPrincipal&idioma=pt-br");
			Thread th = new SubThread(stocks.get(i).getStock() , new URL(url.toString()), table);
			th.start();
		}
		return true;
	}

	@Override
	public TableDAO getList() throws Exception {
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
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasFinished() {
		return (count == stocks.size() ) ? true : false;
	}

}
