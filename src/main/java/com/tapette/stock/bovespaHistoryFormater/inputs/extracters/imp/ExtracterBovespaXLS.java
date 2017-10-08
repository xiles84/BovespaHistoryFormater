package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Extracters;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.imp.StockEntryBovespaXLSImp;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ExtracterBovespaXLS implements Extracters{

	private ArrayList<String> fileDir = new ArrayList<String>();
	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	private TableDAO list = null;


	public ExtracterBovespaXLS(String fileDir , ArrayList<Stock> stocks) throws Exception {
		this.fileDir.add(fileDir);
		this.stocks = stocks;
	}

	
	public ExtracterBovespaXLS(ArrayList<String> fileDir , ArrayList<Stock> stocks) throws Exception {
		this.fileDir = fileDir;
		this.stocks = stocks;
	}

	@Override
	public boolean execute() throws Exception {
		File folder = null;
		File[] listOfFiles = null;

		for (int i = 0; i < getFileDir().size() ; i++) {
			folder = getResource(getFileDir().get(i));
			listOfFiles = folder.listFiles();
			for (int j = 0; j < listOfFiles.length; j++)
				if (listOfFiles[j].isFile())
					run(listOfFiles[j]);
		}
		return true;
	}

	protected boolean run(String fileStr) throws Exception {
		if(fileStr == null || fileStr.isEmpty())
			throw new Exception();
		return run(new File(fileStr));
	}

	protected boolean run(File file) throws Exception {
		if(file == null)
			throw new Exception();
		if(this.list == null)
			this.list = new TableDAOImp();
		BufferedReader reader = null;
		try {
			reader = bufferedReader(fileReader(file));
			String text = null;
			while ((text = readLine(reader)) != null && text.length() == 245) {
				if(!text.startsWith("00COTAHIST") && !text.startsWith("99COTAHIST")) {
					if(this.stocks.isEmpty())
						list.add(new StockEntryBovespaXLSImp(text));
					else
						for (int j = 0; j < stocks.size(); j++)
							if(text.contains(this.stocks.get(j).getStock()))
								list.add(new StockEntryBovespaXLSImp(text));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
		return true;
	}

	@Override
	public TableDAO getList() throws Exception {
		if(list == null)
			execute();
		return list;
	}

	protected FileReader fileReader(File file) throws FileNotFoundException {
		return new FileReader(file);
	}

	protected String readLine(BufferedReader reader) throws IOException {
		return reader.readLine();
	}

	protected BufferedReader bufferedReader(FileReader fileReader) {
		return new BufferedReader(fileReader);
	}

	protected File getResource(String filePath) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = null;
		if(classLoader.getResource(filePath) != null)
			file = new File(classLoader.getResource(filePath).getFile());
		if(file == null || !file.exists())
			file = new File(filePath);
		return file;
	}

	@Override
	public ArrayList<String> getFileDir(){
		return this.fileDir;
	}

}
