package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import java.net.URL;
import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Extracters;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserBovespaProventos;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ExtracterProventosBovespa  implements Extracters{

	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	volatile int count = 0;
	private TableDAO table = null;
	private int loopTimeout = 10;

	public ExtracterProventosBovespa(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}

	@Override
	public boolean execute() throws Exception {
		StringBuilder url = null;
		table = new TableDAOImp();
		for (int i = 0; i < stocks.size(); i++) {
			url = new StringBuilder();
			url.append("http://bvmf.bmfbovespa.com.br/Fundos-Listados/FundosListadosDetalhe.aspx?Sigla=").append(stocks.get(i).getCodigo()).append("&tipoFundo=Imobiliario&aba=abaPrincipal&idioma=pt-br");
			Thread th = new WebMultiThread(stocks.get(i) , new URL(url.toString()), table, new ParserBovespaProventos(), count);
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
