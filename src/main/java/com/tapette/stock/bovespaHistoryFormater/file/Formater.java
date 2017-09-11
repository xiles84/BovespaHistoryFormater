package com.tapette.stock.bovespaHistoryFormater.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;

public class Formater {
	
	String fileStr = "";
	private boolean executed = false;
	private TableDAO list = null;
	
	public Formater() {
		this.executed = false;
	}
	
	public boolean execute() throws Exception {
		executed = run();
		return executed;
	}
	
	private boolean run() throws Exception {
		if(this.fileStr.isEmpty())
			throw new Exception();
		this.list = new TableDAO();
		File file = new File(this.fileStr);
		BufferedReader reader = null;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;
		    while ((text = reader.readLine()) != null && text.length() == 245) {
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

	public String getFileStr() {
		return fileStr;
	}

	public void setFileStr(String fileStr) {
		this.fileStr = fileStr;
	}

	public boolean isExecuted() {
		return executed;
	}

	public TableDAO getList() throws Exception {
		if(!executed) throw new Exception(this.getClass().getName() + "was not executd");
		return list;
	}
	
	

}
