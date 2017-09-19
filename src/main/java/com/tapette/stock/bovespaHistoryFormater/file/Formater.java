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
	private TableDAO list = null;

	public Formater(String fileDir) {
		this.fileDir.add(fileDir);
	}

	public Formater(ArrayList<String> fileDir) {
		this.fileDir = fileDir;
	}

	public boolean execute() throws Exception {
		File folder = null;
		File[] listOfFiles = null;

		for (int i = 0; i < this.fileDir.size() ; i++) {
			folder = new File(this.fileDir.get(i));
			listOfFiles = folder.listFiles();
			for (int j = 0; j < listOfFiles.length; j++) {
				if (listOfFiles[j].isFile())
					run(listOfFiles[j]);
			}
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
		this.list = new TableDAOImp();
		BufferedReader reader = null;
		try {
			reader = bufferedReader(fileReader(file));
			String text = null;
			while ((text = readLine(reader)) != null && text.length() == 245) {
				if(!text.startsWith("00COTAHIST") && !text.startsWith("99COTAHIST"))
					list.add(new StockEntry(text));
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

}
