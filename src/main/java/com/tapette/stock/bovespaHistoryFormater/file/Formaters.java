package com.tapette.stock.bovespaHistoryFormater.file;

import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;

public interface Formaters {

	boolean execute() throws Exception;

	TableDAO getList() throws Exception;

	ArrayList<String> getFileDir();

}
