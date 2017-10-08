package com.tapette.stock.bovespaHistoryFormater.inputs.extracters;

import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;

public interface Extracters {

	boolean execute() throws Exception;

	TableDAO getList() throws Exception;

	ArrayList<String> getFileDir();

}
