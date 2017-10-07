package com.tapette.stock.bovespaHistoryFormater.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.file.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ProventosFormater  implements Formaters{

	class SubThread extends Thread{

		URL url = null;
		int[] tags = new int[7]; 

		public SubThread(URL url) {
			this.url = url;
		}

		@Override
		public void run() {
			super.run();
			ArrayList<String> list = getProventos();
			for (int i = 0; i < list.size(); i++)
				parseTags(list.get(i));
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

		private void parseTags(String str) {
			if(str.contains("/>")) {
				System.out.println("Major error");
				return;
			}
			parseTags2(str, str.indexOf(">"));
			System.out.println();
		}

		private void parseTags2(String str, int InitialTag) {
			if(str.indexOf("<",InitialTag)<0)
				return;
			if(!(str.substring(InitialTag+1, str.indexOf("<",InitialTag))).isEmpty()) {
				System.out.print(str.substring(InitialTag+1, str.indexOf("<",InitialTag)));
				System.out.print(";");
			}
			parseTags2(str, str.indexOf(">",str.indexOf("<",InitialTag)));
		}

	}

	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	volatile int count = 0;

	public ProventosFormater(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}

	@Override
	public boolean execute() throws Exception {
		StringBuilder url = null;
		for (int i = 0; i < stocks.size(); i++) {
			url = new StringBuilder();
			url.append("http://bvmf.bmfbovespa.com.br/Fundos-Listados/FundosListadosDetalhe.aspx?Sigla=").append(stocks.get(i).getCodigo()).append("&tipoFundo=Imobiliario&aba=abaPrincipal&idioma=pt-br");
			Thread th = new SubThread(new URL(url.toString()));
			th.start();
		}
		return false;
	}

	@Override
	public TableDAO getList() throws Exception {
		// TODO Auto-generated method stub
		TableDAO aa = new TableDAOImp();
		aa.
		return null;
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
