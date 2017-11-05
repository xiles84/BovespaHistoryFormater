package com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyFile;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyStockArray;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Extracters;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.Parsers;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class ExtracterBovespaXLS implements Extracters {

	private static Logger logger = LoggerFactory.getLogger( ExtracterBovespaXLS.class );

	private ArrayList<String> fileDir = new ArrayList<String>();
	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	//private TableDAO table = null;
	private Parsers parser = null;
	private volatile boolean finished = false;


	public ExtracterBovespaXLS(ArrayList<String> fileDir , ArrayList<Stock> stocks, Parsers parser) throws ExceptionEmptyStockArray {
		if(stocks == null || stocks.isEmpty())
			throw new ExceptionEmptyStockArray();
		this.fileDir = fileDir;
		this.stocks = stocks;
		this.parser = parser;
	}

	@Override
	public boolean execute(TableDAO table) throws IOException, ExceptionEmptyFile, ExceptionInvalidFormat {
		File folder = null;
		File[] listOfFiles = null;
		for (int i = 0; i < getFileDir().size() ; i++) {
			folder = getResource(getFileDir().get(i));
			listOfFiles = folder.listFiles();
			for (int j = 0; j < listOfFiles.length; j++)
				if (listOfFiles[j].isFile())
					run(listOfFiles[j], table);
		}
		return true;
	}

	protected boolean run(String fileStr, TableDAO table) throws ExceptionEmptyFile, ExceptionInvalidFormat {
		if(fileStr == null || fileStr.isEmpty())
			throw new ExceptionEmptyFile();
		return run(new File(fileStr), table);
	}

	protected boolean run(File file, TableDAO table) throws ExceptionEmptyFile, ExceptionInvalidFormat{
		if(file == null)
			throw new ExceptionEmptyFile();
		finished = false;
		if(logger.isDebugEnabled())
			logger.debug(String.format("run was called for [%s]", file.getAbsolutePath()));
		BufferedReader reader = null;
		try {
			reader = bufferedReader(fileReader(file));
			String text = null;
			while ((text = readLine(reader)) != null && text.length() == 245) {
				outerloop:
					for (int j = 0; j < stocks.size(); j++) {
						if(logger.isDebugEnabled())
							logger.debug(String.format("run will check if [%s] contains [%s] = %s", text, this.stocks.get(j).getStock(), text.contains(this.stocks.get(j).getStock())));
						if(text.contains(this.stocks.get(j).getStock())) {
							table.addStockEntry(parser.parseTags(text, this.stocks.get(j)));
							if(logger.isDebugEnabled())
								logger.debug(String.format("run found this tags [%s]", parser.parseTags(text, this.stocks.get(j)) != null ? parser.parseTags(text, this.stocks.get(j)) : "null"));
							break outerloop;
						}
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
		finished = true;
		return true;
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

	@Override
	public boolean hasFinished() {
		return finished;
	}

}
