package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Extracters;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.Parsers;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ExtracterBovespaXLS implements Extracters {

	private ArrayList<String> fileDir = new ArrayList<String>();
	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	private TableDAO table = null;
	private Parsers parser = null;
	private boolean hasExecuted = false;


	public ExtracterBovespaXLS(ArrayList<String> fileDir , ArrayList<Stock> stocks, TableDAO table, Parsers parser) throws Exception {
		if(stocks == null || stocks.isEmpty())
			throw new Exception("Stock array must not be neither null nor empty");
		this.fileDir = fileDir;
		this.stocks = stocks;
		this.parser = parser;
		this.table = table;
	}

	@Override
	public boolean execute() throws Exception {
		hasExecuted = false;
		File folder = null;
		File[] listOfFiles = null;
		for (int i = 0; i < getFileDir().size() ; i++) {
			folder = getResource(getFileDir().get(i));
			listOfFiles = folder.listFiles();
			for (int j = 0; j < listOfFiles.length; j++)
				if (listOfFiles[j].isFile())
					run(listOfFiles[j]);
		}
		hasExecuted = true;
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
		BufferedReader reader = null;
		try {
			reader = bufferedReader(fileReader(file));
			String text = null;
			while ((text = readLine(reader)) != null && text.length() == 245) {
				outerloop:
				for (int j = 0; j < stocks.size(); j++)
					if(text.contains(this.stocks.get(j).getStock())) {
						table.addStock(parser.parseTags(text, this.stocks.get(j)));
						break outerloop;
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
		if(!hasExecuted)
			execute();
		return table;
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
