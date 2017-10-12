package com.tapette.stock.bovespaHistoryFormater.inputs.extracters;

import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;

public interface Extracters {

	public boolean execute() throws Exception;

	public TableDAO getList() throws Exception;

	public ArrayList<String> getFileDir();

}
