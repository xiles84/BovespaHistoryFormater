package com.tapette.stock.bovespaHistoryFormater.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.file.table.imp.TableDAOImp;

public class Formater {

	private ArrayList<String> fileDir = new ArrayList<String>();
	private ArrayList<String> stocks = new ArrayList<String>();
	private TableDAO list = null;

	public Formater(String fileDir) throws Exception {
		this.fileDir.add(fileDir);
		execute();
	}

	public Formater(String fileDir , ArrayList<String> stocks) throws Exception {
		this.fileDir.add(fileDir);
		this.stocks = stocks;
		execute();
	}

	public Formater(ArrayList<String> fileDir) throws Exception {
		this.fileDir = fileDir;
		execute();
	}

	public Formater(ArrayList<String> fileDir , ArrayList<String> stocks) throws Exception {
		this.fileDir = fileDir;
		this.stocks = stocks;
		execute();
	}

	public boolean execute() throws Exception {
		System.out.println("1");
		File folder = null;
		File[] listOfFiles = null;

		for (int i = 0; i < getFileDir().size() ; i++) {
			System.out.println("2");
			folder = getResource(getFileDir().get(i));
			System.out.println("3");
			listOfFiles = folder.listFiles();
			for (int j = 0; j < listOfFiles.length; j++)
				if (listOfFiles[j].isFile())
					run(listOfFiles[j]);
		}
		System.out.println("4");
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
						list.add(new StockEntry(text));
					else
						for (int j = 0; j < stocks.size(); j++)
							if(text.contains(this.stocks.get(j)))
								list.add(new StockEntry(text));
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

	public TableDAO getList() throws Exception {
		if(list == null)
			throw new Exception(this.getClass().getName() + "was not executed");
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
		System.out.println("getResource");
		ClassLoader classLoader = getClass().getClassLoader();
		File file = null;
		if(classLoader.getResource(filePath) != null)
			file = new File(classLoader.getResource(filePath).getFile());
		if(file == null || !file.exists())
			file = new File(filePath);
		return file;
	}
	
	public ArrayList<String> getFileDir(){
		return this.fileDir;
	}

}
