package com.tapette.stock.bovespaHistoryFormater.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.tapette.stock.bovespaHistoryFormater.file.table.Line;
import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;

public class Formater {
	
	String fileStr = "";
	
	public TableDAO execute() throws Exception {
		if(this.fileStr.isEmpty())
			throw new Exception();
		TableDAO list = new TableDAO();
		File file = new File(this.fileStr);
		BufferedReader reader = null;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;
		    while ((text = reader.readLine()) != null && text.length() == 245) {
		        list.add(new Line(text));
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
		return list;
	}

	public String getFileStr() {
		return fileStr;
	}

	public void setFileStr(String fileStr) {
		this.fileStr = fileStr;
	}

}
